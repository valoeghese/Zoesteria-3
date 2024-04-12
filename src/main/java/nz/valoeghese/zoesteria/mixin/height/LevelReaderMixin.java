package nz.valoeghese.zoesteria.mixin.height;

import net.minecraft.server.level.ServerChunkCache;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.WorldGenRegion;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.WorldGenLevel;
import nz.valoeghese.zoesteria.common.gen.ZoesteriaChunkGenerator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Zoesteria 3 worldgen requires more height -
 * Y-shifted chunks are still a little difficult to trick the rest of the engine. We'll settle with
 * 512 world height instead.
 *
 * If you're looking for y-shifted chunks switch to the z-shifted-chunks branch.
 */
@Mixin(LevelReader.class)
public interface LevelReaderMixin {
	@Inject(at = @At("HEAD"), method = "getMinBuildHeight", cancellable = true)
	default void zoesteriaMinBuildHeight(CallbackInfoReturnable<Integer> cir) {
		if (this instanceof ServerLevel serverLevel) {
			if (serverLevel.getChunkSource().getGenerator() instanceof ZoesteriaChunkGenerator) {
				cir.setReturnValue(0);
			}
		} else if (this instanceof WorldGenRegion wgr) {
			if (wgr.getLevel().getChunkSource().getGenerator() instanceof ZoesteriaChunkGenerator) {
				cir.setReturnValue(0);
			}
		}
	}

	@Inject(at = @At("HEAD"), method = "getHeight()I", cancellable = true)
	default void zoesteriaHeight(CallbackInfoReturnable<Integer> cir) {
		if (this instanceof ServerLevel serverLevel) {
			if (serverLevel. instanceof ZoesteriaChunkGenerator) {
				cir.setReturnValue(512);
			}
		} else if (this instanceof WorldGenRegion wgr) {
			if (wgr.getLevel().getChunkSource().getGenerator() instanceof ZoesteriaChunkGenerator) {
				cir.setReturnValue(512);
			}
		}
	}
}
