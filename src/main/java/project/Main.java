package project;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import project.views.View;

public class Main extends Application {
    View view = new View();

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setResizable(false);
        primaryStage.setTitle("Hexsweeper");
        primaryStage.setScene(new Scene(view.createContent()));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
