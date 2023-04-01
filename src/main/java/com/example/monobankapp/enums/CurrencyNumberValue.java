package com.example.monobankapp.enums;

public enum CurrencyNumberValue {
    EUR(978),
    USD(840),
    UAH(980),
    GBP(826);

    private final int code;

    CurrencyNumberValue(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
