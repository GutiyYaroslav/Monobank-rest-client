package com.example.monobankapp.controllers;


import com.example.monobankapp.models.CurrencyRate;
import com.example.monobankapp.models.MonobankStatement;
import com.example.monobankapp.models.User;
import com.example.monobankapp.services.MonoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/bank-statement/{account}/{from}/{to}")
    public ResponseEntity<List<MonobankStatement>> getMonobankSatment(@PathVariable("account") String account,
                                                @PathVariable("from") String from,
                                                @PathVariable("to") String to,
                                                @RequestHeader("X-Token") String token){

        return new ResponseEntity<>(monoService.getMonobankStatementByTokenAndParameters(account, from, to, token),HttpStatus.OK);
    }
    @GetMapping("/bank-statement")
    public ResponseEntity<List<MonobankStatement>> getMonobankSatment(@RequestHeader("X-Token") String token){

        return new ResponseEntity<>(monoService.getMonobankStatementOnlyByToken(token), HttpStatus.OK);
    }


}
