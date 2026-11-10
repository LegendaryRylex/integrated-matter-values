package dev.rylex.integratedmattervalues.config;

import net.neoforged.neoforge.common.ModConfigSpec;

public final class ClientConfig {
    private ClientConfig() {}

    public static final ModConfigSpec SPEC;

    private static final boolean DEFAULT_REQUIRE_SHIFT = false;
    private static final boolean DEFAULT_EXACT_VALUES = false;

    private static final ModConfigSpec.BooleanValue REQUIRE_SHIFT;
    private static final ModConfigSpec.BooleanValue EXACT_VALUES;

    static {
        var builder = new ModConfigSpec.Builder();
        REQUIRE_SHIFT = builder.comment(
                        "Only show matter values in Integrated Terminals while Shift is held, with a hint line otherwise.")
                .translation("integratedmattervalues.configuration.require_shift")
                .define("require_shift", DEFAULT_REQUIRE_SHIFT);
        EXACT_VALUES = builder.comment(
                        "Show matter values below 1000 with up to two decimals, such as 0.25, instead of rounded up to a whole number. Applies to every Replication matter tooltip, not only Integrated Terminals.")
                .translation("integratedmattervalues.configuration.exact_values")
                .define("exact_values", DEFAULT_EXACT_VALUES);
        SPEC = builder.build();
    }

    public static boolean requireShift() {
        return SPEC.isLoaded() ? REQUIRE_SHIFT.get() : DEFAULT_REQUIRE_SHIFT;
    }

    public static boolean exactValues() {
        return SPEC.isLoaded() ? EXACT_VALUES.get() : DEFAULT_EXACT_VALUES;
    }
}
