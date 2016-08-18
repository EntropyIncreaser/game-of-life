package com.huiming.gameoflife.file;

import com.google.gson.Gson;
import com.huiming.gameoflife.GameOfLife;
import com.huiming.gameoflife.util.grid.Grid;
import com.huiming.gameoflife.util.grid.GridManager;
import com.huiming.gameoflife.util.grid.GridObj;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.nio.charset.Charset;

/**
 * Reads Grids from the file.
 * @author Lasm_Gratel
 */
public class GridReader {
	/**
	 * Read a grid from the file.
	 * @param file a valid json file
	 * @return grid or null
     */
	public static Grid readGrid(File file) {
		Gson gson = new Gson();
		try {
			GridObj gridObj = gson.fromJson(FileUtils.readFileToString(file, Charset.forName("UTF-8")), GridObj.class);
			Grid grid = new Grid(gridObj.getWidth(), gridObj.getHeight());
			for (GridObj.GridPosElement element : gridObj.getData()) {
				grid.setElement(element.getPosition(), element.getElement());
			}
			GridManager.addGrid(grid);
			return grid;
		} catch (Exception e) {
			GameOfLife.LOGGER.error(e);
		}
		return null;
	}

	public static void saveGrid(File file) {
		Gson gson = new Gson();
		try {
			GridObj obj = new GridObj();
		} catch (Exception e) {
			GameOfLife.LOGGER.error(e);
		}
	}
}
