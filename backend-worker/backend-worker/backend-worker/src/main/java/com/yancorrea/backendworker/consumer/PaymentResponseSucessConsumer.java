package com.yancorrea.backendworker.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import com.yancorrea.backendworker.facade.PaymentFacade;

@Service
public class PaymentResponseSucessConsumer {
    @Autowired
    private PaymentFacade paymentFacade;

    @RabbitListener(queues = { "payment-response-sucess-queue" })
    public void receive(@Payload Message message) {
        String payload = String.valueOf(message.getPayload());
        paymentFacade.sucessPayment(payload);
    }
}
