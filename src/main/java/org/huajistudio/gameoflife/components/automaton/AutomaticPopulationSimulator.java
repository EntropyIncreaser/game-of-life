/*
 * A game inspired from Conway's Game Of Life.
 * Copyright (C) 2016 Huaji Studio.
 * <p>
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p>
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.huajistudio.gameoflife.components.automaton;

import org.huajistudio.gameoflife.api.components.automaton.IAutomaticComponent;
import org.huajistudio.gameoflife.api.components.automaton.SubscribeAutomaton;
import org.huajistudio.gameoflife.api.components.grid.Grid;
import org.huajistudio.gameoflife.components.grid.GridHelper;
import org.huajistudio.gameoflife.api.components.grid.GridPos;

import static org.huajistudio.gameoflife.api.GameOfLifeAPI.LOGGER;
import static org.huajistudio.gameoflife.api.util.GameRule.overPopulationAmount;
import static org.huajistudio.gameoflife.api.util.GameRule.underPopulationAmount;

/**
 * Makes a qualified cell which can simulate the "under/over population" in the real world.
 */
public class AutomaticPopulationSimulator implements IAutomaticComponent {
	@SubscribeAutomaton
	public Grid underPopulation(Grid grid) {
		for (GridPos pos : grid.keySet()) {
			if (GridHelper.getNearbyCellAmount(grid, pos) < underPopulationAmount && grid.getElement(pos).getValue()) {
				LOGGER.info("Under Population Detected " + pos + ", Nearby:" + GridHelper.getNearbyCellAmount(grid, pos));
				grid.setElement(pos, grid.getElement(pos).setValue(false).setRgba(new double[]{1.0f, 1.0f, 1.0f, 1.0f}));
			}
		}
		return grid;
	}

	@SubscribeAutomaton
	public Grid overPopulation(Grid grid) {
		for (GridPos pos : grid.keySet()) {
			if (GridHelper.getNearbyCellAmount(grid, pos) > overPopulationAmount && grid.getElement(pos).getValue())
				grid.setElement(pos, grid.getElement(pos).setValue(false).setRgba(new double[]{1.0f, 1.0f, 1.0f, 1.0f}));
		}
		return grid;
	}
}
