package com.abapayway.sdk.service;

import com.abapayway.sdk.dto.request.ExchangeRateRequest;
import com.abapayway.sdk.dto.request.PurchaseRequest;
import com.fasterxml.jackson.databind.JsonNode;

public interface PaymentService {
    Object createTransaction(PurchaseRequest purchaseRequest) throws Exception;

    JsonNode getExchangeRate(ExchangeRateRequest exchangeRateRequest) throws Exception;
}
