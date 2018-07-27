package io.etherscan.etherscan.util;

import java.util.HashMap;
import java.util.List;

import io.etherscan.etherscan.data.model.Token;
import io.etherscan.etherscan.data.model.TokenTransaction;

public class Transactions {

    public static HashMap<Token, Double> getErc20TokenBalanceTransactions(String walletAddress, List<TokenTransaction> tokenTransactions) {


        HashMap<Token, Double> erc20TokensBalances = new HashMap<>();

        for (TokenTransaction tokenTransaction : tokenTransactions) {
            Double transactionValue = (double) 0;

            if (tokenTransaction.getFrom().equalsIgnoreCase(walletAddress)) {
                transactionValue -= Double.valueOf(tokenTransaction.getValue());
            } else {
                transactionValue += Double.valueOf(tokenTransaction.getValue());
            }

            // check if it's already present in the map and get previous value
            Token token = new Token( tokenTransaction.getTokenName(), tokenTransaction.getTokenSymbol(), Integer.valueOf(tokenTransaction.getTokenDecimal()));
            if (erc20TokensBalances.containsKey(token)) {
                transactionValue = erc20TokensBalances.get(token) + transactionValue;
            }

            erc20TokensBalances.put(token, transactionValue);

        }
        return erc20TokensBalances;
    }
}
