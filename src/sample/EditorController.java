package sample;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;

import java.util.Objects;

public abstract class EditorController {
	@FXML public Canvas worldCanvas;
	@FXML public Button startButton;

	@FXML public void handlePlayButtonAction() {
		drawGrid();
		startButton.setText(Objects.equals(startButton.getText(), "start") ? "stop" : "start");
	}

	public abstract void drawGrid();
}
