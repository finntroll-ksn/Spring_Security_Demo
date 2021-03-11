package com.example.security.demo.service.converter;

import com.example.security.demo.model.Article;
import com.example.security.demo.model.Comment;
import com.example.security.demo.model.dto.ArticlesWithCommentsTransfer;
import com.example.security.demo.model.dto.CommentTransfer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ArticleToArticleWithCommentTransfer implements Converter<Article, ArticlesWithCommentsTransfer> {

    @Autowired
    UserToUserLikeAuthorTransferConverter userToUserLikeAuthorTransferConverter;

    @Autowired
    CommentToCommentTransferConverter commentToCommentTransferConverter;

    @Override
    public ArticlesWithCommentsTransfer convert(Article source) {
        List<Comment> commentList = source.getCommentList();
        List<CommentTransfer> commentTransferList = new ArrayList<>();
        commentList.forEach((comment) -> {
            commentTransferList.add(commentToCommentTransferConverter.convert(comment));
        });
        return ArticlesWithCommentsTransfer.builder()
                .id(source.getId())
                .text(source.getText())
                .author(userToUserLikeAuthorTransferConverter.convert(source.getAuthor()))
                .creationDate(source.getCreationDate())
                .commentList(commentTransferList)
                .build();
    }
}
