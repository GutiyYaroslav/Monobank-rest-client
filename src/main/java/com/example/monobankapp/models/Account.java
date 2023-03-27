package com.example.monobankapp.models;

import lombok.Data;

import java.util.List;

@Data
public class Account {

    private String id;
    private String sendId;
    private Long balance;
    private Long creditLimit;
    private String type;
    private Integer currencyCode;
    private String cashbackType;
    private List<String> maskedPan;
    private String iban;


}
