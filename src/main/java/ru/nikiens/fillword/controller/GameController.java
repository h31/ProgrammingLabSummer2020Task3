package ru.nikiens.fillword.controller;

import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import ru.nikiens.fillword.model.Game;

import java.net.URL;
import java.util.ResourceBundle;

public class GameController implements Initializable {

    @FXML
    private Label timer;

    @FXML
    private GridPane table;

    @FXML
    private Label category;

    @FXML
    private JFXListView<String> wordsList;


    public void initialize(URL location, ResourceBundle resources) {
        Game.getInstance().generateBoard();

        wordsList.setItems(FXCollections.observableArrayList(Game.getInstance().getWords()));
        wordsList.setMouseTransparent(true);
        wordsList.setFocusTraversable(false);

        category.setText(Game.getInstance().getCategory());

        for (int i = 0; i < Game.getInstance().getBoardSize().value(); i++) {
            for (int j = 0; j < Game.getInstance().getBoardSize().value(); j++) {
                table.add(generateLabel(i, j), i, j);
            }
        }
    }

    private Label generateLabel(int x, int y) {
        Label label = new Label(Game.getInstance().getBoard()[x][y].getLetter());
        label.prefHeightProperty().bind(table.heightProperty().divide(Game.getInstance().getBoardSize().value()));
        label.prefWidthProperty().bind(table.widthProperty().divide(Game.getInstance().getBoardSize().value()));
        label.setAlignment(Pos.CENTER);
        return label;
    }
}