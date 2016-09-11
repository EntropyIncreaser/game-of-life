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

/**
 * A position in a Grid.
 * @author Lasm_Gratel
 */
public class GridPos implements Comparable<GridPos> {
	private int x, y;

	public GridPos() {
		this(0, 0);
	}

	public GridPos(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public GridPos up() {
		return up(1);
	}

	public GridPos up(int x) {
		y += x;
		return this;
	}

	public GridPos down() {
		return down(1);
	}

	public GridPos down(int x) {
		y -= x;
		return this;
	}

	public GridPos left() {
		return left(1);
	}

	public GridPos left(int x) {
		this.x -= x;
		return this;
	}

	public GridPos right() {
		return right(1);
	}

	public GridPos right(int x) {
		this.x += x;
		return this;
	}

	public int getX() {
		return x;
	}

	public GridPos setX(int x) {
		this.x = x;
		return this;
	}

	public int getY() {
		return y;
	}

	public GridPos setY(int y) {
		this.y = y;
		return this;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		GridPos gridPos = (GridPos) o;

		if (x != gridPos.x) return false;
		return y == gridPos.y;

	}

	@Override
	public int hashCode() {
		int result = x;
		result = 31 * result + y;
		return result;
	}

	@Override
	public String toString() {
		return "GridPos{" +
			"x=" + x +
			", y=" + y +
			'}';
	}

	@Override
	public int compareTo(GridPos o) {
		switch(Integer.compare(x, o.x)) {
			case -1:
				return -1;
			case 0:
				return Integer.compare(y, o.y);
			case 1:
				return 1;
			default:
				return 0;
		}
	}
}
