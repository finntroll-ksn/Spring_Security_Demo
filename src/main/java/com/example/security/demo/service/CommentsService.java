package com.example.security.demo.service;

import com.example.security.demo.model.dto.CommentCreateRequest;
import com.example.security.demo.model.dto.CommentTransfer;
import org.springframework.security.core.Authentication;

public interface CommentsService {

    CommentTransfer create(CommentCreateRequest comment, Authentication authentication);

    void delete(Long commentId);

}
