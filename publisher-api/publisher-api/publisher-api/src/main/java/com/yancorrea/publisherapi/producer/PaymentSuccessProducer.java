package com.yancorrea.publisherapi.producer;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentSuccessProducer {
    @Autowired
    private AmqpTemplate amqpTemplate;

    public void generateResponse(String message) {
        amqpTemplate.convertAndSend(
                "payment-response-sucess-exchange",
                "payment-response-sucess-routing-key",
                message);
    }
}
