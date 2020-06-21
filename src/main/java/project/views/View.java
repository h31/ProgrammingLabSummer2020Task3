package project.views;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;
import project.controllers.Controller;
import project.models.Field;
import project.models.Tile;

import java.io.IOException;

public class View {
    private Field field;
    private Controller controller;
    private AnchorPane root;
    private Label flagsLeft;
    private Timeline timeline;
    private StackPane face;
    private Group grid;

    private final double r = 18;
    private final double n = r * Math.sqrt(0.75);
    private final double tileHeight = 2 * r;
    private final double tileWidth = 2 * n;

    private final int xStartOffset = 40;
    private final int yStartOffset = 60;

    private boolean difficultySet;
    private boolean timeStarted;

    private int rows, tiles, bombs;


    public void setDifficulty(int rows, int tiles, int bombs) {
        this.rows = rows;
        this.tiles = tiles;
        this.bombs = bombs;

        difficultySet = true;
    }

    public boolean isDifficultySet() {
        return difficultySet;
    }

    public Parent createContent() {
        root = new AnchorPane();

        field = new Field(rows, tiles, bombs);
        controller = new Controller(field, this);

        int windowHeight = (int) (2 * yStartOffset + field.getRowNumber() / 2 * (tileHeight + r));
        int windowWidth = (int) (2.5 * xStartOffset + field.getTilesInRow() * tileWidth);
        root.setPrefSize(windowWidth, windowHeight);

        timeStarted = false;

        timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        final Integer[] time = {0};
        Label timer = new Label(Integer.toString(time[0]));
        timer.setFont(Font.font(24));
        AnchorPane.setTopAnchor(timer, 15d);
        AnchorPane.setRightAnchor(timer, 30d);

        timeline.getKeyFrames().add(
                new KeyFrame(Duration.seconds(1),
                        actionEvent -> {
                            time[0]++;
                            timer.setText(Integer.toString(time[0]));
                        })
        );

        root.getChildren().add(timer);

        face = new StackPane();
        Hexagon hex = new Hexagon(r, n);
        hex.setStroke(Color.BLACK);
        hex.setFill(Color.ANTIQUEWHITE);
        hex.setStrokeWidth(0.5);
        Text smile = new Text("UwU");
        smile.setFont(Font.font(14));
        face.getChildren().addAll(hex, smile);

        AnchorPane.setTopAnchor(face, 15d);
        face.setTranslateX(xStartOffset - n + tileWidth * field.getTilesInRow() / 2);
        root.getChildren().add(face);

        flagsLeft = new Label("Flags: " + field.getFlagsLeft());
        flagsLeft.setFont(Font.font(24));
        AnchorPane.setTopAnchor(flagsLeft, 15d);
        AnchorPane.setLeftAnchor(flagsLeft, 30d);
        root.getChildren().add(flagsLeft);

        Button exit = new Button("Exit");
        exit.setPrefSize(80, 20);
        AnchorPane.setBottomAnchor(exit, 15d);
        AnchorPane.setRightAnchor(exit, 15d);
        exit.setOnAction(actionEvent -> controller.exit());
        root.getChildren().add(exit);

        Button reset = new Button("Reset");
        reset.setPrefSize(80, 20);
        AnchorPane.setBottomAnchor(reset, 15d);
        reset.setTranslateX((double) (windowWidth / 2) - 40);
        reset.setOnAction(actionEvent -> controller.reset());
        root.getChildren().add(reset);

        Button returnToMenu = new Button("Main Menu");
        returnToMenu.setPrefSize(80, 20);
        AnchorPane.setBottomAnchor(returnToMenu, 15d);
        AnchorPane.setLeftAnchor(returnToMenu, 15d);
        returnToMenu.setOnAction(actionEvent -> {
            try {
                controller.returnToMenu();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        root.getChildren().add(returnToMenu);

        grid = new Group();
        for (Tile[] rows : field.getGrid()) {
            for (Tile tile : rows) {
                StackPane newTile = createClosedTile(tile);
                grid.getChildren().add(newTile);
            }
        }
        root.getChildren().add(grid);

        return root;
    }

    public void redraw() {
        root.getChildren().remove(flagsLeft);
        flagsLeft.setText("Flags: " + field.getFlagsLeft());
        root.getChildren().add(flagsLeft);

        if (field.isGameOver()) {
            root.getChildren().remove(face);
            face.getChildren().remove(1);
            Text smile = new Text(field.isLost()
                    ? "XwX"
                    : "UзU");
            smile.setFont(Font.font(14));

            face.getChildren().add(smile);
            root.getChildren().add(face);
        }

        if (!timeStarted) {
            timeline.playFromStart();
            timeStarted = true;
        }

        if (field.isGameOver())
            timeline.stop();

        root.getChildren().remove(grid);
        grid.getChildren().clear();
        for (Tile[] rows : field.getGrid()) {
            for (Tile tile : rows) {
                StackPane newTile = tile.isOpened()
                        ? createOpenedTile(tile)
                        : createClosedTile(tile);
                grid.getChildren().add(newTile);
            }
        }
        root.getChildren().add(grid);
    }

    public StackPane createClosedTile(Tile tile) {
        StackPane node = new StackPane();

        Hexagon cover = new Hexagon(r, n);
        cover.setFill(Color.DARKBLUE);
        cover.setStrokeWidth(1);
        cover.setStroke(Color.WHITE);

        Text flag = new Text("P");
        flag.setFill(field.isLost()
                ? tile.hasBomb()
                    ? Color.GREEN
                    : Color.RED
                : Color.WHITE);
        flag.setFont(Font.font(16));
        if (!tile.isMarked())
            flag.setVisible(false);

        node.getChildren().addAll(cover, flag);
        if (field.isLost() && tile.hasBomb() && !tile.isMarked()) {
            Text bomb = new Text("X");
            bomb.setFont(Font.font(16));
            bomb.setFill(Color.RED);

            node.getChildren().add(bomb);
        }

        node.setOnMouseClicked(event -> {
            switch (event.getButton()) {
                case PRIMARY:
                    controller.mouseClickLeft(tile);
                    break;
                case SECONDARY:
                    controller.mouseClickRight(tile);
                    break;
            }
        });

        node.setTranslateY(tile.getY() * tileHeight * 0.75 + yStartOffset);
        node.setTranslateX(tile.getX() * tileWidth + (tile.getY() % 2) * n + xStartOffset);

        return node;
    }

    public StackPane createOpenedTile(Tile tile) {
        StackPane node = new StackPane();

        Hexagon hex = new Hexagon(r, n);
        hex.setFill(Color.WHITE);
        hex.setStrokeWidth(0.4);
        hex.setStroke(Color.BLACK);

        Text bombs = new Text(tile.hasBomb()
                ? "X"
                : tile.getBombsAround() > 0
                    ? String.valueOf(tile.getBombsAround())
                    : "");
        bombs.setFont(Font.font(16));

        node.getChildren().addAll(hex, bombs);
        node.setTranslateY(tile.getY() * tileHeight * 0.75 + yStartOffset);
        node.setTranslateX(tile.getX() * tileWidth + (tile.getY() % 2) * n + xStartOffset);

        node.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.MIDDLE)
                controller.mouseClickMiddle(tile);
        });

        return node;
    }

    private static class Hexagon extends Polygon {
        Hexagon(double r, double n) {
            getPoints().addAll(
                    0.0, r,
                    n, r * 0.5,
                    n, -r * 0.5,
                    0.0, -r,
                    -n, -r * 0.5,
                    -n, r * 0.5
            );
        }
    }
}
