package com.huiming.gameoflife;

import com.huiming.gameoflife.file.GridReader;
import com.huiming.gameoflife.gui.Editor;
import com.huiming.gameoflife.gui.Welcome;
import com.huiming.gameoflife.util.I18n;
import com.huiming.gameoflife.util.grid.Grid;
import javafx.application.Application;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.util.Locale;

public class GameOfLife extends Application {
	public static final Logger LOGGER = LogManager.getLogger("GameOfLife");
	public static Locale locale = Locale.getDefault();

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Welcome wel = new Welcome();
		Editor editor = new Editor(20, 20);
		wel.start(primaryStage);
		wel.newLifeButton.setOnAction(event -> {
			// TODO: select width
			primaryStage.hide();
			try {
				editor.start(primaryStage);
			} catch (Exception e) {
				GameOfLife.LOGGER.error(e);
			}
		});

		wel.openLifeButton.setOnAction(event -> {
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle(I18n.parse("window.jsonchooser.title"));
			fileChooser.getExtensionFilters().addAll(
				new FileChooser.ExtensionFilter(I18n.parse("window.jsonchooser.extension.all"), "*.*"),
				new FileChooser.ExtensionFilter(I18n.parse("window.jsonchooser.extension.json"), "*.json")
			);
			fileChooser.setInitialFileName("grid.json");
			File file = fileChooser.showOpenDialog(primaryStage);
			primaryStage.hide();
			Grid grid;
			try {
				grid = GridReader.readGrid(file);
				editor.setWorldGrid(grid);
				editor.start(primaryStage);
			} catch (Exception e) {
				GameOfLife.LOGGER.error(e);
			}
		});
	}
}
