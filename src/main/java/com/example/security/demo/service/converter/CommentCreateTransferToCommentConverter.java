package com.example.security.demo.service.converter;

import com.example.security.demo.model.Article;
import com.example.security.demo.model.Comment;
import com.example.security.demo.model.dto.CommentCreateRequest;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CommentCreateTransferToCommentConverter implements Converter<CommentCreateRequest, Comment> {


    @Override
    public Comment convert(CommentCreateRequest source) {
        Article article = Article.builder()
                .id(Long.parseLong(source.getArticleId()))
                .build();
        return Comment.builder()
                .id(source.getId())
                .text(source.getText())
                .article(article)
                .build();
    }
}
