package sample;

public class Grid {
	public boolean[][] grid;
	public int width, height;

	public Grid(int width, int height) {
		this.width = width;
		this.height = height;
		grid = new boolean[height][width];
	}

	public void copy(int x, int y, Grid grid) {
		for (int i = 0; i < grid.width; i++) {
			System.arraycopy(grid.grid[i], 0, this.grid[i + x], y, grid.height);
		}
	}

	public Grid cut(int fromX, int toX, int fromY, int toY) {
		Grid ret = new Grid(toX - fromX + 1, toY - fromY + 1);
		for (int i = 0; i < ret.width; i++) {
			System.arraycopy(grid[i + fromX], fromY, ret.grid[i], 0, ret.height);
		}
		return ret;
	}
}
