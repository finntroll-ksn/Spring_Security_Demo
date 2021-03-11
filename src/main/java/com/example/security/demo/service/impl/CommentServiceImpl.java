package com.example.security.demo.service.impl;

import com.example.security.demo.model.Comment;
import com.example.security.demo.model.User;
import com.example.security.demo.model.dto.CommentCreateRequest;
import com.example.security.demo.model.dto.CommentTransfer;
import com.example.security.demo.repository.CommentRepo;
import com.example.security.demo.security.UserPrinciple;
import com.example.security.demo.service.CommentsService;
import com.example.security.demo.service.converter.CommentCreateTransferToCommentConverter;
import com.example.security.demo.service.converter.CommentToCommentTransferConverter;
import com.example.security.demo.service.converter.CommentTransferToCommentConverter;
import java.time.ZonedDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class CommentServiceImpl implements CommentsService {

    @Autowired
    CommentTransferToCommentConverter commentTransferToCommentConverter;

    @Autowired
    CommentToCommentTransferConverter commentToCommentTransferConverter;

    @Autowired
    CommentCreateTransferToCommentConverter commentCreateTransferToCommentConverter;


    @Autowired
    CommentRepo commentRepo;

    @Override
    @CacheEvict(cacheNames = "article", key = "#commentTransfer.articleId")
    public CommentTransfer create(
            CommentCreateRequest commentTransfer,
            Authentication authentication) {
        Comment comment = commentCreateTransferToCommentConverter.convert(commentTransfer);
        comment.setCreationDate(ZonedDateTime.now());
        UserPrinciple userDetails = (UserPrinciple) authentication.getPrincipal();
        User user = User.builder().id(userDetails.getId()).build();
        comment.setAuthor(user);
        commentRepo.save(comment);
        return commentToCommentTransferConverter.convert(comment);
    }

    @Override
    public void delete(Long commentId) {
        commentRepo.deleteById(commentId);
    }
}
