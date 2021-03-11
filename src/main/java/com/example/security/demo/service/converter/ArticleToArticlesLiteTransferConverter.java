package com.example.security.demo.service.converter;

import com.example.security.demo.model.Article;
import com.example.security.demo.model.dto.ArticlesLiteTransfer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ArticleToArticlesLiteTransferConverter implements Converter<Article, ArticlesLiteTransfer> {

    @Autowired
    UserToUserLikeAuthorTransferConverter userToUserLikeAuthorTransferConverter;

    @Override
    public ArticlesLiteTransfer convert(Article source) {
        return ArticlesLiteTransfer.builder()
                .id(source.getId())
                .text(source.getText())
                .creationDate(source.getCreationDate())
                .author(userToUserLikeAuthorTransferConverter.convert(source.getAuthor()))
                .build();
    }
}
