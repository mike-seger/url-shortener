package com.spahija.urlshortener.urlshortener.service;

import com.spahija.urlshortener.urlshortener.model.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class SecurityService {

    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public String generateUnencryptedPass() {
        String alphanumvalues = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        Random random = new Random();

        StringBuilder sb = new StringBuilder(8);
        for (int i = 0; i < 8; i++) {
            char randomChar = alphanumvalues.charAt(random.nextInt(alphanumvalues.length()));
            sb.append(randomChar);
        }
        String unencryptedPass = sb.toString();

        return unencryptedPass;
    }

    public String encryptPass(String unencryptedPass) {
        String encryptedPass = passwordEncoder.encode(unencryptedPass);

        return encryptedPass;
    }

    public Boolean checkPassword(String enteredPass, User user) {
        Boolean matches = passwordEncoder.matches(enteredPass, user.getPassword());

        return matches;
    }
}
