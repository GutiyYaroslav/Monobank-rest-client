package com.example.monobankapp.models.internal;

import lombok.Builder;
import lombok.Getter;

@Getter
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
