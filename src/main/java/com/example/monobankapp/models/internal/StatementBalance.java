package com.example.monobankapp.models.internal;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StatementBalance {
    private String id;
    private Long time;
    private String description;
    private Integer mcc;
    private Integer originalMcc;
    private Boolean hold;
    private Long amount;
    private Long operationAmount;
    private Integer currencyCode;
    private Long commissionRate;
    private Long cashbackAmount;
    private Long balance;
    private String comment;
    private String receiptId;
    private String invoiceId;
    private String counterEdrpou;
    private String counterIban;
    private String counterName;
}
