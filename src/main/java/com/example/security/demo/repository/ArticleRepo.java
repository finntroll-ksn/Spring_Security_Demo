package com.example.security.demo.repository;

import com.example.security.demo.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ArticleRepo extends PagingAndSortingRepository<Article, Long> {
    Boolean existsArticleById(Long id);
}
