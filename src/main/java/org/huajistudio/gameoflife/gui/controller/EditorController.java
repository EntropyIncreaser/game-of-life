package org.huajistudio.gameoflife.gui.controller;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;
import javafx.scene.canvas.Canvas;

import java.io.IOException;

public class EditorController extends GridPane {
	@FXML public Canvas worldCanvas;
	@FXML public JFXButton playButton, saveButton;

	public EditorController() {
		FXMLLoader loader = new FXMLLoader(EditorController.class.getResource("/fxml/Editor.fxml"));
		loader.setRoot(this);
		loader.setController(this);

		try {
			loader.load();
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}
	}
}
