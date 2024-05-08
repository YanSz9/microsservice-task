package com.yancorrea.backendworker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yancorrea.backendworker.dto.PaymentDto;
import com.yancorrea.backendworker.facade.PaymentFacade;

@RestController
@RequestMapping("/pagamentos")
public class PaymentController {
    @Autowired
    private PaymentFacade paymentFacade;

    @PostMapping
    public String processar(@RequestBody PaymentDto request) {
        return paymentFacade.solicitarPagamento(request);
    }

}
