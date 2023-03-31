package com.example.monobankapp.models.monobank;

import lombok.Data;


import java.util.List;

@Data
public class MonobankUser {
    private String clientId;
    private String name;
    private String webHookUrl;
    private String permissions;
    private List<MonobankAccount> accounts;
    private List<MonobankJar> jars;
}
