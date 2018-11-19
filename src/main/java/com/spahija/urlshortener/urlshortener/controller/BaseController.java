package com.spahija.urlshortener.urlshortener.controller;

import com.spahija.urlshortener.urlshortener.model.UrlMapping;
import com.spahija.urlshortener.urlshortener.repository.UrlMappingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class BaseController {

    private UrlMappingRepository urlMappingRepository;

    @Autowired
    public BaseController(UrlMappingRepository urlMappingRepository) {
        this.urlMappingRepository = urlMappingRepository;
    }

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/help")
    public String help(){
        return "help";
    }

    @GetMapping("/r/{slug}")
    public RedirectView getStatistics(@PathVariable String slug) {
        UrlMapping urlMapping = urlMappingRepository.findBySlug(slug);

        RedirectView redirectView = new RedirectView();
        redirectView.setUrl(urlMapping.getLongUrl());

        if (urlMapping.getRedirectType().equals(301)) {
            redirectView.setStatusCode(HttpStatus.MOVED_PERMANENTLY);
        }
        else if(urlMapping.getRedirectType().equals(302)){
            redirectView.setStatusCode(HttpStatus.FOUND);
        }

        if (urlMapping != null) {
            urlMapping.incrementRedirects();
            urlMappingRepository.save(urlMapping);
        }
        return redirectView;
    }

}
