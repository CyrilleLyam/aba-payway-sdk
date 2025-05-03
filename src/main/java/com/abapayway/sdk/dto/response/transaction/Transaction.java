package com.abapayway.sdk.dto.response.transaction;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Transaction {

    @JsonProperty("transaction_id")
    private String transactionId;

    @JsonProperty("transaction_date")
    private String transactionDate;

    @JsonProperty("apv")
    private String apv;

    @JsonProperty("payment_status")
    private String paymentStatus;

    @JsonProperty("payment_status_code")
    private int paymentStatusCode;

    @JsonProperty("payment_type")
    private String paymentType;

    @JsonProperty("original_amount")
    private double originalAmount;

    @JsonProperty("original_currency")
    private String originalCurrency;

    @JsonProperty("total_amount")
    private double totalAmount;

    @JsonProperty("discount_amount")
    private double discountAmount;

    @JsonProperty("refund_amount")
    private double refundAmount;

    @JsonProperty("payment_amount")
    private double paymentAmount;

    @JsonProperty("payment_currency")
    private String paymentCurrency;

    // Getters and setters

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getApv() {
        return apv;
    }

    public void setApv(String apv) {
        this.apv = apv;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public int getPaymentStatusCode() {
        return paymentStatusCode;
    }

    public void setPaymentStatusCode(int paymentStatusCode) {
        this.paymentStatusCode = paymentStatusCode;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public double getOriginalAmount() {
        return originalAmount;
    }

    public void setOriginalAmount(double originalAmount) {
        this.originalAmount = originalAmount;
    }

    public String getOriginalCurrency() {
        return originalCurrency;
    }

    public void setOriginalCurrency(String originalCurrency) {
        this.originalCurrency = originalCurrency;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public double getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(double discountAmount) {
        this.discountAmount = discountAmount;
    }

    public double getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(double refundAmount) {
        this.refundAmount = refundAmount;
    }

    public double getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

    public String getPaymentCurrency() {
        return paymentCurrency;
    }

    public void setPaymentCurrency(String paymentCurrency) {
        this.paymentCurrency = paymentCurrency;
    }
}
