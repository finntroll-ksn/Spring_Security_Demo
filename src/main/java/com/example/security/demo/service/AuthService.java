package com.example.security.demo.service;

import com.example.security.demo.model.auth.request.LoginForm;
import com.example.security.demo.model.auth.request.SignUpForm;
import com.example.security.demo.model.auth.response.JwtResponse;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    JwtResponse authenticateUser(LoginForm loginRequest);

    ResponseEntity<String> registrationUser(SignUpForm signUpRequest);
}
