package org.huajistudio.gameoflife.gui.controller;

import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;
import javafx.scene.canvas.Canvas;
import org.huajistudio.gameoflife.GameOfLife;
import org.huajistudio.gameoflife.util.I18n;

import java.io.IOException;
import java.util.ResourceBundle;

public class EditorController extends GridPane {
	@FXML public Canvas worldCanvas;
	@FXML public JFXButton playButton, saveButton;

	public EditorController() {
		FXMLLoader loader = new FXMLLoader(EditorController.class.getResource("/fxml/Editor.fxml"));
		loader.setResources(ResourceBundle.getBundle("language/language", GameOfLife.locale, new I18n.UTF8Control()));
		loader.setRoot(this);
		loader.setController(this);

		try {
			loader.load();
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}
	}
}
