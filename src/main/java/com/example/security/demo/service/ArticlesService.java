package com.example.security.demo.service;

import com.example.security.demo.model.dto.ArticlesLiteTransfer;
import com.example.security.demo.model.dto.ArticlesWithCommentsTransfer;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;

public interface ArticlesService {
    Page<ArticlesLiteTransfer> get(int page, int limit);

    ArticlesWithCommentsTransfer getById(String id);

    ArticlesLiteTransfer create(ArticlesLiteTransfer message, Authentication authentication);

    ArticlesLiteTransfer update(ArticlesLiteTransfer messageFromDb, ArticlesLiteTransfer message);

    void delete(String id);
}
