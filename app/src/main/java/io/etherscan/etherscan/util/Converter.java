package io.etherscan.etherscan.util;

import android.annotation.SuppressLint;

public class Converter {

    @SuppressLint("DefaultLocale")
    public static String showEthereum(String value){
        return String.format("%.2f", Double.parseDouble(value) / Math.pow(10, 18));
    }
}
