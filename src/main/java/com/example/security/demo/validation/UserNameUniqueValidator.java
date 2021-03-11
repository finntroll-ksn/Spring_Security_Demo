package com.example.security.demo.validation;

import com.example.security.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UserNameUniqueValidator implements ConstraintValidator<UserNameUnique, String> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void initialize(UserNameUnique contactNumber) { }

    @Override
    public boolean isValid(String userName,
                           ConstraintValidatorContext cxt) {
        if(userName == null) return true;
        return !userRepository.existsByUsername(userName);
    }

}