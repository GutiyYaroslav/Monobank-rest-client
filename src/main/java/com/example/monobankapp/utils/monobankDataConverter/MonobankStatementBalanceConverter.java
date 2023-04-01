package com.example.monobankapp.utils.monobankDataConverter;

import com.example.monobankapp.models.internal.StatementBalance;
import com.example.monobankapp.models.monobank.MonobankStatementBalance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MonobankStatementBalanceConverter {

    public List<StatementBalance> convertToCustomSStatementBalance(List<MonobankStatementBalance> monobankStatementBalances){
        return monobankStatementBalances.stream()
                .map(bill -> StatementBalance.builder()
                        .id(bill.getId())
                        .time(bill.getTime())
                        .description(bill.getDescription())
                        .mcc(bill.getMcc())
                        .originalMcc(bill.getOriginalMcc())
                        .hold(bill.getHold())
                        .amount(bill.getAmount())
                        .operationAmount(bill.getOperationAmount())
                        .currencyCode(bill.getCurrencyCode())
                        .commissionRate(bill.getCommissionRate())
                        .cashbackAmount(bill.getCashbackAmount())
                        .balance(bill.getBalance())
                        .comment(bill.getComment())
                        .receiptId(bill.getReceiptId())
                        .invoiceId(bill.getInvoiceId())
                        .counterEdrpou(bill.getCounterEdrpou())
                        .counterIban(bill.getCounterIban())
                        .counterName(bill.getCounterName())
                        .build())
                .collect(Collectors.toUnmodifiableList());
    }
}
