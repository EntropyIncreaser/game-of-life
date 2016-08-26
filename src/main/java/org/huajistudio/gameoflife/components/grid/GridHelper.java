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

import com.google.common.collect.Lists;
import org.huajistudio.gameoflife.api.GameOfLifeAPI;
import org.huajistudio.gameoflife.api.components.grid.Grid;
import org.huajistudio.gameoflife.api.components.grid.GridPos;

import java.util.Arrays;
import java.util.List;

/**
 * Useful functions for a Grid.
 * @author Lasm_Gratel
 */
public class GridHelper {
	/**
	 * Get the nearby(8 cells) cells' amount.
	 * @param grid the grid
	 * @param pos the position to the element in the grid
     * @return the amount
     */
	public static int getNearbyCellAmount(Grid grid, GridPos pos) {
		List<GridPos> poses = Lists.newArrayList(pos.left().up(),
			pos.left(),
			pos.left().down(),
			pos.down(),
			pos.right().down(),
			pos.right(),
			pos.right().up(),
			pos.up()
		);

		GameOfLifeAPI.LOGGER.info(Arrays.toString(poses.toArray()));

		int i = 0;
		for (GridPos pos1 : poses) {
			if (grid.getElement(pos1).getValue())
				i++;
		}

		return i;
	}

	/**
	 * Swap the element in a grid.
	 * @param grid the grid
	 * @param pos the position to the element in the grid
	 * @return the grid that contains swapped element
     */
	public static Grid swap(Grid grid, GridPos pos) {
		grid.replaceElement(pos, grid.getElement(pos), grid.getElement(pos).swap());
		return grid;
	}

	/**
	 * Resize a grid.
	 * The data that out of the bounds will be despot.
	 * @param grid grid to resize
	 * @param width new grid's width
	 * @param height new grid's height
     * @return grid which has been resized
     */
	public static Grid resize(Grid grid, int width, int height) {
		Grid newGrid = new Grid(width, height);
		grid.keySet().stream().filter(pos -> pos.getX() < width && pos.getY() < height).forEach(pos -> newGrid.setElement(pos, grid.getElement(pos)));
		return newGrid;
	}
}
