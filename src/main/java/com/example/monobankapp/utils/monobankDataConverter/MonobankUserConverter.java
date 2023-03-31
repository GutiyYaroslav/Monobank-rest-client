package com.example.monobankapp.utils.monobankDataConverter;

import com.example.monobankapp.models.internal.Account;
import com.example.monobankapp.models.internal.Jar;
import com.example.monobankapp.models.internal.User;
import com.example.monobankapp.models.monobank.MonobankAccount;
import com.example.monobankapp.models.monobank.MonobankJar;
import com.example.monobankapp.models.monobank.MonobankUser;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MonobankUserConverter {
    public User convertToCustomUser(MonobankUser monobankUser){
        return getCustomUserFromMonobankUser(monobankUser);
    }

    private User getCustomUserFromMonobankUser(MonobankUser monobankUser){
        List<Account> currentAccounts = new ArrayList<>();
        for(MonobankAccount item : monobankUser.getAccounts()){
            Account currentAccount = Account.builder()
                    .id(item.getId())
                    .sendId(item.getSendId())
                    .balance(item.getBalance())
                    .creditLimit(item.getCreditLimit())
                    .type(item.getType())
                    .currencyCode(item.getCurrencyCode())
                    .cashbackType(item.getCashbackType())
                    .maskedPan(item.getMaskedPan())
                    .iban(item.getIban())
                    .build();
            currentAccounts.add(currentAccount);
        }
        List<Jar> currentJars = new ArrayList<>();
        if(monobankUser.getJars() != null){
            for(MonobankJar item : monobankUser.getJars()){
                Jar currentJar = Jar.builder()
                        .id(item.getId())
                        .sendId(item.getSendId())
                        .title(item.getTitle())
                        .description(item.getDescription())
                        .currencyCode(item.getCurrencyCode())
                        .balance(item.getBalance())
                        .goal(item.getGoal())
                        .build();
                currentJars.add(currentJar);
            }
        }
        return User.builder()
                .clientId(monobankUser.getClientId())
                .name(monobankUser.getName())
                .webHookUrl(monobankUser.getWebHookUrl())
                .permissions(monobankUser.getPermissions())
                .accounts(currentAccounts)
                .jars(currentJars)
                .build();
    }
}
