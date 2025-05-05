package com.abapayway.sdk.service;

import com.abapayway.sdk.dto.request.CheckTransactionRequest;
import com.abapayway.sdk.dto.request.ExchangeRateRequest;
import com.abapayway.sdk.dto.request.ListTransactionRequest;
import com.abapayway.sdk.dto.request.PurchaseRequest;
import com.abapayway.sdk.dto.request.RefundTransactionRequest;
import com.abapayway.sdk.dto.request.*;
import com.abapayway.sdk.dto.response.CheckTransactionResponse;
import com.abapayway.sdk.dto.response.transaction.TransactionResponse;
import com.fasterxml.jackson.databind.JsonNode;;

public interface PaymentService {
    Object createTransaction(PurchaseRequest purchaseRequest) throws Exception;
    CheckTransactionResponse checkTransaction(CheckTransactionRequest checkTransactionRequest) throws Exception;
    TransactionResponse listTransactions(ListTransactionRequest listTransactionRequest) throws Exception;
    JsonNode getExchangeRate(ExchangeRateRequest exchangeRateRequest) throws Exception;
    JsonNode refundTransaction(RefundTransactionRequest refundTransactionRequest) throws Exception;
    JsonNode closeTransaction(CloseTransactionRequest closeTransactionRequest) throws Exception;
}
