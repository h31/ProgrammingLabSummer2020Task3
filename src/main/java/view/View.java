package view;

import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.io.InputStream;

public class View {

    public void createLose(Group group) {
        InputStream input = getClass().getResourceAsStream("/Lose.jpg");
        Image image = new Image(input);
        ImageView imageView = new ImageView(image);
        group.getChildren().add(imageView);
    }

    public void loseScene(MenuButton btn1, MenuButton btn2, int score, Group group) {
        VBox loseText = new VBox(10);
        VBox loseBtn = new VBox(10);
        Text gameOver = new Text("Game Over");
        Text finalScore = new Text("Your final score: " + score);
        gameOver.setFont(Font.font("Showcard Gothic", 70));
        finalScore.setFont(Font.font("Showcard Gothic", 30));
        gameOver.setFill(Color.YELLOW);
        finalScore.setFill(Color.YELLOW);
        loseText.setTranslateX(280);
        loseText.setTranslateY(310);
        loseBtn.setTranslateX(280);
        loseBtn.setTranslateY(450);
        loseText.getChildren().addAll(gameOver, finalScore);
        loseBtn.getChildren().addAll(btn1, btn2);
        group.getChildren().addAll(loseText, loseBtn);
    }

    public VBox vBoxMenu(MenuButton btn1, MenuButton btn2) {
        VBox menu = new VBox(30);
        menu.setTranslateX(350);
        menu.setTranslateY(400);
        menu.getChildren().addAll(btn1, btn2);
        return menu;
    }

    public void winScene(MenuButton btn1, MenuButton btn2, Group group) {
        VBox winText = new VBox(10);
        VBox winBtn = new VBox(10);
        Text congrats = new Text("Congratulations!");
        Text youWin = new Text("You win");
        congrats.setFont(Font.font("Showcard Gothic", 30));
        youWin.setFont(Font.font("Showcard Gothic", 30));
        congrats.setFill(Color.DARKBLUE);
        youWin.setFill(Color.DARKBLUE);
        winText.setTranslateX(320);
        winText.setTranslateY(310);
        winBtn.setTranslateX(320);
        winBtn.setTranslateY(400);
        winText.getChildren().addAll(congrats, youWin);
        winBtn.getChildren().addAll(btn1, btn2);
        group.getChildren().addAll(winText, winBtn);
    }

    public VBox vBoxGameMenu(MenuButton btn1, MenuButton btn2, MenuButton btn3) {
        VBox pause = new VBox(20);
        pause.setTranslateX(350);
        pause.setTranslateY(400);
        pause.getChildren().addAll(btn1, btn2, btn3);
        return pause;
    }

    public void createMenu(Group group) {
        InputStream input = getClass().getResourceAsStream("/MainMenu.png");
        Image image = new Image(input);
        ImageView imageView = new ImageView(image);
        group.getChildren().add(imageView);
    }

    public void createBoard(Group group) {
        InputStream input = getClass().getResourceAsStream("/backGround.jpg");
        Image image = new Image(input);
        ImageView imageView = new ImageView(image);
        group.getChildren().add(imageView);
    }

    public void createWin(Group group) {
        InputStream input = getClass().getResourceAsStream("/Win.png");
        Image image = new Image(input);
        ImageView imageView = new ImageView(image);
        StackPane stackPane = new StackPane(imageView);
        stackPane.setLayoutX(150);
        stackPane.setLayoutY(200);
        group.getChildren().add(stackPane);
    }

    public void drawBoard(Group group, int[][] board, int score) {
        group.getChildren().clear();
        Text scoreText = new Text(String.valueOf(score));
        scoreText.setFont(Font.font("Showcard Gothic", 60));
        scoreText.setFill(Color.YELLOW);
        scoreText.setTranslateX(150);
        scoreText.setTranslateY(70);
        createBoard(group);
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (board[i][j] != 0) {
                    InputStream block = getClass().getResourceAsStream("/Numbers/" + board[i][j] + ".png");
                    Image image = new Image(block);
                    ImageView imageView = new ImageView(image);
                    imageView.setLayoutX(j * 232 + 1);
                    imageView.setLayoutY(i * 232 + 90);
                    group.getChildren().add(imageView);
                }
            }
        }
        group.getChildren().add(scoreText);
    }

    public static class MenuButton extends StackPane {
        public MenuButton(String name) {
            Text text = new Text(name);
            text.setFont(Font.font("Arial", 35));
            Rectangle bg = new Rectangle(200, 30);
            bg.setOpacity(0.6);
            bg.setFill(Color.BLACK);
            setAlignment(Pos.CENTER_LEFT);
            setRotate(-0.5);
            getChildren().addAll(bg, text);
            setOnMouseEntered(event -> {
                bg.setTranslateX(10);
                text.setTranslateX(10);
                bg.setFill(Color.WHITE);
                text.setFill(Color.BLACK);
            });
            setOnMouseExited(event -> {
                bg.setTranslateX(0);
                text.setTranslateX(0);
                bg.setFill(Color.BLACK);
                text.setFill(Color.WHITE);
            });
            final DropShadow drop = new DropShadow(50, Color.AQUA);
            drop.setInput(new Glow());
            setOnMousePressed(event -> setEffect(drop));
            setOnMouseReleased(event -> setEffect(null));
        }
    }
}
