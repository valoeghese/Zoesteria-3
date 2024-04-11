package nz.valoeghese.zoesteria.common.gen;

import nz.valoeghese.zoesteria.worldgen.ZoesteriaWorldGenerator;

/**
 * Get the seed from RandomState.
 */
public interface WorldGeneratorAccess {
	ZoesteriaWorldGenerator zoesteriaWorldGenerator();
}
