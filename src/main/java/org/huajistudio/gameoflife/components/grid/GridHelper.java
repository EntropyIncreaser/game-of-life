package org.huajistudio.gameoflife.components.grid;

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
		GridPos[] poses = {
			pos.left().up(),
			pos.left(),
			pos.left().down(),
			pos.down(),
			pos.right().down(),
			pos.right(),
			pos.right().up(),
			pos.up()
		};

		int i = 0;

		for (GridPos pos1 : poses) {
			if (grid.hasElement(pos1) && grid.getElement(pos1).getValue())
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
