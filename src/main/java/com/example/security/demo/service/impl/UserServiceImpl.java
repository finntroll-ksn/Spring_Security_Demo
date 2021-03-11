package com.example.security.demo.service.impl;

import com.example.security.demo.model.User;
import com.example.security.demo.repository.UserRepository;
import com.example.security.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Iterable<User> getUsers(){
        return userRepository.findAll();
    }

}
