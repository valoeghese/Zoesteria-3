package nz.valoeghese.zoesteria;

import net.fabricmc.api.ModInitializer;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import nz.valoeghese.zoesteria.common.gen.ZoesteriaBiomeSource;
import nz.valoeghese.zoesteria.common.gen.ZoesteriaChunkGenerator;
import nz.valoeghese.zoesteria.common.gen.ZoesteriaWorldPresets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Zoesteria3 implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
    public static final Logger LOGGER = LoggerFactory.getLogger("zoesteria-3");
	public static final String MOD_ID = "zoesteria";

	public static ResourceLocation location(String target) {
		return new ResourceLocation(MOD_ID, target);
	}

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
	}
}