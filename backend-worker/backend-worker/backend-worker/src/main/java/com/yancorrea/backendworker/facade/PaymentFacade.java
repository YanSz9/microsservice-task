package com.yancorrea.backendworker.facade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.yancorrea.backendworker.dto.PaymentDto;
import com.yancorrea.backendworker.producer.PaymentRequestProducer;

@Service
public class PaymentFacade {
    @Autowired
    private PaymentRequestProducer producer;

    public String solicitarPagamento(PaymentDto request) {
        try {
            producer.integrar(request);
        } catch (JsonProcessingException e) {
            return "Erro ao solicitar pagamento..." + e.getMessage();
        }
        return "Pagamento aguardando confirmação...";
    }

    public void errorPayment(String payload) {
        System.err.println("Erro ===" + payload);
    }

    public void sucessPayment(String payload) {
        System.out.println("Sucesso ===" + payload);
    }

}
