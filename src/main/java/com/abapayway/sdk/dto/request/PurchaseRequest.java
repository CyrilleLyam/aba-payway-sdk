package com.abapayway.sdk.dto.request;

import com.abapayway.sdk.validator.ValidReturnDeeplink;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class PurchaseRequest {

    @JsonProperty("req_time")
    @NotBlank(message = "Request time is required")
    @Pattern(regexp = "^\\d{14}$", message = "Request time must be in UTC format YYYYMMDDHHmmss")
    private String reqTime;

    @JsonProperty("tran_id")
    @NotBlank(message = "Transaction ID is required")
    @Size(max = 20, message = "Transaction ID must be 20 characters or less")
    private String tranId;

    @JsonProperty("amount")
    @NotBlank(message = "Amount is required")
    @Pattern(regexp = "^(0|[1-9]\\d*)(\\.\\d{1,2})?$", message = "Amount must be a non-negative number")
    private String amount;

    @JsonProperty("payment_option")
    @NotBlank(message = "Payment option is required")
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

    @JsonProperty("return_deeplink")
    @ValidReturnDeeplink
    private String returnDeeplink;

    @JsonProperty("items")
    private String items;

    @JsonProperty("shipping")
    private String shipping;

    @JsonProperty("firstname")
    private String firstname;

    @JsonProperty("lastname")
    private String lastname;

    @JsonProperty("email")
    private String email;

    @JsonProperty("phone")
    private String phone;

    @JsonProperty("type")
    private String type;

    @JsonProperty("custom_fields")
    private String customFields;

    @JsonProperty("return_params")
    private String returnParams;

    @JsonProperty("payout")
    private String payout;

    @JsonProperty("lifetime")
    private String lifetime;

    @JsonProperty("additional_params")
    private String additionalParams;

    @JsonProperty("google_pay_token")
    private String googlePayToken;

    public String getReqTime() {
        return reqTime;
    }

    public void setReqTime(String reqTime) {
        this.reqTime = reqTime;
    }

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

    public String getReturnDeeplink() {
        return returnDeeplink;
    }

    public void setReturnDeeplink(String returnDeeplink) {
        this.returnDeeplink = returnDeeplink;
    }

    public String getItems() {
        return items;
    }

    public void setItems(String items) {
        this.items = items;
    }

    public String getShipping() {
        return shipping;
    }

    public void setShipping(String shipping) {
        this.shipping = shipping;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCustomFields() {
        return customFields;
    }

    public void setCustomFields(String customFields) {
        this.customFields = customFields;
    }

    public String getReturnParams() {
        return returnParams;
    }

    public void setReturnParams(String returnParams) {
        this.returnParams = returnParams;
    }

    public String getPayout() {
        return payout;
    }

    public void setPayout(String payout) {
        this.payout = payout;
    }

    public String getLifetime() {
        return lifetime;
    }

    public void setLifetime(String lifetime) {
        this.lifetime = lifetime;
    }

    public String getAdditionalParams() {
        return additionalParams;
    }

    public void setAdditionalParams(String additionalParams) {
        this.additionalParams = additionalParams;
    }

    public String getGooglePayToken() {
        return googlePayToken;
    }

    public void setGooglePayToken(String googlePayToken) {
        this.googlePayToken = googlePayToken;
    }
}
