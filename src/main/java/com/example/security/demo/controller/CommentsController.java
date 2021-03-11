package com.example.security.demo.controller;

import com.example.security.demo.messaging.CommentMQSender;
import com.example.security.demo.model.dto.CommentCreateRequest;
import com.example.security.demo.model.dto.CommentTransfer;
import com.example.security.demo.service.CommentsService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
public class CommentsController {

    @Autowired
    CommentsService commentsService;

    @Autowired
    CommentMQSender commentMQSender;

    @PostMapping("/api/comments")
    @ResponseBody
    // TODO может быть Authentication authentication стоит спустить внутрь сервиса и сделать автоваре
    public CommentTransfer create(
        @Valid @RequestBody CommentCreateRequest comment,
        Authentication authentication) {
        return commentsService.create(comment, authentication);
    }

    @DeleteMapping("/api/comments/{id}")
    public void delete(@PathVariable Long id) {
        commentMQSender.sendToDeleteComment(id);
    }


}
