package com.abapayway.sdk.service;

import com.abapayway.sdk.config.PaywayProperties;
import com.abapayway.sdk.dto.request.*;
import com.abapayway.sdk.dto.response.CheckTransactionResponse;
import com.abapayway.sdk.dto.response.transaction.TransactionResponse;
import com.abapayway.sdk.util.SignatureUtil;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import kong.unirest.HttpResponse;
import kong.unirest.MultipartBody;
import kong.unirest.Unirest;
import kong.unirest.json.JSONObject;

import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.apache.commons.lang3.StringUtils.defaultString;

@Service
public class PaymentServiceImpl implements PaymentService {
    private final PaywayProperties props;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public PaymentServiceImpl(PaywayProperties props) {
        this.props = props;
    }

    @Override
    public Object createTransaction(PurchaseRequest request) throws Exception {
        String reqTime = defaultString(request.getReqTime());
        String merchantId = props.getMerchantId();
        String tranId = defaultString(request.getTranId());
        String amount = defaultString(request.getAmount());
        String items = defaultString(request.getItems());
        String shipping = defaultString(request.getShipping());
        String firstname = defaultString(request.getFirstname());
        String lastname = defaultString(request.getLastname());
        String email = defaultString(request.getEmail());
        String phone = defaultString(request.getPhone());
        String type = defaultString(request.getType());
        String paymentOption = defaultString(request.getPaymentOption());
        String returnUrl = defaultString(request.getReturnUrl());
        String cancelUrl = defaultString(request.getCancelUrl());
        String continueSuccessUrl = defaultString(request.getContinueSuccessUrl());
        String returnDeeplink = defaultString(request.getReturnDeeplink());
        String currency = defaultString(request.getCurrency());
        String customFields = defaultString(request.getCustomFields());
        String returnParams = defaultString(request.getReturnParams());
        String payout = defaultString(request.getPayout());
        String lifetime = defaultString(request.getLifetime());
        String additionalParams = defaultString(request.getAdditionalParams());
        String googlePayToken = defaultString(request.getGooglePayToken());

        String b4hash = reqTime + merchantId + tranId + amount + items + shipping +
                firstname + lastname + email + phone + type + paymentOption + returnUrl + cancelUrl +
                continueSuccessUrl + returnDeeplink + currency + customFields + returnParams + payout +
                lifetime + additionalParams + googlePayToken;

        String hash = SignatureUtil.generateHmacHash(b4hash, props.getApiKey());

        MultipartBody requestBody = Unirest.post("api/payment-gateway/v1/payments/purchase")
                .header("Content-Type", "multipart/form-data")
                .multiPartContent()
                .field("req_time", reqTime)
                .field("merchant_id", merchantId)
                .field("tran_id", tranId)
                .field("amount", amount)
                .field("hash", hash);

        if (items != null && !items.isEmpty()) requestBody.field("items", items);
        if (shipping != null && !shipping.isEmpty()) requestBody.field("shipping", shipping);
        if (firstname != null && !firstname.isEmpty()) requestBody.field("firstname", firstname);
        if (lastname != null && !lastname.isEmpty()) requestBody.field("lastname", lastname);
        if (email != null && !email.isEmpty()) requestBody.field("email", email);
        if (phone != null && !phone.isEmpty()) requestBody.field("phone", phone);
        if (type != null && !type.isEmpty()) requestBody.field("type", type);
        if (paymentOption != null && !paymentOption.isEmpty()) requestBody.field("payment_option", paymentOption);
        if (returnUrl != null && !returnUrl.isEmpty()) requestBody.field("return_url", returnUrl);
        if (cancelUrl != null && !cancelUrl.isEmpty()) requestBody.field("cancel_url", cancelUrl);
        if (continueSuccessUrl != null && !continueSuccessUrl.isEmpty())
            requestBody.field("continue_success_url", continueSuccessUrl);
        if (returnDeeplink != null && !returnDeeplink.isEmpty()) requestBody.field("return_deeplink", returnDeeplink);
        if (currency != null && !currency.isEmpty()) requestBody.field("currency", currency);
        if (customFields != null && !customFields.isEmpty()) requestBody.field("custom_fields", customFields);
        if (returnParams != null && !returnParams.isEmpty()) requestBody.field("return_params", returnParams);
        if (request.getViewType() != null && !request.getViewType().isEmpty())
            requestBody.field("view_type", request.getViewType());
        if (request.getPaymentGate() != null && !request.getPaymentGate().isEmpty())
            requestBody.field("payment_gate", request.getPaymentGate());
        if (payout != null && !payout.isEmpty()) requestBody.field("payout", payout);
        if (lifetime != null && !lifetime.isEmpty()) requestBody.field("lifetime", lifetime);
        if (additionalParams != null && !additionalParams.isEmpty())
            requestBody.field("additional_params", additionalParams);
        if (googlePayToken != null && !googlePayToken.isEmpty()) requestBody.field("google_pay_token", googlePayToken);

        HttpResponse<String> response = requestBody.asString();
        String contentType = response.getHeaders().getFirst("Content-Type");
        return contentType.equalsIgnoreCase("text/html; charset=utf-8")
                ? response.getHeaders().getFirst("Location")
                : objectMapper.readTree(response.getBody());
    }


    @Override
    public CheckTransactionResponse checkTransaction(CheckTransactionRequest checkTransactionRequest) throws Exception {
        String reqTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String merchantId = props.getMerchantId();
        String tranId = checkTransactionRequest.getTranId();
        String b4hash = reqTime + merchantId + tranId;
        String hash = SignatureUtil.generateHmacHash(b4hash, props.getApiKey());

        HttpResponse<String> response = Unirest.post("api/payment-gateway/v1/payments/check-transaction-2")
                .header("Content-Type", "application/json")
                .body(new JSONObject()
                        .put("req_time", reqTime)
                        .put("tran_id", tranId)
                        .put("merchant_id", merchantId)
                        .put("hash", hash)
                        .toString())
                .asString();
        ObjectMapper objectMapper = new ObjectMapper();
        CheckTransactionResponse responseObject = objectMapper.readValue(response.getBody(), CheckTransactionResponse.class);

        // Check the status code
        if (!"00".equals(responseObject.getStatus().getCode())) {
            throw new Exception("Error: " + responseObject.getStatus().getMessage() +
                    ", Transaction ID: " + responseObject.getStatus().getTranId());
        }

        return responseObject;
    }

    @Override
    public TransactionResponse listTransactions(ListTransactionRequest listTransactionRequest) throws Exception {
        String reqTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String merchantId = props.getMerchantId();
        String fromDate = listTransactionRequest.getFromDate() == null ? "" : listTransactionRequest.getFromDate();
        String toDate = listTransactionRequest.getToDate() == null ? "" : listTransactionRequest.getToDate();
        String fromAmount = listTransactionRequest.getFromAmount();
        String toAmount = listTransactionRequest.getToAmount();
        String status = listTransactionRequest.getStatus() == null ? "" : listTransactionRequest.getStatus();
        String page = listTransactionRequest.getPage();
        String pagination = listTransactionRequest.getPagination();

        String b4hash = reqTime + merchantId + fromDate + toDate + fromAmount + toAmount + status + page + pagination;
        String hash = SignatureUtil.generateHmacHash(b4hash, props.getApiKey());

        JSONObject body = new JSONObject();
        body.put("req_time", reqTime);
        body.put("merchant_id", merchantId);
        body.put("from_date", fromDate);
        body.put("to_date", toDate);
        body.put("from_amount", fromAmount);
        body.put("to_amount", toAmount);
        body.put("page", page);
        body.put("pagination", pagination);
        body.put("status", status);
        body.put("hash", hash);


        HttpResponse<String> response = Unirest.post("api/payment-gateway/v1/payments/transaction-list-2")
                .header("Content-Type", "application/json")
                .body(body.toString())
                .asString();

        ObjectMapper objectMapper = new ObjectMapper();
        TransactionResponse transactionResponse = objectMapper.readValue(response.getBody(), TransactionResponse.class);
        if (transactionResponse.getStatus().getCode().equals("00")) {
            return transactionResponse;
        } else {
            throw new Exception("Error from API: " + transactionResponse.getStatus().getMessage());
        }
    }

    @Override
    public JsonNode getExchangeRate(ExchangeRateRequest exchangeRateRequest) throws Exception {
        String reqTime = exchangeRateRequest.getReqTime();
        String merchantId = props.getMerchantId();
        String b4hash = reqTime + merchantId;
        String hash = SignatureUtil.generateHmacHash(b4hash, props.getApiKey());

        return sendJsonTo("api/payment-gateway/v1/exchange-rate", createJsonBody(
                "req_time", reqTime,
                "merchant_id", merchantId,
                "hash", hash
        ));
    }

    @Override
    public JsonNode closeTransaction(CloseTransactionRequest closeTransactionRequest) throws Exception {
        String reqTime = closeTransactionRequest.getReqTime();
        String merchantId = props.getMerchantId();
        String tranId = closeTransactionRequest.getTranId();
        String b4hash = reqTime + merchantId + tranId;
        String hash = SignatureUtil.generateHmacHash(b4hash, props.getApiKey());

        return sendJsonTo("api/payment-gateway/v1/payments/close-transaction", createJsonBody(
                "req_time", reqTime,
                "merchant_id", merchantId,
                "tran_id", tranId,
                "hash", hash
        ));
    }

    private JsonNode sendJsonTo(String endpoint, ObjectNode body) throws Exception {
        HttpResponse<String> response = Unirest.post(endpoint)
                .header("Content-Type", "application/json")
                .body(body.toString())
                .asString();
        return objectMapper.readTree(response.getBody());
    }

    private ObjectNode createJsonBody(Object... keyValuePairs) {
        ObjectNode body = objectMapper.createObjectNode();
        for (int i = 0; i < keyValuePairs.length; i += 2) {
            String key = (String) keyValuePairs[i];
            String value = (String) keyValuePairs[i + 1];
            body.put(key, value == null ? "" : value);
        }
        return body;
    }
}
