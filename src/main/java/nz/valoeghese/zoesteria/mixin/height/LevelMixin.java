package nz.valoeghese.zoesteria.mixin.height;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.level.Level;
import nz.valoeghese.zoesteria.worldgen.ZoesteriaWorldGenerator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Level.class)
public class LevelMixin {
	@Inject(at = @At("HEAD"), method = "getSeaLevel", cancellable = true)
	public void zoesteriaSeaLevel(CallbackInfoReturnable<Integer> cir) {
		if ((Object)this instanceof ServerLevel serverLevel) {
			if (ZoesteriaWorldGenerator.isZoesteriaWorld(serverLevel)) {
				cir.setReturnValue(ZoesteriaWorldGenerator.SEA_LEVEL);
			}
		}
	}
}
