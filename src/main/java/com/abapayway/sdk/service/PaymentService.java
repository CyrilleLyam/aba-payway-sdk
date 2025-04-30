package com.abapayway.sdk.service;

import com.abapayway.sdk.dto.request.PurchaseRequest;
import com.abapayway.sdk.dto.response.PaywayResponse;

public interface PaymentService {
    PaywayResponse createTransaction(PurchaseRequest purchaseRequest) throws Exception;
}
