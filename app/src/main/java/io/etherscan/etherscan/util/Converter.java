package io.etherscan.etherscan.util;

import android.annotation.SuppressLint;

public class Converter {

    @SuppressLint("DefaultLocale")
    public static String showEthereum(Double value) {
        return String.format("%.2f", value / Math.pow(10, 18));
    }
}
