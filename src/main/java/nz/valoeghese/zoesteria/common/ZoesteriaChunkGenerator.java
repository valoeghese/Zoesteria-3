package nz.valoeghese.zoesteria.common;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.server.level.WorldGenRegion;
import net.minecraft.world.level.*;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeManager;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkAccess;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.*;
import net.minecraft.world.level.levelgen.blending.Blender;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

public class ZoesteriaChunkGenerator extends ChunkGenerator {
	public static final Codec<ZoesteriaChunkGenerator> CODEC = RecordCodecBuilder.create(
			instance -> instance.group(
							BiomeSource.CODEC.fieldOf("biome_source").forGetter(generator -> generator.biomeSource),
							NoiseGeneratorSettings.CODEC.fieldOf("settings").forGetter(generator -> generator.settings)
					)
					.apply(instance, instance.stable(ZoesteriaChunkGenerator::new))
	);

	public ZoesteriaChunkGenerator(BiomeSource biomeSource, Holder<NoiseGeneratorSettings> settings) {
		super(biomeSource);
		this.settings = settings;
	}

	private final Holder<NoiseGeneratorSettings> settings;

	// World Generation
	@Override
	public int getBaseHeight(int x, int z, Heightmap.Types type, LevelHeightAccessor level, RandomState random) {
		return 0;
	}

	@Override
	public NoiseColumn getBaseColumn(int x, int z, LevelHeightAccessor height, RandomState random) {
		BlockState[] states = new BlockState[this.getGenDepth()];
		Arrays.fill(states, Blocks.AIR.defaultBlockState());
		return new NoiseColumn(this.getMinY(), states);
	}

	@Override
	public CompletableFuture<ChunkAccess> fillFromNoise(Executor executor, Blender blender, RandomState random, StructureManager structureManager, ChunkAccess chunk) {
		return CompletableFuture.completedFuture(chunk);
	}

	@Override
	public void buildSurface(WorldGenRegion level, StructureManager structureManager, RandomState random, ChunkAccess chunk) {
	}

	@Override
	public void spawnOriginalMobs(WorldGenRegion level) {
		if (!this.settings.value().disableMobGeneration()) {
			ChunkPos chunkPos = level.getCenter();
			Holder<Biome> holder = level.getBiome(chunkPos.getWorldPosition().atY(level.getMaxBuildHeight() - 1));
			WorldgenRandom worldgenRandom = new WorldgenRandom(new LegacyRandomSource(RandomSupport.generateUniqueSeed()));
			worldgenRandom.setDecorationSeed(level.getSeed(), chunkPos.getMinBlockX(), chunkPos.getMinBlockZ());
			NaturalSpawner.spawnMobsForChunkGeneration(level, holder, chunkPos, worldgenRandom);
		}
	}

	// Unused Generation Methods
	@Override
	public void applyCarvers(WorldGenRegion level, long seed, RandomState random, BiomeManager biomeManager, StructureManager structureManager, ChunkAccess chunk, GenerationStep.Carving step) {
	}

	// Metadata and Serialisation
	@Override
	protected Codec<? extends ChunkGenerator> codec() {
		return CODEC;
	}


	@Override
	public void addDebugScreenInfo(List<String> info, RandomState random, BlockPos pos) {
		info.add("Zoesteria Chunk Generator");
	}

	@Override
	public int getGenDepth() {
		return (this.settings.value()).noiseSettings().height();
	}

	@Override
	public int getSeaLevel() {
		return (this.settings.value()).seaLevel();
	}

	@Override
	public int getMinY() {
		return (this.settings.value()).noiseSettings().minY();
	}
}
