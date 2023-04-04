package com.example.monobankapp.utils.monobankDataConverter;

import com.example.monobankapp.enums.CurrencyNumberValue;
import com.example.monobankapp.models.internal.CurrencyRate;
import com.example.monobankapp.models.monobank.MonobankCurrencyRate;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class MonobankCurrencyRateConverter {

    private final static DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss zzz");

    public static List<CurrencyRate> convertToCustomCurrencyRate(List<MonobankCurrencyRate> monobankCurrencyRate){
        return monobankCurrencyRate.stream()
                .filter(CurrencyNumberValue::isContainInEnum)
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

    private static String changeCurrencyCode(int code) {
        return Arrays.stream(CurrencyNumberValue.values())
                .filter(item -> item.getCode() == code)
                .findFirst()
                .map(String::valueOf)
                .orElseThrow();
    }

    private static String changeDate(Long date) {
        Instant instant = Instant.ofEpochSecond(date);
        ZonedDateTime dateTime = ZonedDateTime.ofInstant(instant, ZoneId.of("UTC"));
        return FORMATTER.format(dateTime);
    }

}

