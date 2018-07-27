package io.etherscan.etherscan.util;

import android.util.Log;

import java.util.HashMap;
import java.util.Map;

import io.etherscan.etherscan.data.model.Token;

public class Rates {

    /**
     * Find the sum of token transactions for each token and convert them to eth using the current rate
     */
    public static Double getSumOfTokensInETH(HashMap<Token, Double> erc20TokensBalances, Map<String, Map<String, String>> rates) {


        Double sumERC20 = (double) 0;
        for (Token token : erc20TokensBalances.keySet()) {
            String rate;
            try {
                rate = rates.get(token.getTokenSymbol().toUpperCase()).get("ETH");
            } catch (NullPointerException npe) {
                Log.e("TAG", "No rates info for token: " + token.getTokenSymbol());
                rate = "0";
            }

            Double value             = erc20TokensBalances.get(token);
            Double valueOfTokenInEth = value * Double.valueOf(rate);
            sumERC20 += valueOfTokenInEth;
//            Log.d("TAG", " rate " + rate + " token: " + token + " value " + value + " final " + valueOfTokenInEth);
//            Log.d("TAG", "token: " + token + " " + Converter.showEthereum(String.valueOf(valueOfTokenInEth)));
        }

        return sumERC20;
    }

    public static final Double getValueOfTokenInEth(Token token, Double tokenValue, Map<String, Map<String, String>> rates){
        String rate;
        try {
            rate = rates.get(token.getTokenSymbol().toUpperCase()).get("ETH");
        } catch (NullPointerException npe) {
            rate = "0";
            //FIXME the api has no info for some tokens
        }

        return tokenValue * Double.valueOf(rate);
    }

}
