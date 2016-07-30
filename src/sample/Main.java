package sample;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		Welcome wel = new Welcome();
		Editor editor = new Editor(20);
		wel.start(primaryStage);
		wel.newLifeBtn.setOnAction(event -> {
            primaryStage.hide();
            try {
                editor.start(primaryStage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
		wel.openLifeBtn.setOnAction(event -> {});
	}

	public static void main(String[] args) {
		launch(args);
	}
}