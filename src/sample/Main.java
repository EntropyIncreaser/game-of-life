package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage;

public class Main extends Application {
	Grid grid = new Grid(10, 10);

	@Override
	public void start(Stage primaryStage) throws Exception {
		Welcome wel = new Welcome();
		Editor editor = new Editor(20);
		wel.start(primaryStage);
		wel.newLifeBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				primaryStage.hide();
				try {
					editor.start(primaryStage);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static void main(String[] args) {
		launch(args);
	}
}
