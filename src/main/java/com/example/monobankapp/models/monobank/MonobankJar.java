package com.example.monobankapp.models.monobank;

import lombok.Data;

@Data
public class MonobankJar {
    private String id;
    private String sendId;
    private String title;
    private String description;
    private Integer currencyCode;
    private Long balance;
    private Long goal;
}
