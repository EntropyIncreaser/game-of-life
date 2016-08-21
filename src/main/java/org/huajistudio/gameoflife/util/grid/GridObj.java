package org.huajistudio.gameoflife.util.grid;

import java.util.Arrays;

public class GridObj {
	private int width, height;
	private GridPosElement[] data;

	public static class GridPosElement {
		private GridPos position;
		private GridElement element;

		public GridElement getElement() {
			return element;
		}

		public void setElement(GridElement element) {
			this.element = element;
		}

		public GridPos getPosition() {
			return position;
		}

		public void setPosition(GridPos position) {
			this.position = position;
		}

		@Override
		public String toString() {
			return "GridPosElement{" +
				"element=" + element +
				", position=" + position +
				'}';
		}
	}

	public GridPosElement[] getData() {
		return data;
	}

	public void setData(GridPosElement[] data) {
		this.data = data;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	@Override
	public String toString() {
		return "GridObj{" +
			"data=" + Arrays.toString(data) +
			", width=" + width +
			", height=" + height +
			'}';
	}
}
