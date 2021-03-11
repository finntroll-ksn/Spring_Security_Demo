package com.example.security.demo.service.converter;

import com.example.security.demo.model.Comment;
import com.example.security.demo.model.dto.CommentTransfer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CommentTransferToCommentConverter implements Converter<CommentTransfer, Comment> {

    @Autowired
    UserWithTransferToUserConverter userWithTransferToUserConverter;

    @Autowired
    ArticleLiteTransferToArticleConverter articleLiteTransferToArticleConverter;

    @Override
    public Comment convert(CommentTransfer source) {
        return Comment.builder()
                .id(source.getId())
                .text(source.getText())
                .creationDate(source.getCreationDate())
                .author(userWithTransferToUserConverter.convert(source.getAuthor()))
                .article(articleLiteTransferToArticleConverter.convert(source.getArticle()))
                .build();
    }
}
