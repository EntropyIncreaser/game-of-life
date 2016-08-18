package com.huiming.gameoflife;

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
	JFXButton newLifeBtn, openLifeBtn;
	JFXComboBox<Locale> comboBox;

	@Override
	public void start(Stage primaryStage) throws Exception {
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));

		Text title = new Text("Welcome to Game of Life");
		title.setFont(Font.font(25));
		grid.add(title, 0, 0);

		newLifeBtn  = new JFXButton("Create a New Space");
		newLifeBtn.getStyleClass().add("button-raised");
		openLifeBtn = new JFXButton("Open a Space");
		openLifeBtn.getStyleClass().add("button-raised");
		HBox HnlBtn = new HBox(newLifeBtn), HolBtn = new HBox(openLifeBtn);
		HnlBtn.setAlignment(Pos.CENTER);
		HolBtn.setAlignment(Pos.CENTER);
		grid.add(HnlBtn, 0, 1);
		grid.add(HolBtn, 0, 2);

		comboBox = new JFXComboBox<>();
		comboBox.getItems().addAll(
			new Locale("en", "US"),
			Locale.SIMPLIFIED_CHINESE,
			Locale.TRADITIONAL_CHINESE
		);

		comboBox.setPromptText("Choose a language...");
		comboBox.valueProperty().addListener((observable, oldValue, newValue) -> {
			GameOfLife.locale = comboBox.getValue() == null ? GameOfLife.locale : comboBox.getValue();
        });

		grid.add(comboBox, 0, 3);


		primaryStage.setScene(new Scene(grid));
		primaryStage.setTitle("Welcome");
		primaryStage.show();
	}
}
