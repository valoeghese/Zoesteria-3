package nz.valoeghese.zoesteria.worldgen;

import net.minecraft.world.level.Level;

public class ZoesteriaWorldGenerator {

	public ZoesteriaWorldGenerator(long seed) {
		this.seed = seed;
	}

	private final long seed;

	/**
	 * Whether the Zoesteria World Generator is currently active as the overworld generator. This affects the world height
	 * in the overworld.
	 * This is required as we cannot inject early into the ServerLevel constructor, when world height information is first needed.
	 */
	public static boolean active;
	public static int SEA_LEVEL = 32;
	public static int HEIGHT = 512;

	public static boolean isZoesteriaWorld(Level level) {
		return active && level.dimension() == Level.OVERWORLD;
	}
}
