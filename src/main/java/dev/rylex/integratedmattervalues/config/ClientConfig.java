package dev.rylex.integratedmattervalues.config;

import net.neoforged.neoforge.common.ModConfigSpec;

public final class ClientConfig {
    private ClientConfig() {}

    public static final ModConfigSpec SPEC;

    private static final boolean DEFAULT_REQUIRE_SHIFT = false;

    private static final ModConfigSpec.BooleanValue REQUIRE_SHIFT;

    static {
        var builder = new ModConfigSpec.Builder();
        REQUIRE_SHIFT = builder.comment(
                        "Only show matter values in Integrated Terminals while Shift is held, with a hint line otherwise.")
                .translation("integratedmattervalues.configuration.require_shift")
                .define("require_shift", DEFAULT_REQUIRE_SHIFT);
        SPEC = builder.build();
    }

    public static boolean requireShift() {
        return SPEC.isLoaded() ? REQUIRE_SHIFT.get() : DEFAULT_REQUIRE_SHIFT;
    }
}
