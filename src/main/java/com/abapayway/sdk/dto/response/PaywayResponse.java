package com.abapayway.sdk.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PaywayResponse {

    private Status status;
    private String description;

    @JsonProperty("qrString")
    private String qrString;

    @JsonProperty("qrImage")
    private String qrImage;

    @JsonProperty("abapay_deeplink")
    private String abapayDeeplink;

    @JsonProperty("app_store")
    private String appStore;

    @JsonProperty("play_store")
    private String playStore;

    // Getters and Setters

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getQrString() {
        return qrString;
    }

    public void setQrString(String qrString) {
        this.qrString = qrString;
    }

    public String getQrImage() {
        return qrImage;
    }

    public void setQrImage(String qrImage) {
        this.qrImage = qrImage;
    }

    public String getAbapayDeeplink() {
        return abapayDeeplink;
    }

    public void setAbapayDeeplink(String abapayDeeplink) {
        this.abapayDeeplink = abapayDeeplink;
    }

    public String getAppStore() {
        return appStore;
    }

    public void setAppStore(String appStore) {
        this.appStore = appStore;
    }

    public String getPlayStore() {
        return playStore;
    }

    public void setPlayStore(String playStore) {
        this.playStore = playStore;
    }

    public static class Status {

        private String code;

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
