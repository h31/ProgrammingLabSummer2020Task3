package Project;

import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;

public class Menu {

    public static Scene playNewGame() {
        BorderPane menu = new BorderPane();
        Label topLabel = new Label("          Welcome to Snake!\nTutorial:\nUse W, A, S, D to move\n" +
                "Press ESC to pause and unpause\nPress Enter to restart!");
        topLabel.setFont(Font.font(20));
        Button startClassicGame = new Button();
        startClassicGame.setTranslateX(12);
        startClassicGame.setAlignment(Pos.TOP_CENTER);
        startClassicGame.setText("Start classic game");
        startClassicGame.setOnAction(event -> {
            Controller a = new Controller();
            Model.alternativeGame = false;
            a.startNewGame();
        });
        Button startAlternativeGame = new Button();

        startAlternativeGame.setTranslateY(30);
        startAlternativeGame.setText("Start alternative game");
        startAlternativeGame.setOnAction(event -> {
            Controller a = new Controller();
            Model.alternativeGame = true;
            a.startNewGame();
        });
        Group buttons = new Group();
        buttons.getChildren().addAll(startClassicGame, startAlternativeGame);
        menu.setMinSize(300, 300);
        menu.setMaxSize(300, 300);
        menu.setTop(topLabel);
        menu.setCenter(buttons);
        return new Scene(menu);
    }
}
