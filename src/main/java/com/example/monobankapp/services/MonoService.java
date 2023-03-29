package com.example.monobankapp.services;

import com.example.monobankapp.Utils.MonobankDataConverter.MonobankCurrencyRateConverter;
import com.example.monobankapp.Utils.MonobankDataConverter.MonobankStatementBalanceConverter;
import com.example.monobankapp.Utils.MonobankDataConverter.MonobankUserConverter;
import com.example.monobankapp.clients.MonoClient;
import com.example.monobankapp.models.CustomModels.CurrencyRate;
import com.example.monobankapp.models.CustomModels.StatementBalance;
import com.example.monobankapp.models.CustomModels.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class MonoService {

    private final MonobankCurrencyRateConverter monobankCurrencyRateConverter;
    private final MonobankStatementBalanceConverter monobankStatementBalanceConverter;
    private final MonobankUserConverter monobankUserConverter;
    private final MonoClient monoClient;

    public List<CurrencyRate> getAllCurrency(){
        return monobankCurrencyRateConverter.ConvertToCustomCurrencyRate(monoClient.getAllCurrency());
    }

    public User getUserInfoByToken(String token){
        return monobankUserConverter.convertToCustomUser(monoClient.getUserByToken(token));
    }

    public List<StatementBalance>getMonobankStatementOnlyByToken(String token,
                                                                 Optional<String> account,
                                                                 Optional<String> from,
                                                                 Optional<String> to){
        return monobankStatementBalanceConverter
                .convertToCustomSStatementBalance(monoClient.getMonobankStatementOnlyByToken(token, account, from, to));
    }
}
