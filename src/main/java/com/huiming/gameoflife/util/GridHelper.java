package com.huiming.gameoflife.util;

import com.huiming.gameoflife.Grid;

/**
 * Useful functions for a Grid.
 * @author Lasm_Gratel
 */
public class GridHelper {
	public void set(int x, int y, boolean type) {
		gridMatrix[x][y] = type;
	}

	public void swap(int x, int y) {
		gridMatrix[x][y] = !gridMatrix[x][y];
	}

	public void copy(int x, int y, Grid grid) {
		for (int i = 0; i < grid.width; i++) {
			System.arraycopy(grid.gridMatrix[i], 0, this.gridMatrix[i + x], y, grid.height);
		}
	}

	public Grid cut(int fromX, int toX, int fromY, int toY) {
		Grid ret = new Grid(toX - fromX + 1, toY - fromY + 1);
		for (int i = 0; i < ret.width; i++) {
			System.arraycopy(gridMatrix[i + fromX], fromY, ret.gridMatrix[i], 0, ret.height);
		}
		return ret;
	}
}
