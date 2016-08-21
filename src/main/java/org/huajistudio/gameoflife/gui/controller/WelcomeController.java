package org.huajistudio.gameoflife.gui.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;

import java.util.Locale;
import java.io.IOException;

public class WelcomeController extends GridPane {
	@FXML public JFXButton newLifeButton;
	@FXML public JFXButton openLifeButton;
	@FXML public JFXComboBox<Locale> comboBox;

	public WelcomeController() {
		FXMLLoader loader = new FXMLLoader(WelcomeController.class.getResource("/fxml/Welcome.fxml"));
		loader.setRoot(this);
		loader.setController(this);

		try {
			loader.load();
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}
	}
}
