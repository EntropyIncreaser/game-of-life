package org.huajistudio.gameoflife.event;

import javafx.stage.Stage;

public class JFXWindowEvent implements IEvent {
	private Stage stage;

	public JFXWindowEvent(Stage stage) {
		this.stage = stage;
	}

	public Stage getPane() {
		return stage;
	}

	public JFXWindowEvent setPane(Stage stage) {
		this.stage = stage;
		return this;
	}

	/**
	 * Performed when the window is opened.
	 */
	public static class OpenEvent extends JFXWindowEvent {
		public OpenEvent(Stage stage) {
			super(stage);
		}
	}

	/**
	 * Performed when the window is closed.
	 */
	public static class CloseEvent extends JFXWindowEvent {
		public CloseEvent(Stage stage) {
			super(stage);
		}
	}
}
