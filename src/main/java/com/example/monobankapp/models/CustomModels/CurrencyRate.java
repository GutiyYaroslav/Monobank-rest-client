package com.example.monobankapp.models.CustomModels;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CurrencyRate {
    private String currencyCodeA;
    private String currencyCodeB;
    private String date;
    private double rateSell;
    private double rateBuy;
    private double rateCross;
}
