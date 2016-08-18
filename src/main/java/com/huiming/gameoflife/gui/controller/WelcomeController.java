package com.huiming.gameoflife.gui.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import javafx.fxml.FXML;

import java.util.Locale;

public class WelcomeController {
	@FXML public JFXButton newLifeButton;
	@FXML public JFXButton openLifeButton;
	@FXML public JFXComboBox<Locale> comboBox;
}
