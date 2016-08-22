package org.huajistudio.gameoflife.event;

import org.huajistudio.gameoflife.components.cell.Cell;
import org.huajistudio.gameoflife.components.grid.GridPos;

/**
 * An abstract class which contains the event of cells.
 */
public abstract class CellEvent implements IEvent {
	private Cell cell;
	private GridPos position;

	public CellEvent(Cell cell, GridPos position) {
		this.cell = cell;
		this.position = position;
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

	/**
	 * Perform when a cell has been created.
	 */
	public static class CellCreatedEvent extends CellEvent {
		public CellCreatedEvent(Cell cell, GridPos position) {
			super(cell, position);
		}
	}

	/**
	 * Perform when a cell has been killed.
	 */
	public static class CellKilledEvent extends CellEvent {
		public CellKilledEvent(Cell cell, GridPos position) {
			super(cell, position);
		}
	}
}
