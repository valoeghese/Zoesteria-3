package nz.valoeghese.zoesteria.mixin.zshift;

import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.LevelChunkSection;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(ChunkAccess.class)
public abstract class ChunkAccessMixin {
	@Shadow @Final protected ChunkPos chunkPos;

	@Shadow public abstract LevelChunkSection[] getSections();

	/**
	 * @author Valoeghese
	 * @reason testing
	 */
	@Overwrite
	public LevelChunkSection getSection(int index) {
		try {
			return this.getSections()[index];
		} catch (ArrayIndexOutOfBoundsException e) {
			return Dummy
		}
	}
	/**
	 * @author Valoeghese
	 * @reason testing
	 */
	@Overwrite
	public int getMinBuildHeight() {
		return 16 * (int) (4 * Math.sin(this.chunkPos.x/5.0));
	}
}
