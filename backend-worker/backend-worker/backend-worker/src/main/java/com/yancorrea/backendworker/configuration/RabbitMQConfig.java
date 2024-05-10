package com.yancorrea.backendworker.configuration;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${spring.rabbitmq.host}")
    private String host;
    @Value("${spring.rabbitmq.port}")
    private String port;
    @Value("${spring.rabbitmq.username}")
    private String username;
    @Value("${spring.rabbitmq.password}")
    private String password;

    @Bean
    public Queue paymentRequestQueue() {
        return new Queue("payment-request-queue", true);
    }

    @Bean
    public Queue paymentResponseErrorQueue() {
        return new Queue("payment-response-error-queue", true);
    }

    @Bean
    public Queue paymentResponseSucessQueue() {
        return new Queue("payment-response-sucess-queue", true);
    }

    @Bean
    public TopicExchange paymentRequestExchange() {
        return new TopicExchange("payment-request-exchange");
    }

    @Bean
    public TopicExchange paymentResponseErrorExchange() {
        return new TopicExchange("payment-response-error-exchange");
    }

    @Bean
    public TopicExchange paymentResponseSucessExchange() {
        return new TopicExchange("payment-response-sucess-exchange");
    }

    @Bean
    public RabbitAdmin createRabbitAdmin(ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }

    @Bean
    public Binding paymentRequestBinding(Queue paymentRequestQueue, TopicExchange paymentRequestExchange) {
        return BindingBuilder.bind(paymentRequestQueue).to(paymentRequestExchange)
                .with("payment-request-routing-key");
    }

    @Bean
    public Binding paymentResponseErrorBinding(Queue paymentResponseErrorQueue,
            TopicExchange paymentResponseErrorExchange) {
        return BindingBuilder.bind(paymentResponseErrorQueue).to(paymentResponseErrorExchange)
                .with("payment-response-error-routing-key");
    }

    @Bean
    public Binding paymentResponseSuccessBinding(Queue paymentResponseSucessQueue,
            TopicExchange paymentResponseSucessExchange) {
        return BindingBuilder.bind(paymentResponseSucessQueue).to(paymentResponseSucessExchange)
                .with("payment-response-sucess-routing-key");
    }

    @Bean
    public ApplicationListener<ApplicationReadyEvent> initializeAdmin(RabbitAdmin rabbitAdmin) {
        return event -> rabbitAdmin.initialize();
    }

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        connectionFactory.setHost(host);
        connectionFactory.setPort(Integer.parseInt(port));
        return connectionFactory;
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setConnectionFactory(connectionFactory);

        return rabbitTemplate;
    }
}