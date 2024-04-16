package nz.valoeghese.zoesteria.mixin.height;

import net.minecraft.world.level.Level;
import nz.valoeghese.zoesteria.worldgen.ZoesteriaWorldGenerator;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Level.class)
public class LevelMixin {
	@Shadow @Final public boolean isClientSide;

	@Inject(at = @At("HEAD"), method = "getSeaLevel", cancellable = true)
	public void zoesteriaSeaLevel(CallbackInfoReturnable<Integer> cir) {
		if (this.isClientSide || ZoesteriaWorldGenerator.isZoesteriaWorld((Level)(Object)this)) {
			cir.setReturnValue(ZoesteriaWorldGenerator.SEA_LEVEL);
		}
	}
}
