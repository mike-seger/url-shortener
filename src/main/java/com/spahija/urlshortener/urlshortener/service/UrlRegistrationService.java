package com.spahija.urlshortener.urlshortener.service;

import com.spahija.urlshortener.urlshortener.model.UrlMapping;
import com.spahija.urlshortener.urlshortener.model.UrlRegistrationResponse;
import com.spahija.urlshortener.urlshortener.model.User;
import com.spahija.urlshortener.urlshortener.repository.UrlMappingRepository;
import com.spahija.urlshortener.urlshortener.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UrlRegistrationService {

    private UserRepository userRepository;
    private UrlMappingRepository urlMappingRepository;
    private Base62Converter base62Converter;

    @Autowired
    public UrlRegistrationService(UserRepository userRepository, UrlMappingRepository urlMappingRepository, Base62Converter base62Converter) {
        this.userRepository = userRepository;
        this.urlMappingRepository = urlMappingRepository;
        this.base62Converter = base62Converter;
    }

    public UrlRegistrationResponse registerUrl(String accountId, String host, Map<String, Object> payload) {
        UrlRegistrationResponse urlRegistrationResponse = new UrlRegistrationResponse();
        User user = userRepository.findByAccountId(accountId);

        String url = (String) payload.get("url");
        Integer redirectType = (Integer) payload.getOrDefault("redirectType", 302);
        UrlMapping urlMapping = new UrlMapping(url, redirectType);
        Integer result = urlMappingRepository.getMaxId();
        Integer nextId;
        if (result == null){
            nextId = 1;
        }
        else{
            nextId = result + 1;
        }

        String slug = base62Converter.convert(nextId);
        String shortUrl = host + "/r/" + slug;
        urlMapping.setSlug(slug);
        user.getUrlMappings().add(urlMapping);
        userRepository.save(user);

        urlRegistrationResponse.setShortUrl(shortUrl);

        return urlRegistrationResponse;
    }
}
