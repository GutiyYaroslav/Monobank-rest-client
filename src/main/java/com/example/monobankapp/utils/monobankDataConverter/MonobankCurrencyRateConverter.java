package com.example.monobankapp.utils.monobankDataConverter;

import com.example.monobankapp.enums.CurrencyNumberValue;
import com.example.monobankapp.models.internal.CurrencyRate;
import com.example.monobankapp.models.monobank.MonobankCurrencyRate;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class MonobankCurrencyRateConverter {

    private final static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss zzz");

    public List<CurrencyRate> convertToCustomCurrencyRate(List<MonobankCurrencyRate> monobankCurrencyRate){
        return monobankCurrencyRate.stream()
                .filter(this::checkCurrencyCodeByEnum)
                .map(rate -> CurrencyRate.builder()
                        .currencyCodeA(changeCurrencyCode(rate.getCurrencyCodeA()))
                        .currencyCodeB(changeCurrencyCode(rate.getCurrencyCodeB()))
                        .rateBuy(rate.getRateBuy())
                        .rateSell(rate.getRateSell())
                        .date(changeDate(rate.getDate()))
                        .rateCross(rate.getRateCross())
                        .build())
                .collect(Collectors.toUnmodifiableList());
    }

    private boolean checkCurrencyCodeByEnum(MonobankCurrencyRate currencyRate){
        return Arrays.stream(CurrencyNumberValue.values())
                .anyMatch(value -> value.getCode() == currencyRate.getCurrencyCodeA()
                        || value.getCode() == currencyRate.getCurrencyCodeB());
    }

    private String changeCurrencyCode(int code) {
        return Arrays.stream(CurrencyNumberValue.values())
                .filter(item -> item.getCode() == code)
                .findFirst()
                .map(String::valueOf)
                .orElse(null);
    }

    private String changeDate(Long date) {
        Instant instant = Instant.ofEpochSecond(date);
        ZonedDateTime dateTime = ZonedDateTime.ofInstant(instant, ZoneId.of("UTC"));
        return formatter.format(dateTime);
    }

}

