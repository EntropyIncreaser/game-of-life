package org.huajistudio.gameoflife.components.grid;

/**
 * A position in a Grid.
 * @author Lasm_Gratel
 */
public class GridPos {
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
}
