package com.example.security.demo.service;

import org.springframework.security.core.Authentication;

public interface JwtProvider {
    String generateJwtToken(Authentication authentication);

    String getUserNameFromJwtToken(String token);

    boolean validateJwtToken(String authToken);
}
