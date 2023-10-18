package com.app.lpnotifier.backend.model;

public class TokenCredetial {
    private String token;

    public TokenCredetial() {
    }

    public TokenCredetial(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }
}
