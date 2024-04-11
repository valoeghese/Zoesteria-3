package nz.valoeghese.zoesteria.mixin;

import com.mojang.serialization.Codec;
import net.minecraft.core.Registry;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.chunk.ChunkGenerators;
import nz.valoeghese.zoesteria.Zoesteria3;
import nz.valoeghese.zoesteria.common.gen.ZoesteriaChunkGenerator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ChunkGenerators.class)
public class ChunkGeneratorsMixin {
	@Inject(at = @At("RETURN"), method = "bootstrap")
	private static void onChunkGeneratorRegister(Registry<Codec<? extends ChunkGenerator>> registry, CallbackInfoReturnable<Codec<? extends ChunkGenerator>> info) {
		Registry.register(registry, Zoesteria3.location("overworld"), ZoesteriaChunkGenerator.CODEC);
	}
}