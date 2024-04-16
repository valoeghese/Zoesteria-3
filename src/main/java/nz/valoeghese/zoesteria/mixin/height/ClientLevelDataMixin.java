package nz.valoeghese.zoesteria.mixin.height;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.LevelReader;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ClientLevel.ClientLevelData.class)
public class ClientLevelDataMixin {
	@Shadow @Final private boolean isFlat;

	@Inject(at = @At("HEAD"), method = "getHorizonHeight", cancellable = true)
	private void useSeaLevelForHorizon(LevelHeightAccessor level, CallbackInfoReturnable<Double> cir) {
		if (!this.isFlat && level instanceof LevelReader lr) {
			cir.setReturnValue((double) lr.getSeaLevel());
		}
	}
}
