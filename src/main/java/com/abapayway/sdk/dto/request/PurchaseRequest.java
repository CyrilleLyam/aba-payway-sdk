package com.abapayway.sdk.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class PurchaseRequest {

    @JsonProperty("tran_id")
    @NotBlank(message = "Transaction ID is required")
    @Size(max = 20, message = "Transaction ID must be 20 characters or less")
    private String tranId;

    @JsonProperty("amount")
    @NotBlank(message = "Amount is required")
    @Pattern(regexp = "^(0|[1-9]\\d*)(\\.\\d{1,2})?$", message = "Amount must be a non-negative number")
    private String amount;

    @JsonProperty("payment_option")
    @Pattern(regexp = "^(cards|abapay|abapay_khqr|abapay_khqr_deeplink|alipay|wechat|google_pay)?$", message = "Invalid payment option")
    private String paymentOption;

    @JsonProperty("currency")
    private String currency;

    @JsonProperty("return_url")
    private String returnUrl;

    @JsonProperty("cancel_url")
    private String cancelUrl;

    @JsonProperty("continue_success_url")
    private String continueSuccessUrl;

    @JsonProperty("view_type")
    private String viewType;

    @JsonProperty("payment_gate")
    private String paymentGate;

    public String getTranId() {
        return tranId;
    }

    public void setTranId(String tranId) {
        this.tranId = tranId;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getPaymentOption() {
        return paymentOption;
    }

    public void setPaymentOption(String paymentOption) {
        this.paymentOption = paymentOption;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getReturnUrl() {
        return returnUrl;
    }

    public void setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl;
    }

    public String getCancelUrl() {
        return cancelUrl;
    }

    public void setCancelUrl(String cancelUrl) {
        this.cancelUrl = cancelUrl;
    }

    public String getContinueSuccessUrl() {
        return continueSuccessUrl;
    }

    public void setContinueSuccessUrl(String continueSuccessUrl) {
        this.continueSuccessUrl = continueSuccessUrl;
    }

    public String getViewType() {
        return viewType;
    }

    public void setViewType(String viewType) {
        this.viewType = viewType;
    }

    public String getPaymentGate() {
        return paymentGate;
    }

    public void setPaymentGate(String paymentGate) {
        this.paymentGate = paymentGate;
    }
}
