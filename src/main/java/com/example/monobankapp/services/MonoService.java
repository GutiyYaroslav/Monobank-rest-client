package com.example.monobankapp.services;

import com.example.monobankapp.utils.monobankDataConverter.MonobankCurrencyRateConverter;
import com.example.monobankapp.utils.monobankDataConverter.MonobankStatementBalanceConverter;
import com.example.monobankapp.utils.monobankDataConverter.MonobankUserConverter;
import com.example.monobankapp.clients.MonoClient;
import com.example.monobankapp.models.internal.CurrencyRate;
import com.example.monobankapp.models.internal.StatementBalance;
import com.example.monobankapp.models.internal.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class MonoService {

    private final MonoClient monoClient;

    public List<CurrencyRate> getAllCurrency(){
        return MonobankCurrencyRateConverter.convertToCustomCurrencyRate(monoClient.getAllCurrency());
    }

    public User getUserInfoByToken(String token){
        return MonobankUserConverter.convertToCustomUser(monoClient.getUserByToken(token));
    }

    public List<StatementBalance>getMonobankStatementOnlyByToken(String token,
                                                                 Optional<String> account,
                                                                 Optional<String> from,
                                                                 Optional<String> to){
        return MonobankStatementBalanceConverter
                .convertToCustomSStatementBalance(monoClient.getMonobankStatementOnlyByToken(token, account, from, to));
    }
}
