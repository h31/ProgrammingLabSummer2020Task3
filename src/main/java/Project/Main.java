package Project;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    static Stage primaryStage;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Snake");
        primaryStage.setScene(Menu.playNewGame());
        (Main.primaryStage = primaryStage).show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
