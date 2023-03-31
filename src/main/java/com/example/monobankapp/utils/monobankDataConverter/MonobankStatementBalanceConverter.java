package com.example.monobankapp.utils.monobankDataConverter;

import com.example.monobankapp.models.internal.StatementBalance;
import com.example.monobankapp.models.monobank.MonobankStatementBalance;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MonobankStatementBalanceConverter {

    public List<StatementBalance> convertToCustomSStatementBalance(List<MonobankStatementBalance> monobankStatementBalances){
        return getCustomStatementBalanceFromMonobankStatementBalance(monobankStatementBalances);
    }

    private List<StatementBalance> getCustomStatementBalanceFromMonobankStatementBalance(List<MonobankStatementBalance> monobankStatementBalances) {
        List<StatementBalance> changedStatementBalances = new ArrayList<>();
        for (MonobankStatementBalance item : monobankStatementBalances) {
            StatementBalance currentStatementBalance = StatementBalance.builder()
                    .id(item.getId())
                    .time(item.getTime())
                    .description(item.getDescription())
                    .mcc(item.getMcc())
                    .originalMcc(item.getOriginalMcc())
                    .hold(item.getHold())
                    .amount(item.getAmount())
                    .operationAmount(item.getOperationAmount())
                    .currencyCode(item.getCurrencyCode())
                    .commissionRate(item.getCommissionRate())
                    .cashbackAmount(item.getCashbackAmount())
                    .balance(item.getBalance())
                    .comment(item.getComment())
                    .receiptId(item.getReceiptId())
                    .invoiceId(item.getInvoiceId())
                    .counterEdrpou(item.getCounterEdrpou())
                    .counterIban(item.getCounterIban())
                    .counterName(item.getCounterName())
                    .build();
            changedStatementBalances.add(currentStatementBalance);
        }
        return changedStatementBalances;
    }
}
