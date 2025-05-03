package com.abapayway.sdk.service;

import com.abapayway.sdk.dto.request.CheckTransactionRequest;
import com.abapayway.sdk.dto.request.ListTransactionRequest;
import com.abapayway.sdk.dto.request.PurchaseRequest;
import com.abapayway.sdk.dto.response.CheckTransactionResponse;
import com.abapayway.sdk.dto.response.transaction.TransactionResponse;;

public interface PaymentService {
    String createTransaction(PurchaseRequest purchaseRequest) throws Exception;
    CheckTransactionResponse checkTransaction(CheckTransactionRequest checkTransactionRequest) throws Exception;
    TransactionResponse listTransactions(ListTransactionRequest listTransactionRequest) throws Exception;
}
