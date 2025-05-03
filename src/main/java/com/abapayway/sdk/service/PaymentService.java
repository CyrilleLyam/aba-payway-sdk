package com.abapayway.sdk.service;

import com.abapayway.sdk.dto.request.ExchangeRateRequest;
import com.abapayway.sdk.dto.request.PurchaseRequest;

public interface PaymentService {
    String createTransaction(PurchaseRequest purchaseRequest) throws Exception;

    String getExchangeRate(ExchangeRateRequest exchangeRateRequest) throws Exception;
}
