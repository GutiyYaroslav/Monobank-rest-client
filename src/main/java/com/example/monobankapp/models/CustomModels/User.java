package com.example.monobankapp.models.CustomModels;

import com.example.monobankapp.models.MonobankModels.MonobankAccount;
import com.example.monobankapp.models.MonobankModels.MonobankJar;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class User {
    private String clientId;
    private String name;
    private String webHookUrl;
    private String permissions;
    private List<Account> accounts;
    private List<Jar> jars;
}
