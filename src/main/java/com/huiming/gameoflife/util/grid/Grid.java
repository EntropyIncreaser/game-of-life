package com.huiming.gameoflife.util.grid;

import com.huiming.gameoflife.util.ElementNotFoundException;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Grid implements Iterable<GridElement> {
	private Map<GridPos, GridElement> matrix = new HashMap<>();
	private int width, height;

	public Grid(int width, int height) {
		this.width = width;
		this.height = height;
		init();
	}

	private void init() {
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				matrix.put(new GridPos(i, j), (GridElement) new GridElement().setValue(false));
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

	public GridElement getElement(GridPos pos) {
		if (pos.getX() > width || pos.getX() < 0 || pos.getY() > height || pos.getY() < 0)
			throw new ArrayIndexOutOfBoundsException("Position out of range");
		else if (matrix.containsKey(pos))
			return matrix.get(pos);
		else
			throw new ElementNotFoundException("Element not found in matrix");
	}

	public Grid setElement(GridPos pos, GridElement element) {
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

	public Grid replaceElement(GridPos pos, GridElement x, GridElement y) {
		if (pos.getX() > width || pos.getX() < 0 || pos.getY() > height || pos.getY() < 0)
			throw new ArrayIndexOutOfBoundsException("Position out of range");
		else
			matrix.replace(pos, x, y);
		return this;
	}

	public Grid purge() {
		matrix.clear();
		return this;
	}

	public GridPos[] keyArray() {
		return (GridPos[]) matrix.keySet().toArray();
	}

	@Override
	public Iterator<GridElement> iterator() {
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
