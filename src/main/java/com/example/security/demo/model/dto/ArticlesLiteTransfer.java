package com.example.security.demo.model.dto;

import java.io.Serializable;
import java.time.ZonedDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ArticlesLiteTransfer implements Serializable {
    private Long id;
    private String text;
    private UserTransfer author;
    private ZonedDateTime creationDate;
}
