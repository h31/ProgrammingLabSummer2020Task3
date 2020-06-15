package project.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import project.Main;
import project.models.Field;
import project.models.Tile;
import project.views.View;

import java.io.File;
import java.io.IOException;

public class Controller {
    private Field field;
    private View view;

    public Controller(Field field, View view) {
        this.field = field;
        this.view = view;
    }

    public Controller(View view) {
        this.view = view;
    }

    public void playNewGame() {
        if (!view.isDifficultySet())
            return;

        Main.primaryStage.setScene(new Scene(view.createContent()));
    }

    public void returnToMenu() throws IOException {
        FXMLLoader loader = new FXMLLoader(new File("src/main/resources/mainMenu.fxml").toURI().toURL());
        loader.setController(this);

        Parent root = loader.load();

        Main.primaryStage.setScene(new Scene(root));
    }

    public void reset() {
        Main.primaryStage.getScene().setRoot(view.createContent());
    }

    public void exit() {
        System.exit(0);
    }

    public void setEasy() {
        view.setDifficulty(8, 10, 10);
    }

    public void setNormal() {
        view.setDifficulty(16, 18, 56);
    }

    public void setHard() {
        view.setDifficulty(20, 24, 99);
    }

    public void mouseClickLeft(Tile tile) {
        field.openTile(tile);
        view.redraw();
    }

    public void mouseClickRight(Tile tile) {
        field.markTile(tile);
        view.redraw();
    }

    public void mouseClickMiddle(Tile tile) {
        field.openNearTiles(tile);
        view.redraw();
    }
}
