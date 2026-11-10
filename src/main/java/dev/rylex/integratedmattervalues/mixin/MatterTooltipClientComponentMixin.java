package dev.rylex.integratedmattervalues.mixin;

import com.buuz135.replication.client.MatterTooltipClientComponent;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import dev.rylex.integratedmattervalues.client.MatterAmountFormat;
import dev.rylex.integratedmattervalues.config.ClientConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(MatterTooltipClientComponent.class)
public abstract class MatterTooltipClientComponentMixin {

    @WrapOperation(
            method = "renderInformationPill",
            at =
                    @At(
                            value = "INVOKE",
                            target =
                                    "Lcom/buuz135/replication/util/NumberUtils;getFormatedBigNumber(D)Ljava/lang/String;"))
    private String integratedmattervalues$formatExactAmount(double amount, Operation<String> original) {
        if (ClientConfig.exactValues() && MatterAmountFormat.isBelowSuffixThreshold(amount)) {
            return MatterAmountFormat.exact(amount);
        }
        return original.call(amount);
    }
}
