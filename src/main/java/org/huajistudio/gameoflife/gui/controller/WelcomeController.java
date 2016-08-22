package org.huajistudio.gameoflife.gui.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;
import org.huajistudio.gameoflife.GameOfLife;
import org.huajistudio.gameoflife.util.I18n;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

public class WelcomeController extends GridPane {
	@FXML public JFXButton newLifeButton;
	@FXML public JFXButton openLifeButton;
	@FXML public JFXComboBox<Locale> comboBox;

	public WelcomeController() {
		FXMLLoader loader = new FXMLLoader(WelcomeController.class.getResource("/fxml/Welcome.fxml"));
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
