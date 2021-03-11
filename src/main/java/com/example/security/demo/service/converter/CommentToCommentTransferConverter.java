package com.example.security.demo.service.converter;

import com.example.security.demo.model.Comment;
import com.example.security.demo.model.dto.CommentTransfer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CommentToCommentTransferConverter implements Converter<Comment, CommentTransfer> {

    @Autowired
    UserToUserLikeAuthorTransferConverter userToUserLikeAuthorTransferConverter;

    @Override
    public CommentTransfer convert(Comment source) {
        return CommentTransfer.builder()
                .id(source.getId())
                .text(source.getText())
                .creationDate(source.getCreationDate())
                .author(userToUserLikeAuthorTransferConverter.convert(source.getAuthor()))
                .build();
    }
}
