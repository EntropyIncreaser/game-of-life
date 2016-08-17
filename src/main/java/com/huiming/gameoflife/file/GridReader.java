package com.huiming.gameoflife.file;

import com.google.gson.Gson;
import com.huiming.gameoflife.GameOfLife;
import com.huiming.gameoflife.Grid;
import com.huiming.gameoflife.GridManager;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.nio.charset.Charset;

/**
 * Reads Grids from the file.
 * @author Lasm_Gratel
 */
public class GridReader {
	public static void readGrid(File file) {
		Gson gson = new Gson();
		try {
			GridManager.addGrid(gson.fromJson(FileUtils.readFileToString(file, Charset.forName("UTF-8")), Grid.class));
		} catch (Exception e) {
			GameOfLife.LOGGER.error(e);
		}
	}
}
