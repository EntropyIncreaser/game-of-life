package com.huiming.gameoflife;

public class Grid {
	public boolean[][] gridMatrix;
	public int width, height;

	public Grid(int width, int height) {
		this.width = width;
		this.height = height;
		gridMatrix = new boolean[height][width];
	}


}
