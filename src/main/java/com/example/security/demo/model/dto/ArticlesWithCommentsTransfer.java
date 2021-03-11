package com.example.security.demo.model.dto;

import java.io.Serializable;
import java.time.ZonedDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class ArticlesWithCommentsTransfer implements Serializable {
    private Long id;
    private String text;
    private UserTransfer author;
    private ZonedDateTime creationDate;
    private List<CommentTransfer> commentList = new ArrayList<>();
}
