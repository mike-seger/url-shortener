package com.spahija.urlshortener.urlshortener.service;

import org.springframework.stereotype.Service;

@Service
public class Base62Converter {

    public String convert(Integer id){
        Integer number = id;
        String map = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

        StringBuilder sb = new StringBuilder();

        while (number > 0){
            Integer remainder = number % 62;
            sb.append(map.charAt(remainder-1));
            number = number / 62;
        }
        sb.reverse();
        String slug = sb.toString();

        return slug;
    }
}
