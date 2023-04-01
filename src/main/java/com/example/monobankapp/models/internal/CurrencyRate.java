package com.example.monobankapp.models.internal;


import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CurrencyRate {
    private String currencyCodeA;
    private String currencyCodeB;
    private String date;
    private double rateSell;
    private double rateBuy;
    private double rateCross;
}
