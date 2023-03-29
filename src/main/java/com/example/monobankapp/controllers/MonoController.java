package com.example.monobankapp.controllers;


import com.example.monobankapp.models.CustomModels.CurrencyRate;
import com.example.monobankapp.models.CustomModels.StatementBalance;
import com.example.monobankapp.models.CustomModels.User;
import com.example.monobankapp.models.MonobankModels.MonobankStatementBalance;
import com.example.monobankapp.services.MonoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/mono")
public class MonoController {

    private final MonoService monoService;

    @GetMapping
    public ResponseEntity<List<CurrencyRate>> getCurrency(){
        return new ResponseEntity<>(monoService.getAllCurrency(), HttpStatus.OK);
    }

    @GetMapping("/account")
    public ResponseEntity<User> getUserInfo(@RequestHeader("X-Token") String token){
        return new ResponseEntity<>(monoService.getUserInfoByToken(token),HttpStatus.OK);
    }

    @GetMapping("/bank-statement")
    public ResponseEntity<List<StatementBalance>> getMonobankStatement(@RequestHeader("X-Token") String token,
                                                                       @RequestParam("account") Optional<String> account,
                                                                       @RequestParam("from") Optional<String> from,
                                                                       @RequestParam("to") Optional<String> to){
        return new ResponseEntity<>(monoService.getMonobankStatementOnlyByToken(token, account, from, to), HttpStatus.OK);
    }
}
