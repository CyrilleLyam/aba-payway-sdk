package com.abapayway.sdk.service;

import com.abapayway.sdk.dto.request.CheckTransactionRequest;
import com.abapayway.sdk.dto.request.CofRequest;
import com.abapayway.sdk.dto.request.ListTransactionRequest;
import com.abapayway.sdk.dto.request.PurchaseRequest;
import com.abapayway.sdk.dto.request.PurchaseTokenRequest;
import com.abapayway.sdk.dto.response.CheckTransactionResponse;
import com.abapayway.sdk.dto.response.transaction.TransactionResponse;;

public interface PaymentService {
    String createTransaction(PurchaseRequest purchaseRequest) throws Exception;
    CheckTransactionResponse checkTransaction(CheckTransactionRequest checkTransactionRequest) throws Exception;
    TransactionResponse listTransaction(ListTransactionRequest listTransactionRequest) throws Exception;
    void accountDetails() throws Exception;
    String createCof(CofRequest cofRequest) throws Exception;
    String processPayment(PurchaseTokenRequest puschaseTokenRequest) throws Exception; 
}
