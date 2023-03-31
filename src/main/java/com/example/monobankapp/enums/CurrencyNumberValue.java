package com.example.monobankapp.enums;

public enum CurrencyNumberValue {
    EUR("978"),
    USD("840"),
    UAH("980"),
    GBP("826");

    private final String code;

    CurrencyNumberValue(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

}
