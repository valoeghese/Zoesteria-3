package nz.valoeghese.zoesteria.common;

import net.minecraft.core.Registry;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.LevelChunkSection;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;

public class DummySection extends LevelChunkSection {
	public DummySection(Registry<Biome> biomeRegistry) {
		super(biomeRegistry);
	}

	@Override
	public BlockState getBlockState(int x, int y, int z) {
		return Blocks.AIR.defaultBlockState();
	}

	@Override
	public FluidState getFluidState(int x, int y, int z) {
		return Fluids.EMPTY.defaultFluidState();
	}

	@Override
	public BlockState setBlockState(int x, int y, int z, BlockState state) {
		return state;
	}

	@Override
	public BlockState setBlockState(int x, int y, int z, BlockState state, boolean useLocks) {
		return state;
	}
}
