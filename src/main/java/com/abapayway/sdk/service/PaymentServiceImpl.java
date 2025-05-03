package com.abapayway.sdk.service;

import com.abapayway.sdk.config.PaywayProperties;
import com.abapayway.sdk.dto.request.CheckTransactionRequest;
import com.abapayway.sdk.dto.request.CofRequest;
import com.abapayway.sdk.dto.request.ListTransactionRequest;
import com.abapayway.sdk.dto.request.PurchaseRequest;
import com.abapayway.sdk.dto.request.PurchaseTokenRequest;
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
import com.fasterxml.jackson.databind.ObjectMapper;

import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Map;

import java.util.UUID;

@Service
public class PaymentServiceImpl implements PaymentService {
    private final PaywayProperties props;

    public PaymentServiceImpl(PaywayProperties props) {
        this.props = props;
    }

    @Override
    public String createTransaction(PurchaseRequest request) throws Exception {
        String reqTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String tranId = request.getTranId();
        String amount = request.getAmount();
        String paymentOption = request.getPaymentOption();
        String returnUrl = request.getReturnUrl();
        String continueUrlSuccess = request.getContinueSuccessUrl();
        String returnParams = "ABA_2025";
        String b4hash = reqTime + props.getMerchantId() + tranId + amount + paymentOption+ returnUrl+continueUrlSuccess+returnParams;
        String hash = SignatureUtil.generateHmacHash(b4hash, props.getApiKey());

        MultipartBody requestBody = Unirest.post("api/payment-gateway/v1/payments/purchase")
                .header("Content-Type", "multipart/form-data")
                .multiPartContent()
                .field("req_time", reqTime)
                .field("merchant_id", merchantId)
                .field("tran_id", tranId)
                .field("amount", amount)
                .field("payment_option", paymentOption)
                .field("return_url", returnUrl)
                .field("continue_success_url", continueUrlSuccess)
                .field("return_params", returnParams)
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
        if (continueSuccessUrl != null && !continueSuccessUrl.isEmpty()) requestBody.field("continue_success_url", continueSuccessUrl);
        if (returnDeeplink != null && !returnDeeplink.isEmpty()) requestBody.field("return_deeplink", returnDeeplink);
        if (currency != null && !currency.isEmpty()) requestBody.field("currency", currency);
        if (customFields != null && !customFields.isEmpty()) requestBody.field("custom_fields", customFields);
        if (returnParams != null && !returnParams.isEmpty()) requestBody.field("return_params", returnParams);
        if (request.getViewType() != null && !request.getViewType().isEmpty()) requestBody.field("view_type", request.getViewType());
        if (request.getPaymentGate() != null && !request.getPaymentGate().isEmpty()) requestBody.field("payment_gate", request.getPaymentGate());
        if (payout != null && !payout.isEmpty()) requestBody.field("payout", payout);
        if (lifetime != null && !lifetime.isEmpty()) requestBody.field("lifetime", lifetime);
        if (additionalParams != null && !additionalParams.isEmpty()) requestBody.field("additional_params", additionalParams);
        if (googlePayToken != null && !googlePayToken.isEmpty()) requestBody.field("google_pay_token", googlePayToken);

        HttpResponse<String> response = requestBody.asString();
        String contentType = response.getHeaders().getFirst("Content-Type");
        if ("text/html; charset=utf-8".equalsIgnoreCase(contentType)) {
            return response.getHeaders().getFirst("Location");
        }
        return response.getBody();
    }

    @Override
    public CheckTransactionResponse checkTransaction(CheckTransactionRequest checkTransactionRequest) throws Exception{
        String reqTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String merchantId = props.getMerchantId();
        String tranId = checkTransactionRequest.getTranId();
        String b4hash = reqTime+merchantId+tranId;
        String hash =  SignatureUtil.generateHmacHash(b4hash, props.getApiKey());

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
    public TransactionResponse listTransaction(ListTransactionRequest listTransactionRequest) throws Exception{
        String reqTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String merchantId = props.getMerchantId();
        String fromDate = listTransactionRequest.getFromDate() == null ? "": listTransactionRequest.getFromDate();
        String toDate = listTransactionRequest.getToDate() == null ? "" : listTransactionRequest.getToDate();
        String fromAmount = listTransactionRequest.getFromAmount();
        String toAmount = listTransactionRequest.getToAmount();
        String status = listTransactionRequest.getStatus() == null ? "" : listTransactionRequest.getStatus();
        String page = listTransactionRequest.getPage();
        String pagination = listTransactionRequest.getPagination();
        
        String b4hash = reqTime+merchantId+fromDate+toDate+fromAmount+toAmount+status+page+pagination;
        String hash =  SignatureUtil.generateHmacHash(b4hash, props.getApiKey());
        
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
    public void accountDetails() throws Exception{
        String reqTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String merchantId = props.getMerchantId();
        
        String timestamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()); // e.g., 20250503113045
        String uuidPart = UUID.randomUUID().toString().replace("-", "").substring(0, 12); // 12-char unique

        String returnParam =timestamp + uuidPart;
        
        String b4hash = merchantId+reqTime+returnParam;
        String hash =  SignatureUtil.generateHmacHash(b4hash, props.getApiKey());
        
        JSONObject body = new JSONObject();
        body.put("req_time", reqTime);
        body.put("merchant_id", merchantId);
        body.put("return_param", returnParam);
        body.put("hash", hash);

        
        HttpResponse<String> response = Unirest.post("api/aof/pushback-status")
        .header("Content-Type", "application/json")
        .body(body.toString())
        .asString();

        System.out.println("\n=== API Response ===");
        System.out.println("HTTP Status Code   : " + response.getStatus());
        System.out.println("Response Body      : " + response.getBody());

    }

    @Override
    public String createCof(CofRequest request) throws Exception {
        String ctid = UUID.randomUUID().toString().replace("-", "").substring(0, 20);
        String merchantId = props.getMerchantId();
        String returnParam = request.getReturnParam();
        String returnUrl = request.getReturnUrl();
        String continueAddCardSuccess = request.getContinueAddCardSuccessUrl();
        String b4hash = merchantId + ctid + returnParam;
        String hash = SignatureUtil.generateHmacHash(b4hash, props.getApiKey());
        
        Unirest.config().followRedirects(true);
        MultipartBody requestBody = Unirest.post("api/payment-gateway/v1/cof/initial")
                .header("Content-Type", "multipart/form-data")
                .multiPartContent()
                .field("merchant_id", merchantId)
                .field("ctid", ctid) 
                .field("return_param", returnParam) 
                .field("return_url", returnUrl) 
                .field("continue_add_card_success_url", continueAddCardSuccess) 
                .field("hash", hash);

        HttpResponse<String> response = requestBody.asString();
        String contentType = response.getHeaders().getFirst("Content-Type");
        System.out.println(response.getHeaders());
        if (response.getStatus() == 302 || response.getStatus() == 301) {
            return response.getHeaders().getFirst("Location");
        }
        if ("text/html; charset=utf-8".equalsIgnoreCase(contentType)) {
            System.out.println("Redirect URL: " + response.getHeaders().getFirst("Location")); // Debugging line
            return response.getHeaders().getFirst("Location");
        }

        return response.getBody();
    }

    @Override
    public String processPayment(PurchaseTokenRequest request) throws Exception{
        int amount = request.getAmount();
        String type = request.getType();
        String reqTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String tranId = "tranId"+reqTime;
        //String continueSuccessUrl = request.getContinueSuccessUrl();
        String returnUrl = request.getReturnUrl();
        String returnParam = request.getReturnParam();
        // String firstName = request.getFirstname();
        // String lastName = request.getLastname();

        List<Map<String, Object>> items = List.of(
            Map.of("name", "product 1", "quantity", 1, "price", 1.00),
            Map.of("name", "product 2", "quantity", 2, "price", 4.00)
        );

        // Convert the list to JSON
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(items);

        // Encode the JSON string to Base64
        String base64EncodedItems = Base64.getEncoder().encodeToString(json.getBytes());


        String b4hash = reqTime+props.getMerchantId()+tranId+amount+base64EncodedItems+type+returnUrl+returnParam;
        String hash = SignatureUtil.generateHmacHash(b4hash, props.getApiKey());
        
        HttpResponse<String> response = Unirest.post("api/payment-gateway/v1/payments/purchase")
        .header("Content-Type", "application/json")
        .body(new JSONObject()
                .put("req_time", reqTime)
                .put("merchant_id", props.getMerchantId())
                .put("tran_id", tranId)
                .put("amount", amount)
                .put("items", base64EncodedItems)
                .put("type", type)
                .put("return_url", returnUrl)
                .put("return_param", returnParam)
                .put("hash", hash)
                .toString())
        .asString();
        
        String contentType = response.getHeaders().getFirst("Content-Type");
        if ("text/html; charset=utf-8".equalsIgnoreCase(contentType)) {
            return response.getHeaders().getFirst("Location");
        }
        //System.out.println("RES123"+response);

        return response.getBody();
    }


}
