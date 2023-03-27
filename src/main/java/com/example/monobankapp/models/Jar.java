package com.example.monobankapp.models;

import lombok.Data;

@Data
public class Jar {

    private String id;
    private String sendId;
    private String title;
    private String description;
    private Integer currencyCode;
    private Long balance;
    private Long goal;
}
