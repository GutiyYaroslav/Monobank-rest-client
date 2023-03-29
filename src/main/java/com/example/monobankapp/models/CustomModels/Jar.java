package com.example.monobankapp.models.CustomModels;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Jar {
    private String id;
    private String sendId;
    private String title;
    private String description;
    private Integer currencyCode;
    private Long balance;
    private Long goal;
}
