package com.example.security.demo.messaging;

import com.example.security.demo.config.RabbitConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentMQSender {
  private final RabbitTemplate rabbitTemplate;

  @Autowired
  public CommentMQSender(RabbitTemplate rabbitTemplate) {
    this.rabbitTemplate = rabbitTemplate;
  }

  public void sendToDeleteComment(Long commentId) {
    this.rabbitTemplate.convertAndSend(RabbitConfig.QUEUE_COMMENTS, commentId);
  }
}