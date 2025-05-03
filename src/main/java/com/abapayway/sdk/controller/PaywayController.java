package com.abapayway.sdk.controller;
import com.abapayway.sdk.dto.request.*;
import com.abapayway.sdk.dto.response.CheckTransactionResponse;
import com.abapayway.sdk.service.PaymentService;

import com.fasterxml.jackson.databind.JsonNode;
import jakarta.validation.Valid;
import java.util.Map;

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

    @PostMapping("/check-transaction")
    public ResponseEntity<?> checkTransaction(@Valid @RequestBody CheckTransactionRequest checkTransactionRequest) throws Exception {
        try {
            CheckTransactionResponse response = paymentService.checkTransaction(checkTransactionRequest);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.status(400).body(Map.of(
                "error", "Transaction Error",
                "message", e.getMessage()
            ));
        }
    }

    @PostMapping("/list-transactions")
    public ResponseEntity<?> listTransactions(@Valid @RequestBody ListTransactionRequest listTransactionRequest) throws Exception {
        try{
            return ResponseEntity.ok(paymentService.listTransactions(listTransactionRequest));
        }catch(Exception e){
            return ResponseEntity.status(400).body(Map.of(
                "error", "Transaction Error",
                "message", e.getMessage()
            ));
        }
    }

    @PostMapping("/exchange-rate")
    public ResponseEntity<JsonNode> getExchangeRate(@Valid @RequestBody ExchangeRateRequest exchangeRateRequest) throws Exception {
        return ResponseEntity.ok(paymentService.getExchangeRate(exchangeRateRequest));
    }

}
