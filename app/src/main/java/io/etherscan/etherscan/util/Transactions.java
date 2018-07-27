package io.etherscan.etherscan.util;

import java.util.HashMap;
import java.util.List;

import io.etherscan.etherscan.data.model.TokenTransaction;

public class Transactions {

    public static HashMap<String, Double> getErc20TokenBalanceTransactions(String walletAddress, List<TokenTransaction> tokenTransactions) {


        HashMap<String, Double> erc20TokensBalances = new HashMap<>();

        for (TokenTransaction tokenTransaction : tokenTransactions) {
            Double transactionValue = (double) 0;

            if (tokenTransaction.getFrom().equalsIgnoreCase(walletAddress)) {
                transactionValue -= Double.valueOf(tokenTransaction.getValue());
            } else {
                transactionValue += Double.valueOf(tokenTransaction.getValue());
            }

            if (erc20TokensBalances.containsKey(tokenTransaction.getTokenSymbol())) {
                transactionValue = erc20TokensBalances.get(tokenTransaction.getTokenSymbol()) + transactionValue;
            }

            erc20TokensBalances.put(tokenTransaction.getTokenSymbol(), transactionValue);


        }
        return erc20TokensBalances;
    }
}
