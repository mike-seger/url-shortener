package com.spahija.urlshortener.urlshortener.repository;

import com.spahija.urlshortener.urlshortener.model.UrlMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UrlMappingRepository extends JpaRepository<UrlMapping, Integer> {

    @Query(value = "SELECT ID FROM URL_MAPPING ORDER BY ID DESC LIMIT 1", nativeQuery = true)
    Integer getMaxId();

    UrlMapping findBySlug(String slug);
}
