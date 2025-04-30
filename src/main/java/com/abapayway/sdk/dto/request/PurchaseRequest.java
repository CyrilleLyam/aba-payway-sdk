package com.abapayway.sdk.dto.request;


public class PurchaseRequest {

    private String amount;
    private String paymentOption;
    private String currency;

    private String returnUrl;
    private String cancelUrl;
    private String continueSuccessUrl;
    private String returnDeeplink;

    private String returnParams;

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

    public String getReturnDeeplink() {
        return returnDeeplink;
    }

    public void setReturnDeeplink(String returnDeeplink) {
        this.returnDeeplink = returnDeeplink;
    }

    public String getReturnParams() {
        return returnParams;
    }

    public void setReturnParams(String returnParams) {
        this.returnParams = returnParams;
    }
}
