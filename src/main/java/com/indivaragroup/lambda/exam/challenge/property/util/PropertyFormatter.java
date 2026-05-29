package com.indivaragroup.lambda.exam.challenge.property.util;

import java.text.NumberFormat;
import java.util.Locale;

public class PropertyFormatter {
    public static String formatToRupiah(double price) {
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));
        return currencyFormat.format(price).replace(",00", "");
    }
}
