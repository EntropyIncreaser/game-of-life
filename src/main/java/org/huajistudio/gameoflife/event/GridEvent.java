package org.huajistudio.gameoflife.event;

import org.huajistudio.gameoflife.components.grid.Grid;

public class GridEvent implements IEvent {
	private Grid grid;

	public GridEvent(Grid grid) {
		this.grid = grid;
	}

	public Grid getGrid() {
		return grid;
	}

	public GridEvent setGrid(Grid grid) {
		this.grid = grid;
		return this;
	}

	public static class GridCreatedEvent extends GridEvent {
		public GridCreatedEvent(Grid grid) {
			super(grid);
		}
	}

}
