package ru.nikiens.fillword.controller;

import com.jfoenix.controls.JFXButton;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.util.Duration;
import ru.nikiens.fillword.model.BoardSize;
import ru.nikiens.fillword.model.Game;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Random;
import java.util.stream.Stream;

public class MenuController {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private BorderPane parentContainer;

    @FXML
    private JFXButton levelsButton;

    @FXML
    void switchToLevels() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/levels.fxml"));
        Scene scene = levelsButton.getScene();

        root.translateYProperty().set(scene.getHeight());
        parentContainer.getChildren().add(root);

        Timeline timeline = new Timeline();
        KeyValue kv = new KeyValue(root.translateYProperty(), 0, Interpolator.EASE_IN);
        KeyFrame kf = new KeyFrame(Duration.millis(120), kv);

        timeline.getKeyFrames().add(kf);
        timeline.setOnFinished(e -> parentContainer.getChildren().remove(anchorPane));

        timeline.play();
    }

    @FXML
    void switchToRandomGame() throws IOException, URISyntaxException {
        Random random = new Random();
        LevelsController lc = new LevelsController();

        Path[] sources = Files.list(LevelsController.getGlossary()).toArray(Path[]::new);
        BoardSize[] sizes = Stream.of(BoardSize.values())
                .filter(it -> it != BoardSize.TESTING).toArray(BoardSize[]::new);

        Game.getInstance().setBoardSize(sizes[random.nextInt(sizes.length)]);
        lc.switchToGame(sources[random.nextInt(sources.length)], anchorPane);
    }
}