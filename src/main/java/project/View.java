package project;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.IOException;

public class View extends Application {
    public static void main(String[] args) throws IOException {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) {
        final Controller controller = new Controller();

        stage.setHeight(550);
        stage.setWidth(1000);
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.setTitle("Балда");
    }
}
