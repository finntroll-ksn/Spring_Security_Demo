package com.example.security.demo.service.impl;

import com.example.security.demo.model.RolePrefix;
import com.example.security.demo.model.auth.request.LoginForm;
import com.example.security.demo.model.auth.request.SignUpForm;
import com.example.security.demo.model.auth.response.JwtResponse;
import com.example.security.demo.model.Role;
import com.example.security.demo.model.RoleName;
import com.example.security.demo.model.User;
import com.example.security.demo.repository.RoleRepository;
import com.example.security.demo.repository.UserRepository;
import com.example.security.demo.service.AuthService;
import com.example.security.demo.service.JwtProvider;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class AuthServiceImpl implements AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder encoder;

    @Override
    public JwtResponse authenticateUser(LoginForm loginRequest){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateJwtToken(authentication);

        return new JwtResponse(jwt);
    }

    @Override
    public ResponseEntity<String> registrationUser(SignUpForm signUpRequest){

        // Creating user's account
        User user = User.builder()
                            .name(signUpRequest.getName())
                            .username(signUpRequest.getUsername())
                            .email(signUpRequest.getEmail())
                            .password(encoder.encode(signUpRequest.getPassword()))
                            .build();

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();
        // TODO подумать как это можно сделать лучше
        strRoles.forEach(role -> {
            if(role.equals(RolePrefix.admin.toString())){
                Optional<Role> roleAdmin = roleRepository.findByName(RoleName.ROLE_ADMIN);
                roles.add(roleAdmin.get());
            }

            if(role.equals(RolePrefix.pm.toString())){
                Optional<Role> roleAdmin = roleRepository.findByName(RoleName.ROLE_ADMIN);
                roles.add(roleAdmin.get());
            }

            if(role.equals(RolePrefix.user.toString())){
                Optional<Role> roleUser = roleRepository.findByName(RoleName.ROLE_USER);
                roles.add(roleUser.get());
            }
        });

        user.setRoles(roles);
        userRepository.save(user);
        return ResponseEntity.ok().body("User registered successfully!");
    }
}
