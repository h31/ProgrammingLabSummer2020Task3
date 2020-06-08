package ru.nikiens.fillword.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ListCell;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import ru.nikiens.fillword.model.BoardSize;
import ru.nikiens.fillword.model.Game;
import ru.nikiens.fillword.model.util.SourceVerifier;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;

public class LevelsController implements Initializable {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private JFXComboBox<BoardSize> sizeChooser;

    public void initialize(URL location, ResourceBundle resources) {
        sizeChooser.setItems(FXCollections.observableArrayList(BoardSize.values()));
        sizeChooser.setCellFactory(sc -> createSizeCell());
        sizeChooser.setButtonCell(createSizeCell());

        sizeChooser.getSelectionModel().selectFirst();
        Game.getInstance().setBoardSize(sizeChooser.getSelectionModel().getSelectedItem());
    }

    private ListCell<BoardSize> createSizeCell() {
        return new ListCell<>() {
            protected void updateItem(BoardSize item, boolean empty) {
                super.updateItem(item, empty);

                if (item != null) {
                    String num = Integer.toString(item.value());
                    setText(num + "x" + num);
                } else {
                    setText(null);
                }
            }
        };
    }

    void switchToGame(Path source, AnchorPane ap) {
        try {
            new SourceVerifier(source).verify(Game.getInstance().getBoardSize());
            Game.getInstance().setSource(source);
            Game.getInstance().initializeCategory();

            Parent root = FXMLLoader.load(getClass().getResource("/fxml/game.fxml"));

            Stage stage = new Stage();
            stage.setMinWidth(800.0);
            stage.setMinHeight(600.0);
            stage.setTitle("Fillword");
            stage.setScene(new Scene(root));
            stage.show();

            ap.getScene().getWindow().hide();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);

            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText(e.getMessage());
            alert.show();
        }
    }

    void switchToGame(Path source) {
        switchToGame(source, anchorPane);
    }

    @FXML
    void onCustomGameButton() {
        FileChooser chooser = new FileChooser();
        Stage stage = (Stage) anchorPane.getScene().getWindow();

        chooser.setTitle("Choose file");
        switchToGame(chooser.showOpenDialog(stage).toPath());
    }

    @FXML
    void onFoodsGameButton() throws URISyntaxException {
        switchToGame(getGlossary().resolve("foods.txt"));
    }

    @FXML
    void onMusicGameButton() throws URISyntaxException {
        switchToGame(getGlossary().resolve("music.txt"));
    }

    @FXML
    void onSportsGameButton() throws URISyntaxException {
        switchToGame(getGlossary().resolve("sports.txt"));
    }

    @FXML
    void changeBorderSize() {
        Game.getInstance().setBoardSize(sizeChooser.getSelectionModel().getSelectedItem());
    }

    static Path getGlossary() throws URISyntaxException {
        return Paths.get(LevelsController.class.getResource("/glossary").toURI());
    }
}
