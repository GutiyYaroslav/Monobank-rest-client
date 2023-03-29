package com.example.monobankapp.models.CustomModels;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
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
