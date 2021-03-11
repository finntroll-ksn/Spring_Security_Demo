package com.example.security.demo.model.dto;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class UserTransfer implements Serializable {
    private Long id;
    private String name;
    private String username;
    private String email;
}
