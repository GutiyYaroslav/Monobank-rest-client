package com.example.monobankapp.models;


import lombok.Data;

@Data
public class CurrencyRate {

    private String currencyCodeA;

    private String currencyCodeB;

    private String date;

    private double rateSell;

    private double rateBuy;

    private double rateCross;

}
