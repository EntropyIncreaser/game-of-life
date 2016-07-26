package sample;

import java.io.*;
import java.util.Objects;

public class Grid {
	public boolean[][] grid;
	public int width, height;

	static Grid openFromFile(File file) throws IOException {
		try (FileReader in = new FileReader(file)) {
			BufferedReader br = new BufferedReader(in);
			String[] s = br.readLine().split(" ");
			boolean[] data = new boolean[s.length];
			for (int i = 0; i < data.length; i++) {
				if (Objects.equals(s[i], "1")) {
					data[i] = true;
				}
			}
			Grid ret = new Grid((int) Math.sqrt(data.length), (int) Math.sqrt(data.length));
			int cnt = 0;
			for (int i = 0; i < ret.width; i++) {
				for (int j = 0; j < ret.height; j++) {
					ret.grid[i][j] = data[cnt++];
				}
			}
			return ret;
		} catch (IOException e) {
			throw e;
		}
	}

	public Grid(int width, int height) {
		this.width = width;
		this.height = height;
		grid = new boolean[height][width];
	}

	public void set(int x, int y, boolean type) {
		grid[x][y] = type;
	}

	public void swap(int x, int y) {
		grid[x][y] = !grid[x][y];
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
