package nz.valoeghese.zoesteria.mixin.zshift;

import net.minecraft.core.Registry;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.LevelHeightAccessor;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.LevelChunkSection;
import net.minecraft.world.level.chunk.UpgradeData;
import net.minecraft.world.level.levelgen.blending.BlendingData;
import nz.valoeghese.zoesteria.common.DummySection;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ChunkAccess.class)
public abstract class ChunkAccessMixin {
	@Shadow @Final protected ChunkPos chunkPos;
	private DummySection dummySection_z;

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
			return this.dummySection_z;
		}
	}

	@Inject(at = @At("RETURN"), method = "<init>")
	private void addDummy(ChunkPos chunkPos, UpgradeData upgradeData, LevelHeightAccessor levelHeightAccessor, Registry biomeRegistry, long inhabitedTime, LevelChunkSection[] sections, BlendingData blendingData, CallbackInfo ci) {
		this.dummySection_z = new DummySection(biomeRegistry);
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
