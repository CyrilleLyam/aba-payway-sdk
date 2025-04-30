package com.abapayway.sdk.service;

import com.abapayway.sdk.config.PaywayProperties;
import com.abapayway.sdk.dto.request.PurchaseRequest;
import com.abapayway.sdk.util.SignatureUtil;
import kong.unirest.HttpResponse;
import kong.unirest.MultipartBody;
import kong.unirest.Unirest;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class PaymentServiceImpl implements PaymentService {
    private final PaywayProperties props;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public PaymentServiceImpl(PaywayProperties props) {
        this.props = props;
    }

    @Override
    public String createTransaction(PurchaseRequest request) throws Exception {
        String reqTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String tranId = request.getTranId();
        String amount = request.getAmount();
        String paymentOption = request.getPaymentOption();
        String b4hash = reqTime + props.getMerchantId() + tranId + amount + paymentOption;
        String hash = SignatureUtil.generateHmacHash(b4hash, props.getApiKey());

        MultipartBody requestBody = Unirest.post("api/payment-gateway/v1/payments/purchase")
                .header("Content-Type", "multipart/form-data")
                .multiPartContent()
                .field("req_time", reqTime)
                .field("merchant_id", props.getMerchantId())
                .field("tran_id", tranId)
                .field("amount", amount)
                .field("payment_option", paymentOption)
                .field("hash", hash);

        if (request.getViewType() != null) {
            requestBody.field("view_type", request.getViewType());
        }

        if(request.getPaymentGate() !=null){
            requestBody.field("payment_gate", request.getPaymentGate());
        }

        HttpResponse<String> response = requestBody.asString();
        String contentType = response.getHeaders().getFirst("Content-Type");
        if ("text/html; charset=utf-8".equalsIgnoreCase(contentType)) {
            return response.getHeaders().getFirst("Location");
        }

        return response.getBody();
    }
}
