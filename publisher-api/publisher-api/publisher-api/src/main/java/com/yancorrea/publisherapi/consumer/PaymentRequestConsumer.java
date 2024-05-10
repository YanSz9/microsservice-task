package com.yancorrea.publisherapi.consumer;

import java.util.Random;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import com.yancorrea.publisherapi.producer.PaymentErrorProducer;
import com.yancorrea.publisherapi.producer.PaymentSuccessProducer;

@Component
public class PaymentRequestConsumer {
    @Autowired
    private PaymentErrorProducer errorProducer;
    @Autowired
    private PaymentSuccessProducer sucessProducer;

    @RabbitListener(queues = "payment-request-queue")
    public void receiveMessage(Message<String> message) {
        String messageBody = message.getPayload();
        System.out.println(messageBody);
        if (new Random().nextBoolean()) {
            sucessProducer.generateResponse("Pagamento realizado com sucesso, informações do pedido:" + message);
        } else
            errorProducer.generateResponse("Erro ao processar pagamento, informações do pedido:" + message);
    }
}
