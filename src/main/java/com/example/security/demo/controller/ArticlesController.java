package com.example.security.demo.controller;

import com.example.security.demo.model.dto.ArticlesLiteTransfer;
import com.example.security.demo.model.dto.ArticlesWithCommentsTransfer;
import com.example.security.demo.service.ArticlesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
public class ArticlesController {

    @Autowired
    ArticlesService articlesService;

    @GetMapping(value = "/api/articles", params = { "page", "limit" })
    public @ResponseBody
    ResponseEntity<Page<ArticlesLiteTransfer>> get(@RequestParam("page") int page,
        @RequestParam("limit") int limit){
        return ResponseEntity.ok(articlesService.get(page, limit));
    }

    @GetMapping("/api/articles/{id}")
    public ArticlesWithCommentsTransfer getOne(@PathVariable("id") String articleId){
        return articlesService.getById(articleId);
    }

    @PostMapping("/api/articles")
    @ResponseBody
    public ArticlesLiteTransfer create(@RequestBody ArticlesLiteTransfer article, Authentication authentication){
        return articlesService.create(article, authentication);
    }

    @DeleteMapping("/api/articles/{id}")
    public void delete(@PathVariable("id") String articleId){
        articlesService.delete(articleId);
    }
}
