package com.abapayway.sdk.controller;

import com.abapayway.sdk.dto.request.PurchaseRequest;
import com.abapayway.sdk.dto.response.PaywayResponse;
import com.abapayway.sdk.service.PaymentService;
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

    @PostMapping(value = "/create-transaction", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<PaywayResponse> createTransaction(@ModelAttribute PurchaseRequest purchaseRequest) throws Exception {
        return ResponseEntity.ok(paymentService.createTransaction(purchaseRequest));
    }
}
