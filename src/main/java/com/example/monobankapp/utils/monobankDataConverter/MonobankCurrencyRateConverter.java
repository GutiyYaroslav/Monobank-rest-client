package com.example.monobankapp.utils.monobankDataConverter;

import com.example.monobankapp.enums.CurrencyNumberValue;
import com.example.monobankapp.models.internal.CurrencyRate;
import com.example.monobankapp.models.monobank.MonobankCurrencyRate;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class MonobankCurrencyRateConverter {

    private final static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss zzz");

    public List<CurrencyRate> convertToCustomCurrencyRate(List<MonobankCurrencyRate> monobankCurrencyRate) {
        return changeDate(filterAndChangeCurrencyCodeInCurrencyRate(getCustomCurrencyRateFromMonobankCurrencyRate(monobankCurrencyRate)));
    }

    private List<CurrencyRate> getCustomCurrencyRateFromMonobankCurrencyRate(List<MonobankCurrencyRate> monobankCurrencyRates) {
        List<CurrencyRate> customCurrencyRates = new ArrayList<>();
        for (MonobankCurrencyRate item : monobankCurrencyRates) {
            CurrencyRate currentCurrencyRate = CurrencyRate.builder()
                    .currencyCodeA(String.valueOf(item.getCurrencyCodeA()))
                    .currencyCodeB(String.valueOf(item.getCurrencyCodeB()))
                    .date(String.valueOf(item.getDate()))
                    .rateSell(item.getRateSell())
                    .rateBuy(item.getRateBuy())
                    .rateCross(item.getRateCross())
                    .build();
            customCurrencyRates.add(currentCurrencyRate);
        }
        return customCurrencyRates;
    }

    private List<CurrencyRate> filterAndChangeCurrencyCodeInCurrencyRate(List<CurrencyRate> currencyRates) {
        return currencyRates.stream()
                .filter(item -> {
                    boolean currencyACodeIsValid = Arrays.stream(CurrencyNumberValue.values())
                            .anyMatch(enumValue -> enumValue.getCode().equals(item.getCurrencyCodeA()));
                    boolean currencyBCodeIsValid = Arrays.stream(CurrencyNumberValue.values())
                            .anyMatch(enumValue -> enumValue.getCode().equals(item.getCurrencyCodeB()));
                    return currencyACodeIsValid && currencyBCodeIsValid;
                })
                .peek(item -> {
                    for (CurrencyNumberValue enumValue : CurrencyNumberValue.values()) {
                        if (item.getCurrencyCodeA().equals(enumValue.getCode())) {
                            item.setCurrencyCodeA(enumValue.toString());
                        }
                        if (item.getCurrencyCodeB().equals(enumValue.getCode())) {
                            item.setCurrencyCodeB(enumValue.toString());
                        }
                    }
                })
                .collect(Collectors.toList());
    }

    private List<CurrencyRate> changeDate(List<CurrencyRate> currencyRates) {
        for (CurrencyRate rate : currencyRates) {
            Instant instant = Instant.ofEpochSecond(Long.parseLong(rate.getDate()));
            ZonedDateTime dateTime = ZonedDateTime.ofInstant(instant, ZoneId.of("UTC"));
            String formattedDate = formatter.format(dateTime);
            rate.setDate(formattedDate);
        }
        return currencyRates;
    }
}

