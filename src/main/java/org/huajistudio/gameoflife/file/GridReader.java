package org.huajistudio.gameoflife.file;

import com.google.gson.Gson;
import org.huajistudio.gameoflife.GameOfLife;
import org.apache.commons.io.FileUtils;
import org.huajistudio.gameoflife.components.cell.Cell;
import org.huajistudio.gameoflife.components.grid.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

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
			gson.toJson(obj, new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8));
		} catch (Exception e) {
			GameOfLife.LOGGER.error(e);
		}
	}
}
