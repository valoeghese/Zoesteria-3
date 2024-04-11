package nz.valoeghese.zoesteria.datagen;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.presets.WorldPreset;
import nz.valoeghese.zoesteria.Zoesteria3;

public class ZoesteriaWorldPresets {
	public static final ResourceKey<WorldPreset> ZOESTERIA = ResourceKey.create(Registries.WORLD_PRESET, Zoesteria3.location("zoesteria"));
}
