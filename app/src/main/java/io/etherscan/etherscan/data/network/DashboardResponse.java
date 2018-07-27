package io.etherscan.etherscan.data.network;

import java.util.List;
import java.util.Map;

import io.etherscan.etherscan.data.model.TokenTransaction;

public class DashboardResponse {

    private Boolean                          error = false;
    private List<TokenTransaction>           tokenTransactions;
    private BalanceResponse                  adressBalance;
    private Double                           erc20TokensBalance;
    private Double                           totalBalance;
    private Map<String, Map<String, String>> rates;


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


    public Map<String, Map<String, String>> getRates() {
        return rates;
    }

    public void setRates(Map<String, Map<String, String>> rates) {
        this.rates = rates;
    }


    public Double getErc20TokensBalance() {
        return erc20TokensBalance;
    }

    public void setErc20TokensBalance(Double erc20TokensBalance) {
        this.erc20TokensBalance = erc20TokensBalance;
    }

    public Double getTotalBalance() {
        return totalBalance;
    }

    public void setTotalBalance(Double totalBalance) {
        this.totalBalance = totalBalance;
    }
}
