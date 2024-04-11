package nz.valoeghese.zoesteria.common.gen;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.RegistryOps;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.Climate;

import java.util.stream.Stream;

public class ZoesteriaBiomeSource extends BiomeSource {
	public static final Codec<ZoesteriaBiomeSource> CODEC = RecordCodecBuilder.create(
			instance -> instance.group(RegistryOps.retrieveGetter(Registries.BIOME))
					.apply(instance, instance.stable(ZoesteriaBiomeSource::new)));

	/**
	 * Construct a new Zoesteria Biome Source.
	 */
	public ZoesteriaBiomeSource(HolderGetter<Biome> holderGetter) {
		this.ocean = holderGetter.getOrThrow(Biomes.OCEAN);
		this.plains = holderGetter.getOrThrow(Biomes.PLAINS);
		this.river = holderGetter.getOrThrow(Biomes.RIVER);
		this.desert = holderGetter.getOrThrow(Biomes.DESERT);
		this.forest = holderGetter.getOrThrow(Biomes.FOREST);
		this.mountains = holderGetter.getOrThrow(Biomes.JAGGED_PEAKS);
	}

	private final Holder<Biome> ocean;
	private final Holder<Biome> plains;
	private final Holder<Biome> river;
	private final Holder<Biome> desert;
	private final Holder<Biome> forest;
	private final Holder<Biome> mountains;

	@Override
	protected Codec<? extends BiomeSource> codec() {
		return CODEC;
	}

	@Override
	protected Stream<Holder<Biome>> collectPossibleBiomes() {
		return Stream.of(this.ocean, this.plains, this.river, this.desert, this.forest, this.mountains);
	}

	@Override
	public Holder<Biome> getNoiseBiome(int quartX, int quartY, int quartZ, Climate.Sampler sampler) {
		return this.mountains;
	}
}
