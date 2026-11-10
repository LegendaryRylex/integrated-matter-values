package dev.rylex.integratedmattervalues;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import dev.rylex.integratedmattervalues.client.MatterAmountFormat;
import org.junit.jupiter.api.Test;

class MatterAmountFormatTest {
    @Test
    void keepsFractions() {
        assertEquals("0.25", MatterAmountFormat.exact(0.25));
        assertEquals("1.5", MatterAmountFormat.exact(1.5));
        assertEquals("0.33", MatterAmountFormat.exact(1.0 / 3.0));
        assertEquals("0.67", MatterAmountFormat.exact(2.0 / 3.0));
    }

    @Test
    void dropsTrailingZeros() {
        assertEquals("0", MatterAmountFormat.exact(0));
        assertEquals("8", MatterAmountFormat.exact(8.0));
        assertEquals("999.99", MatterAmountFormat.exact(999.99));
    }

    @Test
    void marksAmountsTooSmallToShow() {
        assertEquals("<0.01", MatterAmountFormat.exact(0.004));
        assertEquals("0.01", MatterAmountFormat.exact(0.01));
    }

    @Test
    void leavesSuffixedAmountsToReplication() {
        assertTrue(MatterAmountFormat.isBelowSuffixThreshold(999.99));
        assertFalse(MatterAmountFormat.isBelowSuffixThreshold(1000));
    }
}
