package com.abapayway.sdk.controller;

import com.abapayway.sdk.dto.request.ExchangeRateRequest;
import com.abapayway.sdk.dto.request.PurchaseRequest;
import com.abapayway.sdk.service.PaymentService;
import com.fasterxml.jackson.databind.JsonNode;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class PaywayController {

    private final PaymentService paymentService;

    public PaywayController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/create-transaction")
    public ResponseEntity<Object> createTransaction(@Valid @RequestBody PurchaseRequest purchaseRequest) throws Exception {
        return ResponseEntity.ok(paymentService.createTransaction(purchaseRequest));
    }

    @PostMapping("/exchange-rate")
    public ResponseEntity<JsonNode> getExchangeRate(@Valid @RequestBody ExchangeRateRequest exchangeRateRequest) throws Exception {
        return ResponseEntity.ok(paymentService.getExchangeRate(exchangeRateRequest));
    }
}
