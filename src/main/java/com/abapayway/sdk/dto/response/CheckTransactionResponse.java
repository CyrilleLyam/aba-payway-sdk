package com.abapayway.sdk.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CheckTransactionResponse {

    @JsonProperty("data")
    private Data data;

    @JsonProperty("status")
    private Status status;

    // Getters and Setters
    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    // Nested class for "data"
    public static class Data {
        @JsonProperty("payment_status_code")
        private int paymentStatusCode;

        @JsonProperty("total_amount")
        private double totalAmount;

        @JsonProperty("original_amount")
        private double originalAmount;

        @JsonProperty("refund_amount")
        private double refundAmount;

        @JsonProperty("discount_amount")
        private double discountAmount;

        @JsonProperty("payment_amount")
        private double paymentAmount;

        @JsonProperty("payment_currency")
        private String paymentCurrency;

        @JsonProperty("apv")
        private String apv;

        @JsonProperty("payment_status")
        private String paymentStatus;

        @JsonProperty("transaction_date")
        private String transactionDate;

        // Getters and Setters
        public int getPaymentStatusCode() {
            return paymentStatusCode;
        }

        public void setPaymentStatusCode(int paymentStatusCode) {
            this.paymentStatusCode = paymentStatusCode;
        }

        public double getTotalAmount() {
            return totalAmount;
        }

        public void setTotalAmount(double totalAmount) {
            this.totalAmount = totalAmount;
        }

        public double getOriginalAmount() {
            return originalAmount;
        }

        public void setOriginalAmount(double originalAmount) {
            this.originalAmount = originalAmount;
        }

        public double getRefundAmount() {
            return refundAmount;
        }

        public void setRefundAmount(double refundAmount) {
            this.refundAmount = refundAmount;
        }

        public double getDiscountAmount() {
            return discountAmount;
        }

        public void setDiscountAmount(double discountAmount) {
            this.discountAmount = discountAmount;
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

        public String getTransactionDate() {
            return transactionDate;
        }

        public void setTransactionDate(String transactionDate) {
            this.transactionDate = transactionDate;
        }
    }

    // Nested class for "status"
    public static class Status {
        @JsonProperty("code")
        private String code;

        @JsonProperty("message")
        private String message;

        @JsonProperty("tran_id")
        private String tranId;

        // Getters and Setters
        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public String getTranId() {
            return tranId;
        }

        public void setTranId(String tranId) {
            this.tranId = tranId;
        }
    }
}