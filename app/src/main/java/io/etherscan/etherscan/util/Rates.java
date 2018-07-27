package io.etherscan.etherscan.util;

import java.util.HashMap;
import java.util.Map;

public class Rates {

    /**
     * Find the sum of token transactions for each token and convert them to eth using the current rate
     */
    public static Double getSumOfTokensInETH(HashMap<String, Double> erc20TokensBalances, Map<String, Map<String, String>> rates) {


        Double sumERC20 = (double) 0;
        for (String token : erc20TokensBalances.keySet()) {
            String rate;
            try {
                rate = rates.get(token.toUpperCase()).get("ETH");
            } catch (NullPointerException npe) {
//                Log.e("TAG", "No rates info for token: " + token);
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

}
