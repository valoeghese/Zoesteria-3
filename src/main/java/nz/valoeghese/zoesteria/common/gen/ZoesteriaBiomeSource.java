package nz.valoeghese.zoesteria.common.gen;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.biome.Climate;

import java.util.stream.Stream;

public class ZoesteriaBiomeSource extends BiomeSource {
	public static final Codec<ZoesteriaBiomeSource> CODEC = RecordCodecBuilder.create(
			instance -> instance.group(
							Codec.LONG.fieldOf("seed").forGetter(generator -> generator.seed)
					)
					.apply(instance, instance.stable(ZoesteriaBiomeSource::new)));

	/**
	 * Construct a new Zoesteria Biome Source.
	 */
	public ZoesteriaBiomeSource(long seed) {
		this.seed = seed;
	}

	private final long seed;

	@Override
	protected Codec<? extends BiomeSource> codec() {
		return CODEC;
	}

	@Override
	protected Stream<Holder<Biome>> collectPossibleBiomes() {
		return Stream.of();
	}

	@Override
	public Holder<Biome> getNoiseBiome(int quartX, int quartY, int quartZ, Climate.Sampler sampler) {
		return null;
	}
}
