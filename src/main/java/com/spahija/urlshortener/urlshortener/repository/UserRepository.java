package com.spahija.urlshortener.urlshortener.repository;

import com.spahija.urlshortener.urlshortener.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByAccountId(String accountId);

}
