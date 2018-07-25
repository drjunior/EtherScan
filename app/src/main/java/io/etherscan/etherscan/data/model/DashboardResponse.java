package io.etherscan.etherscan.data.model;

import java.util.List;

import io.etherscan.etherscan.data.network.BalanceResponse;

public class DashboardResponse {

    private Boolean                error = false;
    private List<TokenTransaction> tokenTransactions;
    private BalanceResponse        adressBalance;

    public Boolean getError() {
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
