package com.example.monobankapp.models.internal;

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
