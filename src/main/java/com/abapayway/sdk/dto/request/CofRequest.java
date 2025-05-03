package com.abapayway.sdk.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
// import jakarta.validation.constraints.NotBlank;
// import jakarta.validation.constraints.Size;

public class CofRequest {

    @JsonProperty("return_param")
    private String returnParam;


    @JsonProperty("return_url")
    private String returnUrl;

    @JsonProperty("continue_add_card_success_url")
    private String continueAddCardSuccessUrl;


    public String getReturnParam() {
        return returnParam;
    }

    public void setReturnParam(String returnParam) {
        this.returnParam = returnParam;
    }


    public String getReturnUrl() {
        return returnUrl;
    }

    public void setReturnUrl(String returnUrl) {
        this.returnUrl = returnUrl;
    }

    public String getContinueAddCardSuccessUrl() {
        return continueAddCardSuccessUrl;
    }

    public void setContinueAddCardSuccessUrl(String continueAddCardSuccessUrl) {
        this.continueAddCardSuccessUrl = continueAddCardSuccessUrl;
    }

}
