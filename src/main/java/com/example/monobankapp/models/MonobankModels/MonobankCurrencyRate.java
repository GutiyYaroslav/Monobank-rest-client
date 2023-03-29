package com.example.monobankapp.models.MonobankModels;


import lombok.Data;

@Data
public class MonobankCurrencyRate {
    private Integer currencyCodeA;
    private Integer currencyCodeB;
    private Long date;
    private double rateSell;
    private double rateBuy;
    private double rateCross;
}
