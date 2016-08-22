package org.huajistudio.gameoflife.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.huajistudio.gameoflife.gui.controller.OptionsController;
import org.huajistudio.gameoflife.util.I18n;

public class Options extends Application {
	public OptionsController controller;

	@Override
	public void start(Stage primaryStage) throws Exception {
		controller = new OptionsController();
		primaryStage.setScene(new Scene(controller));
		primaryStage.setTitle(I18n.parse("window.welcome.button.opt.label"));
		primaryStage.show();
	}

}
