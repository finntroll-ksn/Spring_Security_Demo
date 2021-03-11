package com.example.security.demo.messaging;

import com.example.security.demo.config.RabbitConfig;
import com.example.security.demo.service.impl.CommentServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommentMQListener {
  static final Logger logger = LoggerFactory.getLogger(CommentMQListener.class);

  @Autowired
  CommentServiceImpl commentService;

  @RabbitListener(queues = RabbitConfig.QUEUE_COMMENTS)
  public void process(Long commentId){
    logger.info("Delete comment");
    logger.info(commentId.toString());
    commentService.delete(commentId);
  }

}