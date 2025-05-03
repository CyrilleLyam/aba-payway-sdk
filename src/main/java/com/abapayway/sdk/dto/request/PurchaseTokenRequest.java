package com.abapayway.sdk.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PurchaseTokenRequest {
    @JsonProperty("req_time")
    private String reqTime;

    @JsonProperty("type")
    private String type;

    @JsonProperty("items")
    private String items;

    @JsonProperty("amount")
    private int amount;

    @JsonProperty("tran_id")
    private String tranId;

    @JsonProperty("continue_success_url")
    private String continueSuccessUrl;

    @JsonProperty("return_url")
    private String returnUrl;

    @JsonProperty("return_param")
    private String returnParam;

    @JsonProperty("hash")
    private String hash;

    @JsonProperty("custom_fields")
    private String customFields;

    @JsonProperty("firstname")
    private String firstname;

    @JsonProperty("lastname")
    private String lastname;

    @JsonProperty("phone")
    private String phone;

    @JsonProperty("email")
    private String email;

    // Getters and Setters
    public String getReqtime(){
        return reqTime;
    }

    public void setReqtime(String req){
        this.reqTime = req;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getItems() {
        return items;
    }

    public void setItems(String items) {
        this.items = items;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getTranId() {
        return tranId;
    }

    public void setTranId(String tranId) {
        this.tranId = tranId;
    }

    public String getContinueSuccessUrl() {
        return continueSuccessUrl;
    }

    public void setContinueSuccessUrl(String continueSuccessUrl) {
        this.continueSuccessUrl = continueSuccessUrl;
    }

    public String getReturnUrl() {
        return returnUrl;
    }

    public void setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl;
    }

    public String getReturnParam() {
        return returnParam;
    }

    public void setReturnParam(String returnParam) {
        this.returnParam = returnParam;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getCustomFields() {
        return customFields;
    }

    public void setCustomFields(String customFields) {
        this.customFields = customFields;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}