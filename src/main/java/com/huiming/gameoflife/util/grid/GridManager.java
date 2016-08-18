package com.huiming.gameoflife.util.grid;

import java.util.ArrayList;
import java.util.List;

/**
 * Manage the grids.
 * @author Lasm_Gratel
 */
public class GridManager {
	private static List<Grid> gridList = new ArrayList<>();

	public static void addGrid(Grid grid) {
		gridList.add(grid);
	}
}
