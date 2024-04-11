package nz.valoeghese.zoesteria.mixin;

import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import net.minecraft.world.level.levelgen.presets.WorldPreset;
import net.minecraft.world.level.levelgen.presets.WorldPresets;
import nz.valoeghese.zoesteria.common.gen.ZoesteriaBiomeSource;
import nz.valoeghese.zoesteria.common.gen.ZoesteriaChunkGenerator;
import nz.valoeghese.zoesteria.common.gen.ZoesteriaWorldPresets;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WorldPresets.Bootstrap.class)
public abstract class WorldPresetsMixin {
	@Shadow
	abstract protected void registerCustomOverworldPreset(ResourceKey<WorldPreset> dimensionKey, LevelStem levelStem);

	@Shadow protected abstract LevelStem makeOverworld(ChunkGenerator generator);

	@Shadow @Final private HolderGetter<NoiseGeneratorSettings> noiseSettings;

	@Inject(at = @At("RETURN"), method = "bootstrap")
	private void onBootstrap(CallbackInfo ci) {
		BiomeSource source = new ZoesteriaBiomeSource(0);
		Holder<NoiseGeneratorSettings> settings = this.noiseSettings.getOrThrow(NoiseGeneratorSettings.OVERWORLD);

		LevelStem stem = this.makeOverworld(new ZoesteriaChunkGenerator(0, source, settings));
		this.registerCustomOverworldPreset(ZoesteriaWorldPresets.ZOESTERIA, stem);
	}
}
