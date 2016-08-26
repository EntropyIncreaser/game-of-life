package org.huajistudio.gameoflife.components.automaton;

import org.huajistudio.gameoflife.components.grid.Grid;
import org.huajistudio.gameoflife.components.grid.GridHelper;
import org.huajistudio.gameoflife.components.grid.GridPos;

import static org.huajistudio.gameoflife.GameOfLife.LOGGER;
import static org.huajistudio.gameoflife.util.GameRule.overPopulationAmount;
import static org.huajistudio.gameoflife.util.GameRule.underPopulationAmount;

/**
 * Makes a qualified cell which can simulate the "under/over population" in the real world.
 */
public class AutomaticPopulationSimulator implements IAutomaticComponent {
	@SubscribeAutomaton
	public void underPopulation(Grid grid) {
		for (GridPos pos : grid.keySet()) {
			if (GridHelper.getNearbyCellAmount(grid, pos) < underPopulationAmount && grid.getElement(pos).getValue()) {
				LOGGER.info("Under Population Detected " + pos + ", Nearby:" + GridHelper.getNearbyCellAmount(grid, pos));
				grid.setElement(pos, grid.getElement(pos).setValue(false).setRgba(new double[]{1.0f, 1.0f, 1.0f, 1.0f}));
			}
		}
	}

	@SubscribeAutomaton
	public void overPopulation(Grid grid) {
		for (GridPos pos : grid.keySet()) {
			if (GridHelper.getNearbyCellAmount(grid, pos) > overPopulationAmount && grid.getElement(pos).getValue())
				grid.setElement(pos, grid.getElement(pos).setValue(false).setRgba(new double[]{1.0f, 1.0f, 1.0f, 1.0f}));
		}
	}
}
