package dev.rylex.integratedmattervalues;

import dev.rylex.integratedmattervalues.client.IntegratedMatterValuesClient;
import dev.rylex.integratedmattervalues.config.ClientConfig;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Mod(IntegratedMatterValues.MOD_ID)
public final class IntegratedMatterValues {
    public static final String MOD_ID = "integratedmattervalues";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static ResourceLocation id(String path) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
    }

    public IntegratedMatterValues(IEventBus modBus, ModContainer container, Dist dist) {
        container.registerConfig(ModConfig.Type.CLIENT, ClientConfig.SPEC);
        if (dist.isClient()) {
            IntegratedMatterValuesClient.init(container);
        }
        LOGGER.info("{} initialized", MOD_ID);
    }
}
