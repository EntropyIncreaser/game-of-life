package org.huajistudio.gameoflife.util.grid;

import java.util.Random;

/**
 * Move the grid automatically.
 */
public class GridCelluloidAutomating {
	private Random random;

	public void setSeed(long seed) {
		random.setSeed(seed);
	}
}
