package io.etherscan.etherscan.util;

import android.annotation.SuppressLint;

public class Converter {

    @SuppressLint("DefaultLocale")
    public static String showEthereum2f(Double value) {
        return String.format("%.2f", value / Math.pow(10, 18));
    }

    @SuppressLint("DefaultLocale")
    public static String showEthereum3f(Double value) {
        return String.format("%.3f", value / Math.pow(10, 18));
    }

    @SuppressLint("DefaultLocale")
    public static String showWithDecimals(Double value, int numberOfDecimals) {
        return String.format("%.2f", value / Math.pow(10, numberOfDecimals));
    }
}
