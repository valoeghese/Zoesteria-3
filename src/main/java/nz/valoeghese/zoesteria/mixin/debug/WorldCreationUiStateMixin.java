package nz.valoeghese.zoesteria.mixin.debug;

import net.minecraft.client.gui.screens.worldselection.WorldCreationContext;
import net.minecraft.client.gui.screens.worldselection.WorldCreationUiState;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.presets.WorldPreset;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WorldCreationUiState.class)
public abstract class WorldCreationUiStateMixin {
	@Shadow public abstract WorldCreationContext getSettings();

	@Inject(at = @At("HEAD"), method = "updatePresetLists")
	private void a(CallbackInfo ci) {
		Registry<WorldPreset> registry = this.getSettings().worldgenLoadContext().registryOrThrow(Registries.WORLD_PRESET);

		registry.keySet().forEach(System.out::println);
	}
}
