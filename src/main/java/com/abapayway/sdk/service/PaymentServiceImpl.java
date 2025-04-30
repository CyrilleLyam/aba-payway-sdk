package com.abapayway.sdk.service;

import com.abapayway.sdk.config.PaywayProperties;
import com.abapayway.sdk.dto.request.PurchaseRequest;
import com.abapayway.sdk.dto.response.PaywayResponse;
import com.abapayway.sdk.util.SignatureUtil;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class PaymentServiceImpl implements PaymentService {
    private static final Logger logger = LoggerFactory.getLogger(PaymentServiceImpl.class);
    private final PaywayProperties props;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public PaymentServiceImpl(PaywayProperties props) {
        this.props = props;
    }

    @Override
    public PaywayResponse createTransaction(PurchaseRequest request) throws Exception {
        String reqTime = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String tranId = "tran" + System.currentTimeMillis();
        String amount = request.getAmount();
        String b4hash = reqTime + props.getMerchantId() + tranId + amount;
        String hash = SignatureUtil.generateHmacHash(b4hash, props.getApiKey());

        HttpResponse<String> response = Unirest.post("api/payment-gateway/v1/payments/purchase")
                .header("Content-Type", "multipart/form-data")
                .multiPartContent()
                .field("req_time", reqTime)
                .field("merchant_id", props.getMerchantId())
                .field("tran_id", tranId)
                .field("amount", amount)
                .field("hash", hash)
                .asString();

        return objectMapper.readValue(response.getBody(), PaywayResponse.class);
    }
}
