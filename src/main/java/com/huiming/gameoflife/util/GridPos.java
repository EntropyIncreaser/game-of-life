package com.huiming.gameoflife.util;

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
}
