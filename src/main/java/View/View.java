package View;

import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import java.io.InputStream;

public class View {

    private Group group;

    public View(Group group) {
        this.group = group;
    }

    public void createMenu(Group group){
        InputStream input = getClass().getResourceAsStream("/MainMenu.png");
        Image image = new Image(input);
        ImageView imageView = new ImageView(image);
        group.getChildren().addAll(imageView);
    }

    public void createBoard(Group group) {
        InputStream input = getClass().getResourceAsStream("/backGround.jpg");
        Image image = new Image(input);
        ImageView imageView = new ImageView(image);
        group.getChildren().addAll(imageView);
    }

    public void createWin(Group group) {
        InputStream input = getClass().getResourceAsStream("/Test.jpg");
        Image image = new Image(input);
        ImageView imageView = new ImageView(image);
        StackPane stackPane = new StackPane(imageView);
        stackPane.setLayoutX(450);
        stackPane.setLayoutY(450);
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
                    imageView.setLayoutX(i*232+1);
                    imageView.setLayoutY(j*232+90);
                    group.getChildren().addAll(imageView);
                }
            }
        }
        group.getChildren().add(scoreText);
    }

    public static class MenuButton extends StackPane {
        public MenuButton(String name){
            Text text = new Text(name);
            text.setFont(Font.font("Arial", 35));
            Rectangle bg = new Rectangle(200, 30);
            bg.setOpacity(0.6);
            bg.setFill(Color.BLACK);
            setAlignment(Pos.CENTER_LEFT);
            setRotate(-0.5);
            getChildren().addAll(bg, text);
            setOnMouseEntered(event ->{
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
