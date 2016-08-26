/*
 * A game inspired from Conway's Game Of Life.
 * Copyright (C) 2016 Huaji Studio.
 * <p>
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * <p>
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * <p>
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.huajistudio.gameoflife.gui;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.huajistudio.gameoflife.GameOfLife;
import org.huajistudio.gameoflife.api.GameOfLifeAPI;
import org.huajistudio.gameoflife.api.components.grid.Grid;
import org.huajistudio.gameoflife.file.GridReader;
import org.huajistudio.gameoflife.gui.controller.WelcomeController;
import org.reflections.Reflections;
import org.reflections.scanners.ResourcesScanner;

import java.io.File;
import java.util.Locale;
import java.util.Set;
import java.util.regex.Pattern;

import static org.huajistudio.gameoflife.GameOfLife.EDITOR;
import static org.huajistudio.gameoflife.GameOfLife.OPTIONS;
import static org.huajistudio.gameoflife.api.GameOfLifeAPI.LOGGER;

public class Welcome extends Application {
	public JFXButton newLifeButton, openLifeButton, optButton;
	public JFXComboBox<Locale> comboBox;
	public WelcomeController controller;

	@Override
	public void start(Stage primaryStage) throws Exception {
		controller = new WelcomeController();
		comboBox = controller.comboBox;
		newLifeButton = controller.newLifeButton;
		openLifeButton = controller.openLifeButton;
		optButton = controller.optButton;

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
			fileChooser.setTitle(GameOfLifeAPI.I18N.parse("window.jsonchooser.title"));
			fileChooser.getExtensionFilters().addAll(
				new FileChooser.ExtensionFilter(GameOfLifeAPI.I18N.parse("extension.all"), "*.*"),
				new FileChooser.ExtensionFilter(GameOfLifeAPI.I18N.parse("extension.json"), "*.json"),
				new FileChooser.ExtensionFilter(GameOfLifeAPI.I18N.parse("extension.gz"), "*.json.gz")
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

		optButton.setOnAction(event -> {
			try {
				OPTIONS.start(primaryStage);
			} catch (Exception e) {
				LOGGER.error(e);
			}
		});

		primaryStage.setScene(new Scene(controller));
		primaryStage.setTitle(GameOfLifeAPI.I18N.parse("window.welcome.title"));
		primaryStage.show();
	}
}
