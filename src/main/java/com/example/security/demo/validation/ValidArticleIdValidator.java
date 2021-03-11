package com.example.security.demo.validation;

import com.example.security.demo.repository.ArticleRepo;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class ValidArticleIdValidator implements ConstraintValidator<ValidArticleId, String> {

  @Autowired
  ArticleRepo articleRepo;

  @Override
  public void initialize(ValidArticleId constraintAnnotation) {

  }

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    if(value == null) return true;
    return articleRepo.existsArticleById(Long.parseLong(value));
  }
}
