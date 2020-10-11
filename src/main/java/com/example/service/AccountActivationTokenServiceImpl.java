package com.example.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.repository.AccountActivationTokenRepository;
import com.example.tokens.AccountActivationToken;

@Service
public class AccountActivationTokenServiceImpl implements AccountActivationTokenService{

    @Autowired
    private AccountActivationTokenRepository accRepo;
	
	@Override
	@Transactional
	public void saveToken(AccountActivationToken tok) {
		accRepo.save(tok);
		
	}

	@Override
	@Transactional
	public AccountActivationToken findByToken(String token) {
		
		return accRepo.findByToken(token);
	}

	@Override
	@Transactional
	public void deleteToken(AccountActivationToken token) {
		accRepo.delete(token);
		
	}
	
	@Override
	@Transactional
	public void updateToken(Date date,String token,Integer id){
		accRepo.updateToken(date,token,id);
	}

}
