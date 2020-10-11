package com.example.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.tokens.AccountActivationToken;

public interface AccountActivationTokenRepository extends BaseRepository<AccountActivationToken,Integer>{

@Query("SELECT tok FROM AccountActivationToken tok WHERE tok.token = ?1")
public AccountActivationToken findByToken(String token);

@Modifying
@Query("UPDATE AccountActivationToken tok SET tok.created = ?1,tok.token = ?2 WHERE tok.id = ?3")
public void updateToken(Date date,String token,Integer id);

}
