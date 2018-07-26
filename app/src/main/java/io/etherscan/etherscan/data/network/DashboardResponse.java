package io.etherscan.etherscan.data.network;

import java.util.List;

import io.etherscan.etherscan.data.model.TokenTransaction;

public class DashboardResponse {

    private Boolean                error = false;
    private List<TokenTransaction> tokenTransactions;
    private BalanceResponse        adressBalance;

    public Boolean isError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public List<TokenTransaction> getTokenTransactions() {
        return tokenTransactions;
    }

    public void setTokenTransactions(List<TokenTransaction> tokenTransactions) {
        this.tokenTransactions = tokenTransactions;
    }

    public BalanceResponse getAdressBalance() {
        return adressBalance;
    }

    public void setAdressBalance(BalanceResponse adressBalance) {
        this.adressBalance = adressBalance;
    }
}
