package nz.valoeghese.zoesteria.mixin.zshift;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.LevelHeightAccessor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(LevelHeightAccessor.class)
public interface LevelHeightAccessorMixin {
	@Shadow int getMaxSection();

	/**
	 * @author valoeghese
	 * @reason testing
	 */
	@Overwrite
	default boolean isOutsideBuildHeight(BlockPos pos) {
		ChunkPos chunkPos = new ChunkPos(pos);
		int minY = 16 * (int) (4 * Math.sin(chunkPos.x/5.0));
		return pos.getY() >= minY && pos.getY() <= this.getMaxSection();
	}
}
