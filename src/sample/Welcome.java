package sample;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Welcome extends Application {
	Button newLifeBtn, openLifeBtn;

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

		newLifeBtn  = new Button("Create a New Space");
		openLifeBtn = new Button("Open a Space");
		HBox HnlBtn = new HBox(newLifeBtn), HolBtn = new HBox(openLifeBtn);
		HnlBtn.setAlignment(Pos.CENTER);
		HolBtn.setAlignment(Pos.CENTER);
		grid.add(HnlBtn, 0, 1);
		grid.add(HolBtn, 0, 2);

		primaryStage.setScene(new Scene(grid));
		primaryStage.setTitle("Welcome");
		primaryStage.show();
	}
}
