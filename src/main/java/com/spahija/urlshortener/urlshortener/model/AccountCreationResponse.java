package com.spahija.urlshortener.urlshortener.model;

public class AccountCreationResponse {
    private Boolean success;

    private String description;

    private String password;

    public AccountCreationResponse() {
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getSuccess() {
        return success;
    }

    public String getDescription() {
        return description;
    }

    public String getPassword() {
        return password;
    }
}
