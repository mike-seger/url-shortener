package com.spahija.urlshortener.urlshortener.service;

import com.spahija.urlshortener.urlshortener.model.AccountCreationResponse;
import com.spahija.urlshortener.urlshortener.model.User;
import com.spahija.urlshortener.urlshortener.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountCreationService {

    private UserRepository userRepository;
    private SecurityService securityService;

    @Autowired
    public AccountCreationService(UserRepository userRepository, SecurityService securityService) {
        this.userRepository = userRepository;
        this.securityService = securityService;
    }

    public AccountCreationResponse createAccount(String accountId) {
        AccountCreationResponse accountCreationResponse = new AccountCreationResponse();
        User user = userRepository.findByAccountId(accountId);

        if (user != null) {
            accountCreationResponse.setSuccess(false);
            accountCreationResponse.setDescription("Account with that ID already exists.");
        }
        else{
            User newUser = new User(accountId);
            String unencryptedPass = securityService.generateUnencryptedPass();
            String encryptedPass = securityService.encryptPass(unencryptedPass);
            newUser.setPassword(encryptedPass);
            userRepository.save(newUser);
            accountCreationResponse.setSuccess(true);
            accountCreationResponse.setDescription("Your account was created.");
            accountCreationResponse.setPassword(unencryptedPass);
        }

        return accountCreationResponse;
    }
}
