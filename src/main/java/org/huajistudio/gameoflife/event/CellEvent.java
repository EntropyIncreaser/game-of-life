package org.huajistudio.gameoflife.event;

import org.huajistudio.gameoflife.components.cell.Cell;
import org.huajistudio.gameoflife.components.grid.Grid;
import org.huajistudio.gameoflife.components.grid.GridPos;

/**
 * An abstract class which contains the event of cells.
 */
public abstract class CellEvent implements IEvent {
	private Grid grid;
	private Cell cell;
	private GridPos position;

	public CellEvent(Grid grid, GridPos position, Cell cell) {
		this.grid = grid;
		this.position = position;
		this.cell = cell;
	}

	public Cell getCell() {
		return cell;
	}

	public CellEvent setCell(Cell cell) {
		this.cell = cell;
		return this;
	}

	public GridPos getPosition() {
		return position;
	}

	public CellEvent setPosition(GridPos position) {
		this.position = position;
		return this;
	}

	public Grid getGrid() {
		return grid;
	}

	public CellEvent setGrid(Grid grid) {
		this.grid = grid;
		return this;
	}

	/**
	 * Perform when a cell has been created.
	 */
	public static class CellCreatedEvent extends CellEvent {
		public CellCreatedEvent(Grid grid, GridPos position, Cell cell) {
			super(grid, position, cell);
		}
	}

	/**
	 * Perform when a cell has been killed.
	 */
	public static class CellKilledEvent extends CellEvent {
		public CellKilledEvent(Grid grid, GridPos position, Cell cell) {
			super(grid, position, cell);
		}
	}
}
