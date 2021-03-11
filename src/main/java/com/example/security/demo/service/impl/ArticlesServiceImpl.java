package com.example.security.demo.service.impl;

import com.example.security.demo.model.Article;
import com.example.security.demo.model.dto.ArticlesLiteTransfer;
import com.example.security.demo.model.dto.ArticlesWithCommentsTransfer;
import com.example.security.demo.model.dto.UserTransfer;
import com.example.security.demo.repository.ArticleRepo;
import com.example.security.demo.security.UserPrinciple;
import java.time.ZonedDateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import com.example.security.demo.service.ArticlesService;
import org.springframework.stereotype.Component;

import java.util.Optional;

import static com.example.security.demo.config.CommonConfiguration.APP_CONVERSION_SERVICE;

@Component
public class ArticlesServiceImpl implements ArticlesService {

    @Autowired
    @Qualifier(APP_CONVERSION_SERVICE)
    private ConversionService conversionService;

    @Autowired
    ArticleRepo articleRepo;

    @Override
    public Page<ArticlesLiteTransfer> get(int page, int limit) {
        Pageable currentRequest = PageRequest.of(page - 1, limit);
        Page<Article> articleList = articleRepo.findAll(currentRequest);
        return articleList.map(article -> conversionService.convert(article, ArticlesLiteTransfer.class));
    }

    @Override
    @Cacheable(cacheNames = "article", key = "#id")
    public ArticlesWithCommentsTransfer getById(String id) {
        // TODO нужно учить пока просто сделал, без понимания
        Optional<Article> optionalArticle = articleRepo.findById(Long.parseLong(id));
        return conversionService.convert(optionalArticle.get(), ArticlesWithCommentsTransfer.class);
    }

    @Override
    public ArticlesLiteTransfer create(ArticlesLiteTransfer articlesLiteTransfer, Authentication authentication) {
        UserPrinciple userDetails = (UserPrinciple) authentication.getPrincipal();
        articlesLiteTransfer.setCreationDate(ZonedDateTime.now());
        UserTransfer user = UserTransfer.builder().id(userDetails.getId()).build();
        articlesLiteTransfer.setAuthor(user);
        // Article convert = conversionService.convert(articlesLiteTransfer, Article.class);
        Article article = articleRepo.save(conversionService.convert(articlesLiteTransfer, Article.class));
        return conversionService.convert(article, ArticlesLiteTransfer.class);
    }

    @Override
    @CachePut(cacheNames = "article", key = "#article.id")
    public ArticlesLiteTransfer update(ArticlesLiteTransfer articleFromDb, ArticlesLiteTransfer article) {
        BeanUtils.copyProperties(article, articleFromDb, "id");
        Article updatedMessage = articleRepo.save(conversionService.convert(articleFromDb, Article.class));
        return conversionService.convert(updatedMessage, ArticlesLiteTransfer.class);
    }

    @Override
    @CacheEvict(cacheNames = "article", key = "#id")
    public void delete(String id) {
        Article article = new Article();
        article.setId(Long.parseLong(id));
        articleRepo.delete(article);
    }
}
