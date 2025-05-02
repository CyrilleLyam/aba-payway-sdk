package com.abapayway.sdk.service;

import com.abapayway.sdk.config.PaywayProperties;
import com.abapayway.sdk.dto.request.PurchaseRequest;
import com.abapayway.sdk.util.SignatureUtil;
import kong.unirest.HttpResponse;
import kong.unirest.MultipartBody;
import kong.unirest.Unirest;
import org.springframework.stereotype.Service;
import static org.apache.commons.lang3.StringUtils.defaultString;

@Service
public class PaymentServiceImpl implements PaymentService {
    private final PaywayProperties props;

    public PaymentServiceImpl(PaywayProperties props) {
        this.props = props;
    }

    @Override
    public String createTransaction(PurchaseRequest request) throws Exception {
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
        return contentType.equalsIgnoreCase("text/html; charset=utf-8")
                ? response.getHeaders().getFirst("Location")
                : response.getBody();
    }
}
