package com.spahija.urlshortener.urlshortener.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UrlMapping {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String longUrl;

    private String slug;

    private Integer redirectType;

    private Integer redirects = 0;

    public UrlMapping() {
    }

    public UrlMapping(String longUrl, Integer redirectType) {
        this.longUrl = longUrl;
        this.redirectType = redirectType;
    }

    public int getId() {
        return id;
    }

    public String getLongUrl() {
        return longUrl;
    }

    public void setLongUrl(String longUrl) {
        this.longUrl = longUrl;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public Integer getRedirectType() {
        return redirectType;
    }

    public void setRedirectType(Integer redirectType) {
        this.redirectType = redirectType;
    }

    public Integer getRedirects() {
        return redirects;
    }

    public void incrementRedirects() {
        redirects++;
    }
}
