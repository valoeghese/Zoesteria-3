package nz.valoeghese.zoesteria.mixin.yshift;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.ai.village.poi.PoiManager;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.chunk.storage.ChunkSerializer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ChunkSerializer.class)
public class ChunkSerializerMixin {
	@Redirect(at = @At(value = "INVOKE", target = "Lnet/minecraft/server/level/ServerLevel;getSectionIndexFromSectionY(I)I"),
	method = "read")
	private static int modifySectionIndex(ServerLevel instance, int i, ServerLevel level, PoiManager poiManager, ChunkPos pos, CompoundTag tag) {
		return i - tag.getInt("yPos"); // min section
	}
}
