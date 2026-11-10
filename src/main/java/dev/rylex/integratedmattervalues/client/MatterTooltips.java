package dev.rylex.integratedmattervalues.client;

import com.buuz135.replication.calculation.MatterCompound;
import com.buuz135.replication.calculation.client.ClientReplicationCalculation;
import com.buuz135.replication.client.MatterTooltipComponent;
import com.mojang.datafixers.util.Either;
import dev.rylex.integratedmattervalues.config.ClientConfig;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nullable;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.FormattedText;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.ItemStack;

public final class MatterTooltips {
    private MatterTooltips() {}

    @Nullable
    public static List<Either<FormattedText, TooltipComponent>> withMatterValues(
            ItemStack stack, @Nullable List<Either<FormattedText, TooltipComponent>> elements) {
        if (stack.isEmpty()) {
            return elements;
        }
        MatterCompound matter = ClientReplicationCalculation.getMatterCompound(stack);
        if (matter == null || matter.getValues().isEmpty()) {
            return elements;
        }
        List<Either<FormattedText, TooltipComponent>> result =
                elements == null ? new ArrayList<>(1) : new ArrayList<>(elements);
        if (ClientConfig.requireShift() && !Minecraft.getInstance().hasShiftDown()) {
            result.add(Either.left(shiftHint()));
        } else {
            result.add(Either.right(new MatterTooltipComponent(matter)));
        }
        return result;
    }

    private static Component shiftHint() {
        return Component.translatable(
                        "tooltip.replication.matter_value",
                        Component.literal("Shift").withStyle(ChatFormatting.YELLOW))
                .withStyle(ChatFormatting.GRAY);
    }
}
