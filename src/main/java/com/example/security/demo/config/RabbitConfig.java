package com.example.security.demo.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    public static final String QUEUE_COMMENTS = "comments-queue";
    public static final String EXCHANGE_COMMENTS = "comments-exchange";

    private static final Integer TTL_TIME = 15000;
    private static final String QUEUE_DEAD_COMMENTS = "comments-dead";

    @Bean
    Queue ordersQueue() {
          return QueueBuilder
              .durable(QUEUE_COMMENTS)
              .withArgument("x-dead-letter-exchange", "")
              .withArgument("x-dead-letter-routing-key", QUEUE_DEAD_COMMENTS)
              .withArgument("x-message-ttl", TTL_TIME)
              .build();
    }

    @Bean
    Queue deadLetterQueue() {
      return QueueBuilder.durable(QUEUE_DEAD_COMMENTS).build();
    }

    @Bean
    Exchange ordersExchange() {
      return ExchangeBuilder.topicExchange(EXCHANGE_COMMENTS).build();
    }

    @Bean
    Binding binding(Queue ordersQueue, TopicExchange ordersExchange) {
      return BindingBuilder.bind(ordersQueue).to(ordersExchange).with(QUEUE_COMMENTS);
    }
}

