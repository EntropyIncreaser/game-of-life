package org.huajistudio.gameoflife.gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.huajistudio.gameoflife.GameOfLife;
import org.huajistudio.gameoflife.components.grid.Grid;
import org.huajistudio.gameoflife.file.GridReader;
import org.huajistudio.gameoflife.gui.controller.WelcomeController;
import org.huajistudio.gameoflife.util.I18n;
import org.reflections.Reflections;
import org.reflections.scanners.ResourcesScanner;

import java.io.File;
import java.util.Locale;
import java.util.Set;
import java.util.regex.Pattern;

import static org.huajistudio.gameoflife.GameOfLife.EDITOR;
import static org.huajistudio.gameoflife.GameOfLife.LOGGER;

public class Welcome extends Application {
	public JFXButton newLifeButton, openLifeButton;
	public JFXComboBox<Locale> comboBox;
	public WelcomeController controller;

	@Override
	public void start(Stage primaryStage) throws Exception {
		controller = new WelcomeController();
		comboBox = controller.comboBox;
		newLifeButton = controller.newLifeButton;
		openLifeButton = controller.openLifeButton;

		Reflections reflections = new Reflections("language", new ResourcesScanner());
		Set<String> properties =
			reflections.getResources(Pattern.compile(".*\\.properties"));
		properties.forEach(s -> {
			if (s.startsWith("language/language_"))
				comboBox.getItems().add(new Locale(s.substring(s.indexOf("language/language_") + 18, s.lastIndexOf(".properties"))));
        });

		comboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
			GameOfLife.locale = comboBox.getValue() == null ? GameOfLife.locale : comboBox.getValue();
		});

		newLifeButton.setOnAction(event -> {
			// TODO: select width
			primaryStage.hide();
			try {
				EDITOR.start(primaryStage);
			} catch (Exception e) {
				LOGGER.error(e);
			}
		});

		openLifeButton.setOnAction(event -> {
			FileChooser fileChooser = new FileChooser();
			fileChooser.setTitle(I18n.parse("window.jsonchooser.title"));
			fileChooser.getExtensionFilters().addAll(
				new FileChooser.ExtensionFilter(I18n.parse("extension.all"), "*.*"),
				new FileChooser.ExtensionFilter(I18n.parse("extension.json"), "*.json"),
				new FileChooser.ExtensionFilter(I18n.parse("extension.gz"), "*.json.gz")
			);
			fileChooser.setInitialFileName("grid.json");
			File file = fileChooser.showOpenDialog(primaryStage);
			primaryStage.hide();
			Grid grid;
			try {
				grid = GridReader.readGrid(file);
				EDITOR.setWorldGrid(grid);
				EDITOR.start(primaryStage);
			} catch (Exception e) {
				LOGGER.error(e);
			}
		});

		primaryStage.setScene(new Scene(controller));
		primaryStage.setTitle(I18n.parse("window.welcome.title"));
		primaryStage.show();
	}
}
