package nz.valoeghese.zoesteria.mixin;

import net.minecraft.core.HolderGetter;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import net.minecraft.world.level.levelgen.RandomState;
import nz.valoeghese.zoesteria.common.gen.WorldGeneratorAccess;
import nz.valoeghese.zoesteria.worldgen.ZoesteriaWorldGenerator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(RandomState.class)
public class RandomStateMixin implements WorldGeneratorAccess {
	private ZoesteriaWorldGenerator zoesteria;

	@Inject(at = @At("RETURN"), method = "<init>")
	private void onConstruct(NoiseGeneratorSettings settings, HolderGetter noiseParametersGetter, long levelSeed, CallbackInfo ci) {
		this.zoesteria = new ZoesteriaWorldGenerator(levelSeed);
	}


	@Override
	public ZoesteriaWorldGenerator zoesteriaWorldGenerator() {
		return this.zoesteria;
	}
}
