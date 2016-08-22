package org.huajistudio.gameoflife.gui.controller;

import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import org.huajistudio.gameoflife.GameOfLife;
import org.huajistudio.gameoflife.util.I18n;

import java.io.IOException;
import java.util.ResourceBundle;

public class OptionsController extends BorderPane {
	@FXML public JFXTextField textFieldWidth;

	public OptionsController() {
		FXMLLoader loader = new FXMLLoader(WelcomeController.class.getResource("/fxml/Options.fxml"));
		loader.setResources(ResourceBundle.getBundle("language/language", GameOfLife.locale, new I18n.UTF8Control()));
		loader.setController(this);

		try {
			loader.load();
		} catch (IOException exception) {
			throw new RuntimeException(exception);
		}
	}
}
