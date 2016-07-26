package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.Objects;

public class Editor extends Application {
	public Grid worldGrid;

	@Override
	public void start(Stage primaryStage) throws Exception {
		GridPane pane = new GridPane();
		pane.setAlignment(Pos.CENTER);
		pane.setHgap(10);
		pane.setVgap(10);

		Canvas canvas = new Canvas(500, 500);
		drawGrid(canvas, worldGrid, Color.BLACK);
		pane.add(canvas, 0, 0, 1, 2);

		Button playBtn = new Button("Start");
		playBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				drawGrid(canvas, worldGrid, Color.BLACK);
				if (Objects.equals(playBtn.getText(), "Start")) {
					playBtn.setText("Stop");
				} else {
					playBtn.setText("Start");
				}
			}
		});
		pane.add(playBtn, 2, 0);

		primaryStage.setScene(new Scene(pane));
		primaryStage.setTitle("World Editor");
		primaryStage.show();
	}

	public Editor(int width) {
		worldGrid = new Grid(width, width);
	}

	public void drawGrid(Canvas canvas, Grid grid, Color color) {
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
		gc.setFill(color);
		gc.setStroke(color);

		int i, j;

		for (i = 1; i < grid.width; i++) {
			gc.strokeLine(i * 1. / grid.width * canvas.getWidth(), 0,
			              i * 1. / grid.width * canvas.getWidth(), canvas.getWidth());
		}
		for (i = 1; i < grid.height; i++) {
			gc.strokeLine(0, i * 1. / grid.height * canvas.getHeight(),
			              canvas.getWidth(), i * 1. / grid.height * canvas.getHeight());
		}
		for (i = 0; i < grid.width; i++) {
			for (j = 0; j < grid.height; j++) {
				if (grid.grid[i][j]) {
					gc.fillRect(i * 1. / grid.width * canvas.getWidth(),
					            j * 1. / grid.height * canvas.getHeight(),
					            canvas.getWidth() * 1. / grid.width,
					            canvas.getHeight() * 1. / grid.height);
				}
			}
		}
	}
}
