package project;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Minesweeper");
        primaryStage.setScene(Field.setField());
        primaryStage.show();
    }
    public static void main(String[] args) {
        Application.launch(args);
    }
}
