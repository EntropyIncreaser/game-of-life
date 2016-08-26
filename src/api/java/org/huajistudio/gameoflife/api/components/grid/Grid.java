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
package org.huajistudio.gameoflife.api.components.grid;

import org.huajistudio.gameoflife.components.grid.EnumGridCelluloidAutomation;
import org.huajistudio.gameoflife.api.event.CellEvent;
import org.huajistudio.gameoflife.api.event.GridEvent;
import org.huajistudio.gameoflife.api.util.ElementNotFoundException;
import javafx.scene.paint.Color;
import org.huajistudio.gameoflife.api.components.cell.Cell;

import java.util.*;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import static org.huajistudio.gameoflife.api.GameOfLifeAPI.EVENT_MANAGER;

public class Grid implements Iterable<Cell> {
	private Map<GridPos, Cell> matrix = new ConcurrentHashMap<GridPos, Cell>();
	private int width, height;

	public Grid(int width, int height) {
		this.width = width;
		this.height = height;
		init();
	}

	private void init() {
		EVENT_MANAGER.executeEvent(new GridEvent.GridCreatedEvent(this));
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				Cell cell = new Cell().setValue(false).setRgba(new double[]{Color.WHITE.getRed(),Color.WHITE.getGreen(),Color.WHITE.getBlue(),Color.WHITE.getOpacity()});
				matrix.put(new GridPos(i, j), cell);
				EVENT_MANAGER.executeEvent(new CellEvent.CellCreatedEvent(this, new GridPos(i, j), cell));
			}
		}
	}

	public int getHeight() {
		return height;
	}

	public Grid setHeight(int height) {
		this.height = height;
		return this;
	}

	public int getWidth() {
		return width;
	}

	public Grid setWidth(int width) {
		this.width = width;
		return this;
	}

	public Cell getElement(GridPos pos) {
		if (pos.getX() > width || pos.getX() < 0 || pos.getY() > height || pos.getY() < 0)
			throw new ArrayIndexOutOfBoundsException("Position out of range");
		else if (matrix.containsKey(pos))
			return matrix.get(pos);
		else
			throw new ElementNotFoundException("Element not found in matrix");
	}

	public Grid setElement(GridPos pos, Cell element) {
		if (pos.getX() > width || pos.getX() < 0 || pos.getY() > height || pos.getY() < 0)
			throw new ArrayIndexOutOfBoundsException("Position out of range");
		else
			matrix.put(pos, element);
		return this;
	}

	public Grid removeElement(GridPos pos) {
		if (pos.getX() > width || pos.getX() < 0 || pos.getY() > height || pos.getY() < 0)
			throw new ArrayIndexOutOfBoundsException("Position out of range");
		else
			matrix.remove(pos);
		return this;
	}

	public Grid replaceElement(GridPos pos, Cell x, Cell y) {
		if (pos.getX() > width || pos.getX() < 0 || pos.getY() > height || pos.getY() < 0)
			throw new ArrayIndexOutOfBoundsException("Position out of range");
		else
			matrix.replace(pos, x, y);
		return this;
	}

	public boolean hasElement(GridPos pos) {
		return matrix.containsKey(pos);
	}

	public Grid purge() {
		matrix.clear();
		return this;
	}

	public Set<GridPos> keySet() {
		return matrix.keySet();
	}

	public void evolveInPlace(EnumGridCelluloidAutomation enumGridCelluloidAutomation) {
		Map<GridPos, Integer> count = new HashMap<>();
		Map<GridPos, Cell> newGrid = new HashMap<>();
		int offsetX, offsetY, x, y;

		for (Entry<GridPos, Cell> elementEntry :
			matrix.entrySet()) {
			if (elementEntry.getKey().getX() >= width ||
			    elementEntry.getKey().getY() >= height ||
			   !elementEntry.getValue().getValue()) {
				continue;
			}
			for (offsetX = -1; offsetX <= 1; offsetX++) {
				for (offsetY = -1; offsetY <= 1; offsetY++) {
					if (offsetX == 0 && offsetY == 0) {
						continue;
					}
					x = elementEntry.getKey().getX() + offsetX;
					y = elementEntry.getKey().getY() + offsetY;
					if (!enumGridCelluloidAutomation.getSpaceType() &&
					    x >= 0 && x < width && y >= 0 && y < height) {
						continue;
					}
					x = (x + width) % width;
					y = (y + height) % height;
					if (count.containsKey(new GridPos(x, y))) {
						count.put(new GridPos(x, y), count.get(new GridPos(x, y)) + 1);
					} else {
						count.put(new GridPos(x, y), 1);
					}
				}
			}
		}
		matrix.clear();
		init();
		for (Entry<GridPos, Integer> countEntry :
			count.entrySet()) {
//			TODO WTF
//			if (enumGridCelluloidAutomation.couldStay(countEntry.getValue()) &&
//			    matrix.get(countEntry.getKey()) ||
//				enumGridCelluloidAutomation.couldBorn(countEntry.getValue()) &&
//				matrix.get(countEntry.getKey())) {
//				matrix.put(countEntry.getKey(), true);
//			}
		}
	}

	@Override
	public Iterator<Cell> iterator() {
		return matrix.values().iterator();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Grid that = (Grid) o;

		if (width != that.width) return false;
		if (height != that.height) return false;
		return matrix.equals(that.matrix);

	}

	@Override
	public int hashCode() {
		int result = matrix.hashCode();
		result = 31 * result + width;
		result = 31 * result + height;
		return result;
	}
}