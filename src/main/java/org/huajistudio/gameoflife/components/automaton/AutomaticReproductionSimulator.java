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

import static org.huajistudio.gameoflife.api.util.GameRule.simpleRule;

public class AutomaticReproductionSimulator implements IAutomaticComponent {
	@SubscribeAutomaton
	public Grid reProduction(Grid grid) {
		for (GridPos pos : grid.keySet()) {
			if (simpleRule.couldBorn(GridHelper.getNearbyCellAmount(grid, pos)) && (!grid.getElement(pos).getValue()))
				grid.setElement(pos, grid.getElement(pos).setValue(true).setRgba(new double[]{0.0f, 0.0f, 0.0f, 0.0f}));
		}
		return grid;
	}
}
