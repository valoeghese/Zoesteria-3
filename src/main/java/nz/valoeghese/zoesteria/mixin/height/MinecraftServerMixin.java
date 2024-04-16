package nz.valoeghese.zoesteria.mixin.height;

import net.minecraft.core.LayeredRegistryAccess;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.RegistryLayer;
import net.minecraft.server.level.progress.ChunkProgressListener;
import net.minecraft.world.level.dimension.LevelStem;
import nz.valoeghese.zoesteria.common.gen.ZoesteriaChunkGenerator;
import nz.valoeghese.zoesteria.worldgen.ZoesteriaWorldGenerator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftServer.class)
public abstract class MinecraftServerMixin {
	@Shadow public abstract LayeredRegistryAccess<RegistryLayer> registries();

	@Inject(at = @At(
			value="NEW",
			target = "(Lnet/minecraft/server/MinecraftServer;Ljava/util/concurrent/Executor;Lnet/minecraft/world/level/storage/LevelStorageSource$LevelStorageAccess;Lnet/minecraft/world/level/storage/ServerLevelData;Lnet/minecraft/resources/ResourceKey;Lnet/minecraft/world/level/dimension/LevelStem;Lnet/minecraft/server/level/progress/ChunkProgressListener;ZJLjava/util/List;ZLnet/minecraft/world/RandomSequences;)Lnet/minecraft/server/level/ServerLevel;",
			ordinal = 0
	),
			method = "createLevels")
	protected void onCreateLevels(ChunkProgressListener listener, CallbackInfo ci) {
		Registry<LevelStem> registry = this.registries().compositeAccess().registryOrThrow(Registries.LEVEL_STEM);
		ZoesteriaWorldGenerator.active = registry.get(LevelStem.OVERWORLD).generator() instanceof ZoesteriaChunkGenerator;
	}
}
