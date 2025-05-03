package com.abapayway.sdk.service;

import com.abapayway.sdk.config.PaywayProperties;
import com.abapayway.sdk.dto.request.*;
import com.abapayway.sdk.dto.response.CheckTransactionResponse;
import com.abapayway.sdk.dto.response.transaction.TransactionResponse;
import com.abapayway.sdk.util.MerchantAuthUtil;
import com.abapayway.sdk.util.RSAUtil;
import com.abapayway.sdk.util.SignatureUtil;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import kong.unirest.HttpResponse;
import kong.unirest.MultipartBody;
import kong.unirest.Unirest;
import kong.unirest.json.JSONObject;

import org.springframework.stereotype.Service;

import java.security.PublicKey;
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
        String reqTime = checkTransactionRequest.getReqTime();
        String merchantId = props.getMerchantId();
        String tranId = checkTransactionRequest.getTranId();
        String b4hash = reqTime + merchantId + tranId;
        String hash = SignatureUtil.generateHmacHash(b4hash, props.getApiKey());

        ObjectNode body = createJsonBody(
                "req_time", reqTime,
                "merchant_id", merchantId,
                "tran_id", tranId,
                "hash", hash
        );

        JsonNode jsonResponse = sendJsonTo("api/payment-gateway/v1/payments/check-transaction-2", body);
        return objectMapper.treeToValue(jsonResponse, CheckTransactionResponse.class);
    }

    @Override
    public TransactionResponse listTransactions(ListTransactionRequest listTransactionRequest) throws Exception {
        String reqTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String merchantId = props.getMerchantId();

        String fromDate = defaultString(listTransactionRequest.getFromDate());
        String toDate = defaultString(listTransactionRequest.getToDate());
        String fromAmount = defaultString(listTransactionRequest.getFromAmount());
        String toAmount = defaultString(listTransactionRequest.getToAmount());
        String status = defaultString(listTransactionRequest.getStatus());
        String page = defaultString(listTransactionRequest.getPage());
        String pagination = defaultString(listTransactionRequest.getPagination());

        String b4hash = reqTime + merchantId + fromDate + toDate + fromAmount + toAmount + status + page + pagination;
        String hash = SignatureUtil.generateHmacHash(b4hash, props.getApiKey());

        ObjectNode body = createJsonBody(
                "req_time", reqTime,
                "merchant_id", merchantId,
                "from_date", fromDate,
                "to_date", toDate,
                "from_amount", fromAmount,
                "to_amount", toAmount,
                "page", page,
                "pagination", pagination,
                "status", status,
                "hash", hash
        );

        JsonNode jsonResponse = sendJsonTo("api/payment-gateway/v1/payments/transaction-list-2", body);
        TransactionResponse response = objectMapper.treeToValue(jsonResponse, TransactionResponse.class);

        if (!"00".equals(response.getStatus().getCode())) {
            throw new Exception("Error from API: " + response.getStatus().getMessage());
        }

        return response;
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

    @Override
    public JsonNode refundTransaction(RefundTransactionRequest refundTransactionRequest) throws Exception{
        String reqTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String merchantId = props.getMerchantId();
        String amount = refundTransactionRequest.getAmount();
        String tranId = refundTransactionRequest.getTranId();
        
        PublicKey rsaPublicKey = RSAUtil.loadPublicKey(props.getPublicKey());
        String merchantAuth = MerchantAuthUtil.encryptMerchantAuth(merchantId, tranId, amount, rsaPublicKey);
        String b4hash = reqTime+merchantId+merchantAuth;
        String hash = SignatureUtil.generateHmacHash(b4hash, props.getApiKey());

        return sendJsonTo("api/merchant-portal/merchant-access/online-transaction/refund", createJsonBody(
            "request_time", reqTime,
            "merchant_id", merchantId,
            "merchant_auth", merchantAuth,
            "hash",hash
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
