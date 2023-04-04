package com.example.monobankapp.enums;

import com.example.monobankapp.models.monobank.MonobankCurrencyRate;

import java.util.*;

public enum CurrencyNumberValue {

    EUR(978),
    USD(840),
    UAH(980),
    GBP(826);

    private final Integer code;

    private final static List<Integer> valuesOfEnum;

    CurrencyNumberValue(Integer code) {
        this.code = code;
    }
    static {
        valuesOfEnum = new ArrayList<>();
        for (CurrencyNumberValue currency : values()) {
            valuesOfEnum.add(currency.getCode());
        }
    }

    public Integer getCode() {
        return code;
    }

    public static Boolean isContainInEnum(MonobankCurrencyRate currencyRate){
        return valuesOfEnum.contains(currencyRate.getCurrencyCodeA()) &&
                valuesOfEnum.contains(currencyRate.getCurrencyCodeB());
    }
}
