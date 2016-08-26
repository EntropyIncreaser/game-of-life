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
package org.huajistudio.gameoflife.components.grid;

import org.huajistudio.gameoflife.api.components.cell.Cell;
import org.huajistudio.gameoflife.api.components.grid.GridPos;

import java.util.Arrays;

public class GridObj {
	private int width, height;
	private GridPosElement[] data;

	public static class GridPosElement {
		private GridPos position;
		private Cell element;

		public Cell getElement() {
			return element;
		}

		public void setElement(Cell element) {
			this.element = element;
		}

		public GridPos getPosition() {
			return position;
		}

		public void setPosition(GridPos position) {
			this.position = position;
		}

		@Override
		public String toString() {
			return "GridPosElement{" +
				"element=" + element +
				", position=" + position +
				'}';
		}
	}

	public GridPosElement[] getData() {
		return data;
	}

	public void setData(GridPosElement[] data) {
		this.data = data;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	@Override
	public String toString() {
		return "GridObj{" +
			"data=" + Arrays.toString(data) +
			", width=" + width +
			", height=" + height +
			'}';
	}
}
