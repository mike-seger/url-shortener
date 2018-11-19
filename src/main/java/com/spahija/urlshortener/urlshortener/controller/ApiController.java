package com.spahija.urlshortener.urlshortener.controller;

import com.spahija.urlshortener.urlshortener.model.AccountCreationResponse;
import com.spahija.urlshortener.urlshortener.model.UrlRegistrationResponse;
import com.spahija.urlshortener.urlshortener.model.User;
import com.spahija.urlshortener.urlshortener.repository.UrlMappingRepository;
import com.spahija.urlshortener.urlshortener.repository.UserRepository;
import com.spahija.urlshortener.urlshortener.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ApiController {

    private UserRepository userRepository;
    private UrlMappingRepository urlMappingRepository;
    private AccountCreationService accountCreationService;
    private UrlRegistrationService urlRegistrationService;

    @Autowired
    public ApiController(UserRepository userRepository, UrlMappingRepository urlMappingRepository, AccountCreationService accountCreationService, UrlRegistrationService urlRegistrationService) {
        this.userRepository = userRepository;
        this.urlMappingRepository = urlMappingRepository;
        this.accountCreationService = accountCreationService;
        this.urlRegistrationService = urlRegistrationService;
    }

    @PostMapping("/account")
    public AccountCreationResponse createAccount(@RequestBody Map<String,String> payload, HttpServletResponse response) {
        String accountId = payload.get("AccountId");
        AccountCreationResponse accountCreationResponse = accountCreationService.createAccount(accountId);
        if (accountCreationResponse.getSuccess()){
            response.setStatus(201);
        }
        else{
            response.setStatus(409);
        }

        return accountCreationResponse;
    }

    @GetMapping("/accountid")
    public String getAccountId(@AuthenticationPrincipal UserDetails userDetails){
        return userDetails.getUsername();
    }

    @PostMapping("/register")
    public UrlRegistrationResponse registerUrl(@AuthenticationPrincipal UserDetails userDetails, @RequestHeader String host, @RequestBody Map<String, Object> payload, HttpServletResponse response) {
        String accountId = userDetails.getUsername();
        response.setStatus(201);

        return urlRegistrationService.registerUrl(accountId, host, payload);
    }

    @GetMapping(value = "/statistic/{accountId}", produces = "application/json")
    public Map<String, Integer> getStatistics(@AuthenticationPrincipal UserDetails userDetails) {

        String authAccountId = userDetails.getUsername();

        User user = userRepository.findByAccountId(authAccountId);
        StatisticsResponseFactory statisticsResponseFactory = new StatisticsResponseFactory(user);

        return statisticsResponseFactory.getStatistics();
    }

}
