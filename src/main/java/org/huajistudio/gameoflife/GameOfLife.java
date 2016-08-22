package org.huajistudio.gameoflife;

import javafx.application.Application;
import javafx.stage.Stage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.huajistudio.gameoflife.event.EventManager;
import org.huajistudio.gameoflife.gui.Editor;
import org.huajistudio.gameoflife.gui.Welcome;

import java.util.Locale;

public class GameOfLife extends Application {
	public static final Logger LOGGER = LogManager.getLogger("GameOfLife");
	public static Locale locale = Locale.getDefault();
	public static final EventManager EVENT_MANAGER = new EventManager();

	public static final Welcome WELCOME = new Welcome();
	public static final Editor EDITOR = new Editor(10, 10);

	public static void main(String[] args) {
		// Register default listeners
		EVENT_MANAGER.registerListenerInPackage("org.huajistudio.gameoflife.event.listener");
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		WELCOME.start(primaryStage);
	}
}
