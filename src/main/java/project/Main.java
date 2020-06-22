package project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import project.controllers.Controller;
import project.views.View;

import java.io.File;
import java.io.IOException;

public class Main extends Application {
    View view = new View();
    public static Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(new File("src/main/resources/mainMenu.fxml").toURI().toURL());
        loader.setController(new Controller(view));

        Parent root = loader.load();

        primaryStage.setResizable(false);
        primaryStage.setTitle("Hexsweeper");
        primaryStage.setScene(new Scene(root));
        (Main.primaryStage = primaryStage).show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
