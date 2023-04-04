package com.example.monobankapp.utils.monobankDataConverter;

import com.example.monobankapp.models.internal.Account;
import com.example.monobankapp.models.internal.Jar;
import com.example.monobankapp.models.internal.User;
import com.example.monobankapp.models.monobank.MonobankAccount;
import com.example.monobankapp.models.monobank.MonobankJar;
import com.example.monobankapp.models.monobank.MonobankUser;

import java.util.List;
import java.util.stream.Collectors;


public class MonobankUserConverter {
    public static User convertToCustomUser(MonobankUser monobankUser){
        return User.builder()
                .clientId(monobankUser.getClientId())
                .name(monobankUser.getName())
                .webHookUrl(monobankUser.getWebHookUrl())
                .permissions(monobankUser.getPermissions())
                .accounts(convertAccounts(monobankUser.getAccounts()))
                .jars(convertJars(monobankUser.getJars()))
                .build();
    }

    private static List<Account> convertAccounts(List<MonobankAccount> accounts) {
        return accounts.stream()
                .map(account -> Account.builder()
                        .id(account.getId())
                        .sendId(account.getSendId())
                        .balance(account.getBalance())
                        .creditLimit(account.getCreditLimit())
                        .type(account.getType())
                        .currencyCode(account.getCurrencyCode())
                        .cashbackType(account.getCashbackType())
                        .maskedPan(account.getMaskedPan())
                        .iban(account.getIban())
                        .build())
                .collect(Collectors.toUnmodifiableList());
    }

    private static List<Jar> convertJars(List<MonobankJar> jars) {
        return jars.stream()
                .map(jar -> Jar.builder()
                        .id(jar.getId())
                        .sendId(jar.getSendId())
                        .title(jar.getTitle())
                        .description(jar.getDescription())
                        .currencyCode(jar.getCurrencyCode())
                        .balance(jar.getBalance())
                        .goal(jar.getGoal())
                        .build())
                .collect(Collectors.toUnmodifiableList());
    }
}
