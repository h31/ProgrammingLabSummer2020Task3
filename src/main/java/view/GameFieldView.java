package view;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.layout.Pane;

public class GameFieldView extends Pane {
   private static final Label score = new Label();
   private static final Label endGame = new Label("GAME OVER");

    public static void setScore(String s) {
        score.setText(s);
    }

    public static Label getEndGame() {
        return endGame;
    }

    Label scoreLabel = new Label("Score");

    public GameFieldView() {
        //настройка виджета отвечающего за счет
        score.setTextFill(Color.RED);
        score.setFont(new Font(21.5));
        score.setLayoutX(510);
        score.setLayoutY(21.5);

        //настройка виджета отвечающего за окончание игры
        endGame.setStyle("-fx-background-color: #0a0a0a");
        endGame.setTextFill(Color.WHITE);
        endGame.setFont(new Font(35.3));
        endGame.setVisible(false);
        endGame.setLayoutX(128);
        endGame.setLayoutY(150);

        //эффекты для надписи
        scoreLabel.setText("Score");
        scoreLabel.setTextFill(Color.YELLOW);
        scoreLabel.setFont(new Font(23));
        scoreLabel.setLayoutX(420);
        scoreLabel.setLayoutY(20);

        //добавление в граф сцены
        getChildren().addAll(score, endGame, scoreLabel);
    }
}
