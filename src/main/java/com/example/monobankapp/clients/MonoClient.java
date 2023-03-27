package com.example.monobankapp.clients;

import com.example.monobankapp.models.CurrencyRate;
import com.example.monobankapp.models.MonobankStatement;
import com.example.monobankapp.models.User;
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

@Component
@RequiredArgsConstructor
public class MonoClient {

    private static final String URL = "https://api.monobank.ua";

    private final RestTemplate restTemplate;

//    Instant now = Instant.now();
//    long unixTime = now.getEpochSecond();

//    Instant twentyNineDaysAgo = Instant.now().minus(Duration.ofDays(29));
//    long twentyNineDaysAgoUnixTime = twentyNineDaysAgo.getEpochSecond();


    private String unixTimeNow = String.valueOf(Instant.now().getEpochSecond());
    private String unixTimeTwentyNineDaysAgo = String.valueOf(Instant.now().minus(Duration.ofDays(29)).getEpochSecond());

    public List<CurrencyRate> getAllCurrency(){
        return restTemplate.exchange(URL + "/bank/currency",
                HttpMethod.GET, null, new ParameterizedTypeReference<List<CurrencyRate>>(){}).getBody();
    }


    public User getUserByToken(String token) {

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Token", token);
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
        ResponseEntity<User> response = restTemplate.exchange(URL + "/personal/client-info",
                HttpMethod.GET, entity, User.class);
        return response.getBody();
    }

    public List<MonobankStatement> getMonobankStatementOnlyByToken(String token) {

        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Token", token);
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
        ResponseEntity<List<MonobankStatement>> response = restTemplate.exchange(URL +
                "/personal/statement/0/"+unixTimeTwentyNineDaysAgo+"/" + unixTimeNow,
                HttpMethod.GET, entity, new ParameterizedTypeReference<List<MonobankStatement>>(){});
        return response.getBody();

    }

    public List<MonobankStatement> getMonobankStatementByTokenAndParameters(String account,
                                                                            String from,
                                                                            String to,
                                                                            String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Token", token);
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
        ResponseEntity<List<MonobankStatement>> response = restTemplate.exchange(URL +
                        "/personal/statement/" + account + "/" +from+"/" + to,
                HttpMethod.GET, entity, new ParameterizedTypeReference<List<MonobankStatement>>(){});
        return response.getBody();
    }
}
