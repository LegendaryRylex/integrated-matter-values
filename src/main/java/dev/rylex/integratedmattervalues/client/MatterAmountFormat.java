package dev.rylex.integratedmattervalues.client;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public final class MatterAmountFormat {
    private static final double SUFFIX_THRESHOLD = 1000.0;
    private static final double SMALLEST_SHOWN = 0.01;
    private static final String BELOW_SMALLEST = "<0.01";
    private static final DecimalFormat EXACT = createExactFormat();

    private MatterAmountFormat() {}

    public static boolean isBelowSuffixThreshold(double amount) {
        return amount < SUFFIX_THRESHOLD;
    }

    public static String exact(double amount) {
        if (amount > 0 && amount < SMALLEST_SHOWN) {
            return BELOW_SMALLEST;
        }
        return EXACT.format(amount);
    }

    private static DecimalFormat createExactFormat() {
        DecimalFormat format = new DecimalFormat("0.##", DecimalFormatSymbols.getInstance(Locale.ROOT));
        format.setRoundingMode(RoundingMode.HALF_UP);
        return format;
    }
}
