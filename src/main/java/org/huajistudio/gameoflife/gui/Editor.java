/*
 * A game inspired from Conway's Game Of Life.
 * Copyright (C) 2016 Huaji Studio.
 * <p>
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p>
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.huajistudio.gameoflife.gui;

import com.jfoenix.controls.JFXButton;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.huajistudio.gameoflife.GameOfLife;
import org.huajistudio.gameoflife.api.GameOfLifeAPI;
import org.huajistudio.gameoflife.api.components.grid.Grid;
import org.huajistudio.gameoflife.api.components.grid.GridPos;
import org.huajistudio.gameoflife.components.automaton.AutomatonThread;
import org.huajistudio.gameoflife.file.GridReader;
import org.huajistudio.gameoflife.gui.controller.EditorController;

import java.io.File;
import java.util.Objects;

import static org.huajistudio.gameoflife.api.GameOfLifeAPI.LOGGER;

public class Editor extends Application {
	private Grid worldGrid;
	private AutomatonThread thread;

	private EditorController controller;
	public Canvas worldCanvas;
	public JFXButton playButton, saveButton, backButton;

	public Grid getWorldGrid() {
		return worldGrid;
	}

	public void setWorldGrid(Grid worldGrid) {
		this.worldGrid = worldGrid;
	}

	@Override
	public void stop() throws Exception {
		thread.stop();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		thread = new AutomatonThread(worldGrid);
		controller = new EditorController();
		worldCanvas = controller.getFXMLNode("worldCanvas");
		playButton = controller.getFXMLNode("playButton");
		saveButton = controller.getFXMLNode("saveButton");
		backButton = controller.getFXMLNode("backButton");

		drawGrid(worldCanvas, worldGrid, Color.BLACK);

		playButton.setOnAction(event -> {
			drawGrid(worldCanvas, worldGrid, Color.BLACK);
			if (Objects.equals(playButton.getText(), GameOfLifeAPI.I18N.parse("window.editor.button.start.label"))) {
				playButton.setText(GameOfLifeAPI.I18N.parse("window.editor.button.stop.label"));
				thread.resume();
				new Thread(thread).start();
				LOGGER.info("Automaton Thread Started");
			} else {
				playButton.setText(GameOfLifeAPI.I18N.parse("window.editor.button.start.label"));
				thread.stop();
				LOGGER.info("Automaton Thread Stopped");
			}
		});

		saveButton.setOnAction(event -> {
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle(GameOfLifeAPI.I18N.parse("window.jsonsaver.title"));
			fileChooser.getExtensionFilters().addAll(
				new FileChooser.ExtensionFilter(GameOfLifeAPI.I18N.parse("extension.json"), "*.json"),
				new FileChooser.ExtensionFilter(GameOfLifeAPI.I18N.parse("extension.gz"), "*.json.gz")
			);
			fileChooser.setInitialFileName("grid.json");
			File file = fileChooser.showSaveDialog(primaryStage);
			try {
				GridReader.saveGrid(file, worldGrid);
			} catch (Exception e) {
				LOGGER.error(e);
			}
		});

		backButton.setOnAction(event -> {
			try {
				GameOfLife.WELCOME.start(primaryStage);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});

		primaryStage.setScene(new Scene(controller));
		primaryStage.setTitle(GameOfLifeAPI.I18N.parse("window.editor.title"));
		primaryStage.show();
	}

	public Editor(int width, int height) {
		worldGrid = new Grid(width, height);
	}

	public void drawGrid(Canvas canvas, Grid grid, Color color) {
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
		gc.setFill(color);
		gc.setStroke(color);

		int i, j;

		for (i = 0; i <= grid.getWidth(); i++) {
			gc.strokeLine(i * 1. / grid.getWidth() * canvas.getWidth(), 0,
			              i * 1. / grid.getWidth() * canvas.getWidth(), canvas.getWidth());
		}

		for (i = 0; i <= grid.getHeight(); i++) {
			gc.strokeLine(0, i * 1. / grid.getHeight() * canvas.getHeight(),
			              canvas.getWidth(), i * 1. / grid.getHeight() * canvas.getHeight());
		}

		for (i = 0; i < grid.getWidth(); i++) {
			for (j = 0; j < grid.getHeight(); j++) {
				if (grid.getElement(new GridPos(i, j)).getValue()) {
					gc.setFill(Color.color(grid.getElement(new GridPos(i, j)).getRgba()[0],
						grid.getElement(new GridPos(i, j)).getRgba()[1],
						grid.getElement(new GridPos(i, j)).getRgba()[2],
						grid.getElement(new GridPos(i, j)).getRgba()[3])
					);

					gc.fillRect(i * 1. / grid.getWidth() * canvas.getWidth(),
					            j * 1. / grid.getHeight() * canvas.getHeight(),
					            canvas.getWidth() * 1. / grid.getWidth(),
					            canvas.getHeight() * 1. / grid.getHeight());
				}
			}
		}
	}
}
