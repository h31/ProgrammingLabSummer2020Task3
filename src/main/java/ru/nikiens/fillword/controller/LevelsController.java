package ru.nikiens.fillword.controller;

import com.jfoenix.controls.JFXButton;
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

import java.net.URI;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;

public class LevelsController implements Initializable {
    static final Path WORDS_DIR = Paths.get(URI.create(LevelsController.class.getResource("/words").toString()));

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private JFXButton levelButtonSport;

    @FXML
    private JFXButton levelButtonFoods;

    @FXML
    private JFXButton levelButtonMusic;

    @FXML
    private JFXButton levelButtonCustom;

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
            Game.getInstance().initCategory();

            Parent root = FXMLLoader.load(getClass().getResource("/fxml/game.fxml"));

            Stage stage = new Stage();
            stage.setTitle("Fillword");
            stage.setScene(new Scene(root));
            stage.show();

            ap.getScene().getWindow().hide();
        } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);

            alert.setTitle("Ошибка");
            alert.setHeaderText(null);
            alert.setContentText(e.getMessage());
            e.printStackTrace();
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

        chooser.setTitle("Выберите файл");
        switchToGame(chooser.showOpenDialog(stage).toPath());
    }

    @FXML
    void onFoodsGameButton() {
        switchToGame(WORDS_DIR.resolve("foods.txt"));
    }

    @FXML
    void onMusicGameButton() {
        switchToGame(WORDS_DIR.resolve("music.txt"));
    }

    @FXML
    void onSportsGameButton() {
        switchToGame(WORDS_DIR.resolve("sports.txt"));
    }

    @FXML
    void changeBorderSize() {
        Game.getInstance().setBoardSize(sizeChooser.getSelectionModel().getSelectedItem());
    }
}
