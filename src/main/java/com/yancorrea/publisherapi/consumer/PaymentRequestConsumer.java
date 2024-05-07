package com.yancorrea.publisherapi.consumer;

import java.util.Random;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.yancorrea.publisherapi.producer.PaymentErrorProducer;
import com.yancorrea.publisherapi.producer.PaymentSuccessProducer;

@Component
public class PaymentRequestConsumer {
    @Autowired
    private PaymentErrorProducer errorProducer;
    @Autowired
    private PaymentSuccessProducer sucessProducer;

    @RabbitListener(queues = { "payment-request-queue" })
    public void receiveMessage(@Payload Message message) {
        System.out.println(message);
        if (new Random().nextBoolean()) {
            sucessProducer.generateResponse("Mensagem de sucesso pagamento" + message);
        } else
            errorProducer.generateResponse("Erro no pagamento" + message);
    }
}
