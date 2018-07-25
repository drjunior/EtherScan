package io.etherscan.etherscan.data.network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import io.etherscan.etherscan.data.model.TokenTransaction;

public class TransactionsResponse {

    @SerializedName("status")
    @Expose
    private String                 status;
    @SerializedName("message")
    @Expose
    private String                 message;
    @SerializedName("result")
    @Expose
    private List<TokenTransaction> result = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<TokenTransaction> getResult() {
        return result;
    }

    public void setResult(List<TokenTransaction> result) {
        this.result = result;
    }

}

