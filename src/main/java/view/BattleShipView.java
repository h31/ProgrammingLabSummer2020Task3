package view;

import controller.BattleShipApp;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.scene.control.Alert;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class BattleShipView extends Application {
    public static Stage primaryStage;
    Properties properties = new Properties();
    public File file = new File("C:\\Users\\Admin\\ProgrammingLabSummer2020Task3\\src\\main\\resources\\message.properties");

    @Override
    public void start(Stage primaryStage) throws IOException {
        properties.load(new FileReader(file));
        this.primaryStage = primaryStage;
        primaryStage.setTitle(properties.getProperty("name"));
        primaryStage.getIcons().add(new Image("ship.jpg"));
        Parent root = FXMLLoader.load(getClass().getResource("/BattleShipApp.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}