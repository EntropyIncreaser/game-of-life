package com.huiming.gameoflife;

import com.huiming.gameoflife.util.ElementNotFoundException;
import com.huiming.gameoflife.util.GridElement;
import com.huiming.gameoflife.util.GridPos;

import java.util.HashMap;
import java.util.Map;

public class Grid {
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
}
