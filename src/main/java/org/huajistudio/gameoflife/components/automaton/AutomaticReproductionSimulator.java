package org.huajistudio.gameoflife.components.automaton;

import org.huajistudio.gameoflife.components.grid.Grid;
import org.huajistudio.gameoflife.components.grid.GridHelper;
import org.huajistudio.gameoflife.components.grid.GridPos;

import static org.huajistudio.gameoflife.util.GameRule.reproductionAmount;

public class AutomaticReproductionSimulator implements IAutomaticComponent {
	@SubscribeAutomaton
	public void reproduction(Grid grid) {
		for (GridPos pos : grid.keySet()) {
			if (GridHelper.getNearbyCellAmount(grid, pos) == reproductionAmount && (!grid.getElement(pos).getValue()))
				grid.setElement(pos, grid.getElement(pos).setValue(true).setRgba(new double[]{0.0f, 0.0f, 0.0f, 0.0f}));
		}
	}
}
