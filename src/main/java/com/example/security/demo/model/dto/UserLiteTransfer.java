package com.example.security.demo.model.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserLiteTransfer {
    private Long id;
    private String name;
    private String username;
}
