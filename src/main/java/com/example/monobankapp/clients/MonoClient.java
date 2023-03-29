package com.example.monobankapp.clients;

import com.example.monobankapp.models.MonobankModels.MonobankCurrencyRate;
import com.example.monobankapp.models.MonobankModels.MonobankStatementBalance;
import com.example.monobankapp.models.MonobankModels.MonobankUser;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class MonoClient {

    private static final String URL = "https://api.monobank.ua";
    private String unixTimeNow = null;
    private String unixTimeTwentyNineDaysAgo = null;
    private final RestTemplate restTemplate;

    public List<MonobankCurrencyRate> getAllCurrency(){
        return restTemplate.exchange(URL + "/bank/currency",
                HttpMethod.GET, null, new ParameterizedTypeReference<List<MonobankCurrencyRate>>(){}).getBody();
    }

    public MonobankUser getUserByToken(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Token", token);
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
        return restTemplate.exchange(URL + "/personal/client-info",
                HttpMethod.GET, entity, MonobankUser.class).getBody();
    }

    public List<MonobankStatementBalance> getMonobankStatementOnlyByToken(String token,
                                                                          Optional<String> account,
                                                                          Optional<String> from,
                                                                          Optional<String> to) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Token", token);
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
        if(account.isPresent() && from.isPresent() && to.isPresent()){
            return restTemplate.exchange(URL +
                            "/personal/statement/" + account + "/"+ from + "/" + to,
                    HttpMethod.GET, entity, new ParameterizedTypeReference<List<MonobankStatementBalance>>(){}).getBody();
        }else{
            unixTimeNow = String.valueOf(Instant.now().getEpochSecond());
            unixTimeTwentyNineDaysAgo = String.valueOf(Instant.now().minus(Duration.ofDays(29)).getEpochSecond());
            return restTemplate.exchange(URL +
                            "/personal/statement/0/"+unixTimeTwentyNineDaysAgo+"/" + unixTimeNow,
                    HttpMethod.GET, entity, new ParameterizedTypeReference<List<MonobankStatementBalance>>(){}).getBody();
        }
    }
}
