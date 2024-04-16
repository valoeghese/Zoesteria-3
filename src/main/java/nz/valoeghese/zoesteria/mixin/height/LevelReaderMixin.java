package nz.valoeghese.zoesteria.mixin.height;

import net.minecraft.server.level.ServerChunkCache;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.WorldGenRegion;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.WorldGenLevel;
import nz.valoeghese.zoesteria.common.gen.ZoesteriaChunkGenerator;
import nz.valoeghese.zoesteria.worldgen.ZoesteriaWorldGenerator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Zoesteria 3 worldgen requires more height -
 * Y-shifted chunks are still a little difficult to trick the rest of the engine. We'll settle with
 * 512 world height instead.
 *
 * We will also have sea level at 32, to provide more height to the world.
 *
 * If you're looking for y-shifted chunks switch to the z-shifted-chunks branch.
 */
@Mixin(LevelReader.class)
public interface LevelReaderMixin {
	@Inject(at = @At("HEAD"), method = "getHeight()I", cancellable = true)
	default void zoesteriaHeight(CallbackInfoReturnable<Integer> cir) {
		if (this instanceof ServerLevel serverLevel) {
			if (ZoesteriaWorldGenerator.isZoesteriaWorld(serverLevel)) {
				cir.setReturnValue(ZoesteriaWorldGenerator.HEIGHT);
			}
		} else if (this instanceof WorldGenRegion wgr) {
			if (ZoesteriaWorldGenerator.isZoesteriaWorld(wgr.getLevel())) {
				cir.setReturnValue(ZoesteriaWorldGenerator.HEIGHT);
			}
		}
	}
}
