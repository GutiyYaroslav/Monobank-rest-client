package com.example.monobankapp.models;

import lombok.Data;


import java.util.List;

@Data
public class User {

    private String clientId;
    private String name;
    private String webHookUrl;
    private String permissions;
    private List<Account> accounts;
    private List<Jar> jars;
}
