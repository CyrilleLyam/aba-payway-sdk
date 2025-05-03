package com.abapayway.sdk.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

public class RefundTransactionRequest {

  @JsonProperty("amount")
  @NotNull(message = "Amount is required")
  private String amount;

  @JsonProperty("tran_id")
  @NotNull(message = "TransactionId is required")
  private String tranId;

  public String getTranId(){
    return tranId;
  }

  public void setTranId(String tranId){
    this.tranId = tranId;
  }

  public String getAmount() {
    return amount;
  }

  public void setAmount(String amount) {
    this.amount = amount;
  }
  }
