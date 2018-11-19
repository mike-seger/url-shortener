package com.spahija.urlshortener.urlshortener.service;

import com.spahija.urlshortener.urlshortener.model.UrlMapping;
import com.spahija.urlshortener.urlshortener.model.User;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class StatisticsResponseFactory {

    private Map<String, Integer> statistics = new HashMap<>();

    public StatisticsResponseFactory(User user) {
        Set<UrlMapping> urlMappings = user.getUrlMappings();

        for (UrlMapping urlMapping : urlMappings) {
            if (!statistics.containsKey(urlMapping.getLongUrl())) {
                statistics.put(urlMapping.getLongUrl(), urlMapping.getRedirects());
            }
            else{
                Integer currentRedirectCount = statistics.get(urlMapping.getLongUrl());
                currentRedirectCount += urlMapping.getRedirects();
                statistics.put(urlMapping.getLongUrl(), currentRedirectCount);
            }
        }
    }

    public Map<String, Integer> getStatistics() {
        return statistics;
    }
}
