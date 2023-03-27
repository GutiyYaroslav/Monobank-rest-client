package com.example.monobankapp.services;

import com.example.monobankapp.clients.MonoClient;
import com.example.monobankapp.models.CurrencyRate;
import com.example.monobankapp.models.MonobankStatement;
import com.example.monobankapp.models.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class MonoService {

    // EUR - 978 - євро
    // USD - 840  - долар США
    // UAH - 980 - гривня
    // GBP - 826 - фунт стерлінгів


    private final static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss zzz");
    private final static Map<String, String> currencyMap = Map.of(
            "978", "EUR",
            "840", "USD",
            "980", "UAH",
            "826", "GBP"
    );

    private final MonoClient monoClient;

    public List<CurrencyRate> getAllCurrency(){
       List<CurrencyRate> currentListOfCurrencyRate = monoClient.getAllCurrency();


//      Sorting a list by necessary currencies.

        List<CurrencyRate> sortedListOfCurrencyRate = currentListOfCurrencyRate.stream()
               .filter(currencyRate -> currencyMap.containsKey(currencyRate.getCurrencyCodeA()) ||
                       currencyMap.containsKey(currencyRate.getCurrencyCodeB()))
               .collect(Collectors.toList());

//      Changing currency values to more understandable.

        for (CurrencyRate currencyRate : sortedListOfCurrencyRate) {
            String codeA = currencyRate.getCurrencyCodeA();
            String codeB = currencyRate.getCurrencyCodeB();

            if (currencyMap.containsKey(codeA)) {
                currencyRate.setCurrencyCodeA(currencyMap.get(codeA));
            }

            if (currencyMap.containsKey(codeB)) {
                currencyRate.setCurrencyCodeB(currencyMap.get(codeB));
            }
         }

//      Changing the value of the "date" field to a more friendly.

        for (CurrencyRate rate : sortedListOfCurrencyRate) {
            Instant instant = Instant.ofEpochSecond(Long.parseLong(rate.getDate()));
            ZonedDateTime dateTime = ZonedDateTime.ofInstant(instant, ZoneId.of("UTC"));
            String formattedDate = formatter.format(dateTime);
            rate.setDate(formattedDate);
        }
        return sortedListOfCurrencyRate;
    }

    public User getUserInfoByToken(String token){
        return monoClient.getUserByToken(token);
    }
    public List<MonobankStatement>getMonobankStatementOnlyByToken(String token){
        return monoClient.getMonobankStatementOnlyByToken(token);
    }

    public List<MonobankStatement> getMonobankStatementByTokenAndParameters(String account,
                                                                            String from,
                                                                            String to,
                                                                            String token) {
        return monoClient.getMonobankStatementByTokenAndParameters(account, from, to, token);
    }
}
