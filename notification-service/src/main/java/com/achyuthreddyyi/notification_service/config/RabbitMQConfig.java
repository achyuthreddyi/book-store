package com.achyuthreddyyi.notification_service.config;

import com.achyuthreddyyi.notification_service.ApplicationProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class RabbitMQConfig {
    private static final Logger logger = LoggerFactory.getLogger(RabbitMQConfig.class);
    private final ApplicationProperties properties;

    RabbitMQConfig(ApplicationProperties properties) {
        this.properties = properties;
        logger.info("Initializing RabbitMQConfig with properties: {}", properties);
    }

    @Bean
    DirectExchange exchange() {
        logger.info("Creating DirectExchange: {}", properties.orderEventsExchange());
        return new DirectExchange(properties.orderEventsExchange());
    }

    @Bean
    Queue newOrdersQueue() {
        logger.info("Creating newOrdersQueue: {}", properties.newOrdersQueue());
        return QueueBuilder.durable(properties.newOrdersQueue()).build();
    }

    @Bean
    Binding newOrdersQueueBinding() {
        logger.info("Creating binding for newOrdersQueue to exchange: {}", properties.newOrdersQueue());
        return BindingBuilder.bind(newOrdersQueue()).to(exchange()).with(properties.newOrdersQueue());
    }

    @Bean
    Queue deliveredOrdersQueue() {
        logger.info("Creating deliveredOrdersQueue: {}", properties.deliveredOrdersQueue());
        return QueueBuilder.durable(properties.deliveredOrdersQueue()).build();
    }

    @Bean
    Binding deliveredOrdersQueueBinding() {
        logger.info("Creating binding for deliveredOrdersQueue to exchange: {}", properties.deliveredOrdersQueue());
        return BindingBuilder.bind(deliveredOrdersQueue()).to(exchange()).with(properties.deliveredOrdersQueue());
    }

    @Bean
    Queue cancelledOrdersQueue() {
        logger.info("Creating cancelledOrdersQueue: {}", properties.cancelledOrdersQueue());
        return QueueBuilder.durable(properties.cancelledOrdersQueue()).build();
    }

    @Bean
    Binding cancelledOrdersQueueBinding() {
        logger.info("Creating binding for cancelledOrdersQueue to exchange: {}", properties.cancelledOrdersQueue());
        return BindingBuilder.bind(cancelledOrdersQueue()).to(exchange()).with(properties.cancelledOrdersQueue());
    }

    @Bean
    Queue errorOrdersQueue() {
        logger.info("Creating errorOrdersQueue: {}", properties.errorOrdersQueue());
        return QueueBuilder.durable(properties.errorOrdersQueue()).build();
    }

    @Bean
    Binding errorOrdersQueueBinding() {
        logger.info("Creating binding for errorOrdersQueue to exchange: {}", properties.errorOrdersQueue());
        return BindingBuilder.bind(errorOrdersQueue()).to(exchange()).with(properties.errorOrdersQueue());
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, ObjectMapper objectMapper) {
        logger.info("Configuring RabbitTemplate with ConnectionFactory and ObjectMapper");
        final var rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jacksonConverter(objectMapper));
        return rabbitTemplate;
    }

    @Bean
    public Jackson2JsonMessageConverter jacksonConverter(ObjectMapper mapper) {
        logger.info("Creating Jackson2JsonMessageConverter with ObjectMapper");
        return new Jackson2JsonMessageConverter(mapper);
    }
}
