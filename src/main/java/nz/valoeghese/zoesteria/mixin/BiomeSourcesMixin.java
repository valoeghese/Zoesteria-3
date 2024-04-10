package nz.valoeghese.zoesteria.mixin;

import com.mojang.serialization.Codec;
import net.minecraft.core.Registry;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.biome.BiomeSources;
import nz.valoeghese.zoesteria.Zoesteria3;
import nz.valoeghese.zoesteria.common.ZoesteriaBiomeSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BiomeSources.class)
public class BiomeSourcesMixin {
	@Inject(at = @At("RETURN"), method = "bootstrap")
	private static void onBiomeSourceRegister(Registry<Codec<? extends BiomeSource>> registry, CallbackInfoReturnable<Codec<? extends BiomeSource>> cir) {
		Registry.register(registry, Zoesteria3.location("overworld"), ZoesteriaBiomeSource.CODEC);
	}
}