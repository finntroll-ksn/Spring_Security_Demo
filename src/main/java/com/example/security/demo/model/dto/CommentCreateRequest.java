package com.example.security.demo.model.dto;

import com.example.security.demo.validation.ValidArticleId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class CommentCreateRequest {
    private Long id;
    private String text;

    @ValidArticleId
    private String articleId;
}
