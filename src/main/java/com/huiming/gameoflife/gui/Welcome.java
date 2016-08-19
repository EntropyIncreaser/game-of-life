package com.huiming.gameoflife.gui;

import com.huiming.gameoflife.GameOfLife;
import com.huiming.gameoflife.gui.controller.WelcomeController;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Locale;

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

		comboBox.getItems().addAll(
			new Locale("en", "US"),
			Locale.SIMPLIFIED_CHINESE,
			Locale.TRADITIONAL_CHINESE
		);
		comboBox.setPromptText("Choose a language...");
		comboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
			GameOfLife.locale = comboBox.getValue() == null ? GameOfLife.locale : comboBox.getValue();
		});

		primaryStage.setScene(new Scene(controller));
		primaryStage.setTitle("Welcome");
		primaryStage.show();
	}
}
