package project.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import project.Main;
import project.models.Field;
import project.models.Tile;
import project.views.View;

import java.io.File;
import java.io.IOException;

public class Controller {
    private Field field;
    private View view;
//    int xSize = 1;
//    int ySize = 1;
//    int bombsNum = 1;
    @FXML
    TextField x;
    @FXML
    TextField y;
    @FXML
    TextField bombs;
    @FXML
    Label wrong;
    @FXML
    RadioButton easy, normal, hard, custom;

    public Controller(Field field, View view) {
        this.field = field;
        this.view = view;
    }

    public Controller(View view) {
        this.view = view;
    }

    public void setCustom() {
        x.setDisable(false);
        y.setDisable(false);
        bombs.setDisable(false);

        custom.selectedProperty().addListener((observableValue, aBoolean, t1) -> {
            if (t1) {
                x.setDisable(false);
                y.setDisable(false);
                bombs.setDisable(false);
            } else {
                x.setDisable(true);
                y.setDisable(true);
                bombs.setDisable(true);
                wrong.setVisible(false);
            }
        });
    }

    public void playNewGame() {
        if (easy.isSelected())
            view.setDifficulty(8, 10, 10);

        if (normal.isSelected())
            view.setDifficulty(16, 18, 56);

        if (hard.isSelected())
            view.setDifficulty(20, 24, 99);

        if (custom.isSelected()) {
            try {
                int xSize = Integer.parseInt(x.getText());
                int ySize = Integer.parseInt(y.getText());
                int bombsNum = Integer.parseInt(bombs.getText());
                if (xSize <= 0
                        || ySize <= 0
                        || bombsNum <= 0
                        || xSize * ySize <= bombsNum
                        || xSize > 30
                        || ySize > 30
                ) {
                    wrong.setVisible(true);
                    return;
                }
                view.setDifficulty(ySize, xSize, bombsNum);
            } catch (Exception i) {
                wrong.setVisible(true);
                return;
            }
        }

//        view.setDifficulty(ySize, xSize, bombsNum);
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
