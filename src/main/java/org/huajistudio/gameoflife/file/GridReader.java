package org.huajistudio.gameoflife.file;

import com.google.gson.Gson;
import org.apache.commons.io.FileUtils;
import org.huajistudio.gameoflife.components.cell.Cell;
import org.huajistudio.gameoflife.components.grid.Grid;
import org.huajistudio.gameoflife.components.grid.GridManager;
import org.huajistudio.gameoflife.components.grid.GridObj;
import org.huajistudio.gameoflife.components.grid.GridPos;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import static org.huajistudio.gameoflife.GameOfLife.LOGGER;

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
		if (file.toString().endsWith("gz")) {
			try {
				GridObj gridObj = gson.fromJson(new InputStreamReader(new GZIPInputStream(new FileInputStream(file)), StandardCharsets.UTF_8), GridObj.class);
				Grid grid = new Grid(gridObj.getWidth(), gridObj.getHeight());
				for (GridObj.GridPosElement element : gridObj.getData()) {
					grid.setElement(element.getPosition(), element.getElement());
				}
				GridManager.addGrid(grid);
				return grid;
			} catch (Exception e) {
				LOGGER.error(e);
			}
		}

		try {
			GridObj gridObj = gson.fromJson(FileUtils.readFileToString(file, Charset.forName("UTF-8")), GridObj.class);
			Grid grid = new Grid(gridObj.getWidth(), gridObj.getHeight());
			for (GridObj.GridPosElement element : gridObj.getData()) {
				grid.setElement(element.getPosition(), element.getElement());
			}
			GridManager.addGrid(grid);
			return grid;
		} catch (Exception e) {
			LOGGER.error(e);
		}
		return null;
	}

	public static void saveGrid(File file, Grid grid) {
		Gson gson = new Gson();
		try {
			if(file.exists())
				file.delete();
			file.createNewFile();
			GridObj obj = new GridObj();
			List<GridObj.GridPosElement> elementList = new ArrayList<>();
			for(GridPos pos : grid.keySet()) {
				Cell element = grid.getElement(pos);
				GridObj.GridPosElement posElement = new GridObj.GridPosElement();
				posElement.setPosition(pos);
				posElement.setElement(element);
				elementList.add(posElement);
			}
			obj.setData(elementList.toArray(new GridObj.GridPosElement[0]));
			obj.setWidth(grid.getWidth());
			obj.setHeight(grid.getHeight());
			StringWriter jsonStrWriter = new StringWriter();
			gson.toJson(obj, jsonStrWriter);
			String json = jsonStrWriter.toString();
			if (file.toString().endsWith("gz")) {
				try (GZIPOutputStream gzipOutputStream = new GZIPOutputStream(new FileOutputStream(file))) {
					gzipOutputStream.write(json.getBytes(StandardCharsets.UTF_8));
					gzipOutputStream.flush();
				} catch (Exception e) {
					LOGGER.error(e);
				}
			} else
				new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8).append(json).flush();
		} catch (Exception e) {
			LOGGER.error(e);
		}
	}
}
