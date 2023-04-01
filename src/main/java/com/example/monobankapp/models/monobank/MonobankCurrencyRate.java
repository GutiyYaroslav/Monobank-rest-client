package com.example.monobankapp.models.monobank;


import lombok.Data;

@Data
public class MonobankCurrencyRate {
    private int currencyCodeA;
    private int currencyCodeB;
    private Long date;
    private double rateSell;
    private double rateBuy;
    private double rateCross;
}
