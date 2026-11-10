package dev.rylex.integratedmattervalues.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import com.mojang.datafixers.util.Either;
import dev.rylex.integratedmattervalues.client.MatterTooltips;
import java.util.List;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.FormattedText;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.ItemStack;
import org.cyclops.cyclopscore.helper.IModHelpers;
import org.cyclops.integratedterminals.capability.ingredient.IngredientComponentTerminalStorageHandlerItemStackClient;
import org.cyclops.integratedterminals.client.gui.container.ContainerScreenTerminalStorage;
import org.cyclops.integratedterminals.client.gui.tooltip.TooltipRenderHelpers;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(IngredientComponentTerminalStorageHandlerItemStackClient.class)
public abstract class IngredientComponentTerminalStorageHandlerItemStackClientMixin {

    @ModifyVariable(
            method =
                    "drawInstance(Lnet/minecraft/client/gui/GuiGraphicsExtractor;Lnet/minecraft/world/item/ItemStack;JLjava/lang/String;Lnet/minecraft/client/gui/screens/inventory/AbstractContainerScreen;Lorg/cyclops/integratedterminals/client/gui/container/ContainerScreenTerminalStorage$DrawLayer;FIIIILjava/util/List;Ljava/util/List;)V",
            at = @At("HEAD"),
            argsOnly = true,
            ordinal = 1)
    private List<Either<FormattedText, TooltipComponent>> integratedmattervalues$addMatterValues(
            List<Either<FormattedText, TooltipComponent>> elements,
            @Local(argsOnly = true) ItemStack instance,
            @Local(argsOnly = true) AbstractContainerScreen<?> gui,
            @Local(argsOnly = true) ContainerScreenTerminalStorage.DrawLayer layer,
            @Local(argsOnly = true, ordinal = 0) int x,
            @Local(argsOnly = true, ordinal = 1) int y,
            @Local(argsOnly = true, ordinal = 2) int mouseX,
            @Local(argsOnly = true, ordinal = 3) int mouseY) {
        if (layer != ContainerScreenTerminalStorage.DrawLayer.FOREGROUND) {
            return elements;
        }
        int slotSize = IModHelpers.get().getGuiHelpers().getSlotSizeInner();
        if (!TooltipRenderHelpers.isHovering(gui, x, y, slotSize, slotSize, mouseX, mouseY)) {
            return elements;
        }
        return MatterTooltips.withMatterValues(instance, elements);
    }
}
