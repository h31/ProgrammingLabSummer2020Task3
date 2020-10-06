package controller;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Group group = new Group();
        Scene scene = new Scene(group, 926, 1015);
        primaryStage.setScene(scene);
        primaryStage.setTitle("2048");
        primaryStage.show();
        new Controller(group, scene);
    }
}