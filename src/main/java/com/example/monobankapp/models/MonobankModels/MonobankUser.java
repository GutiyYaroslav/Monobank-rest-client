package com.example.monobankapp.models.MonobankModels;

import com.example.monobankapp.models.MonobankModels.MonobankAccount;
import com.example.monobankapp.models.MonobankModels.MonobankJar;
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
