package com.huiming.gameoflife.gui;

import com.huiming.gameoflife.file.GridReader;
import com.huiming.gameoflife.util.I18n;
import com.huiming.gameoflife.util.grid.Grid;
import com.huiming.gameoflife.util.grid.GridPos;
import com.huiming.gameoflife.gui.controller.EditorController;
import com.jfoenix.controls.JFXButton;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.Objects;

import static com.huiming.gameoflife.GameOfLife.LOGGER;

public class Editor extends Application {
	private Grid worldGrid;

	public EditorController controller;
	public Canvas worldCanvas;
	public JFXButton playButton, saveButton;

	public Grid getWorldGrid() {
		return worldGrid;
	}

	public void setWorldGrid(Grid worldGrid) {
		this.worldGrid = worldGrid;
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		controller = new EditorController();
		worldCanvas = controller.worldCanvas;
		playButton = controller.playButton;
		saveButton = controller.saveButton;

		playButton.setOnAction(event -> {
			drawGrid(canvas, worldGrid, Color.BLACK);
			if (Objects.equals(playButton.getText(), "Start")) {
				playButton.setText("Stop");
			} else {
				playButton.setText("Start");
				// TODO: make the world evolve
			}
		});

		saveButton.setOnAction(event -> {

		JFXButton saveBtn = new JFXButton("Save");
		saveBtn.setOnAction(event -> {
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle(I18n.parse("window.jsonsaver.title"));
			fileChooser.getExtensionFilters().addAll(
				new FileChooser.ExtensionFilter(I18n.parse("extension.json"), "*.json")
			);
			fileChooser.setInitialFileName("grid.json");
			File file = fileChooser.showSaveDialog(primaryStage);
			try {
				GridReader.saveGrid(file, worldGrid);
			} catch (Exception e) {
				LOGGER.error(e);
			}
		});

		primaryStage.setScene(new Scene(controller));
		primaryStage.setTitle("World Editor");
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
					gc.fillRect(i * 1. / grid.getWidth() * canvas.getWidth(),
					            j * 1. / grid.getHeight() * canvas.getHeight(),
					            canvas.getWidth() * 1. / grid.getWidth(),
					            canvas.getHeight() * 1. / grid.getHeight());
				}
			}
		}
	}
}
