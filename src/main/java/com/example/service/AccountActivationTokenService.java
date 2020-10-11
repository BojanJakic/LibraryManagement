package com.example.service;

import java.util.Date;

import com.example.tokens.AccountActivationToken;

public interface AccountActivationTokenService {

public void saveToken(AccountActivationToken tok);
public AccountActivationToken findByToken(String token);
public void deleteToken(AccountActivationToken token);
public void updateToken(Date date,String token,Integer id);
}
