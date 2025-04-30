package com.abapayway.sdk.controller;

import com.abapayway.sdk.dto.request.PurchaseRequest;
import com.abapayway.sdk.dto.response.PaywayResponse;
import com.abapayway.sdk.service.PaymentService;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payway")
public class PaywayController {

    private final PaymentService paymentService;

    public PaywayController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/create-transaction")
    public ResponseEntity<String> createTransaction(@Valid @RequestBody PurchaseRequest purchaseRequest) throws Exception {
        return ResponseEntity.ok(paymentService.createTransaction(purchaseRequest));
    }
}
