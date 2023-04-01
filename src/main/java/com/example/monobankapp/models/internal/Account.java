package com.example.monobankapp.models.internal;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
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
