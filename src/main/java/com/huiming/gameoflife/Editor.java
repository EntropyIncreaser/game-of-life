package com.huiming.gameoflife;

import com.huiming.gameoflife.util.GridPos;
import com.jfoenix.controls.JFXButton;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.Objects;

public class Editor extends Application {
	private Grid worldGrid;

	public Grid getWorldGrid() {
		return worldGrid;
	}

	public void setWorldGrid(Grid worldGrid) {
		this.worldGrid = worldGrid;
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		GridPane pane = new GridPane();
		pane.setAlignment(Pos.CENTER);
		pane.setHgap(10);
		pane.setVgap(10);

		Canvas canvas = new Canvas(500, 500);
		drawGrid(canvas, worldGrid, Color.BLACK);
		pane.add(canvas, 0, 0, 1, 2);

		JFXButton playBtn = new JFXButton("Start");
		playBtn.setOnAction(event -> {
            drawGrid(canvas, worldGrid, Color.BLACK);
            if (Objects.equals(playBtn.getText(), "Start")) {
                playBtn.setText("Stop");
            } else {
                playBtn.setText("Start");
	            // TODO: make the world evolve
            }
        });
		pane.add(playBtn, 2, 0);

		primaryStage.setScene(new Scene(pane));
		primaryStage.setTitle("World Editor");
		primaryStage.show();
	}

	public Editor(Grid grid) {
		worldGrid = grid;
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
