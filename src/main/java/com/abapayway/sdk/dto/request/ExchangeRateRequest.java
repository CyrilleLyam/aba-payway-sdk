package com.abapayway.sdk.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

public class ExchangeRateRequest {

    @JsonProperty("req_time")
    @NotBlank(message = "Request time is required")
    private String reqTime;

    public String getReqTime() {
        return reqTime;
    }

    public void setReqTime(String reqTime) {
        this.reqTime = reqTime;
    }
}
