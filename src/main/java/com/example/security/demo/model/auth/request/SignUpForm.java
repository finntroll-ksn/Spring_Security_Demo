package com.example.security.demo.model.auth.request;

import com.example.security.demo.model.RolePrefix;
import com.example.security.demo.validation.EmailUnique;
import com.example.security.demo.validation.RoleCorrect;
import com.example.security.demo.validation.UserNameUnique;
import lombok.Data;

import java.util.Set;

import javax.validation.constraints.*;

@Data
public class SignUpForm {

    @NotBlank
    @Size(min = 3, max = 50)
    private String name;

    @NotBlank
    @Size(min = 3, max = 50)
    @UserNameUnique
    private String username;

    @EmailUnique
    @NotBlank
    @Size(max = 60)
    @Email
    private String email;

    @RoleCorrect
    private Set<String> role;

    @NotBlank
    @Size(min = 6, max = 40)
    private String password;
}
