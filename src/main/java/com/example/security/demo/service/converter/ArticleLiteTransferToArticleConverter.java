package com.example.security.demo.service.converter;

import com.example.security.demo.model.Article;
import com.example.security.demo.model.dto.ArticlesLiteTransfer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ArticleLiteTransferToArticleConverter implements Converter<ArticlesLiteTransfer, Article> {

    @Autowired
    UserWithTransferToUserConverter userWithTransferToUserConverter;

    @Override
    public Article convert(ArticlesLiteTransfer source) {
        return Article.builder()
                .id(source.getId())
                .text(source.getText())
                .author(userWithTransferToUserConverter.convert(source.getAuthor()))
                .build();
    }
}
