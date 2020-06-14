package project.views;

import javafx.scene.Node;
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
import project.controllers.Controller;
import project.models.Field;
import project.models.Tile;

import java.io.IOException;

public class View {
    private Field field;
    private Controller controller;
    private AnchorPane root;

    private final double r = 20;
    private final double n = r * Math.sqrt(0.75);
    private final double tileHeight = 2 * r;
    private final double tileWidth = 2 * n;
    private int windowHeight;
    private int windowWidth;

    private final int xStartOffset = 40;
    private final int yStartOffset = 60;

    private boolean difficultySet = false;

    private int rows, tiles, bombs;


    public void setDifficulty(int rows, int tiles, int bombs) {
        this.rows = rows;
        this.tiles = tiles;
        this.bombs = bombs;

        difficultySet = true;
    }

    public Parent createContent() {
        root = new AnchorPane();

        field = new Field(rows, tiles, bombs);
        controller = new Controller(field, this);

        Button exit = new Button("Exit");
        exit.setPrefSize(80, 20);
        AnchorPane.setBottomAnchor(exit, 15d);
        AnchorPane.setRightAnchor(exit, 15d);
        exit.setOnMouseClicked(event -> controller.exit());
        root.getChildren().add(exit);

        Button reset = new Button("Reset");
        reset.setPrefSize(80, 20);
        AnchorPane.setBottomAnchor(reset, 15d);
        AnchorPane.setLeftAnchor(reset, 15d);
        reset.setOnMouseClicked(event -> controller.reset());
        root.getChildren().add(reset);

        Button returnToMenu = new Button("Main Menu");
        returnToMenu.setPrefSize(80, 20);
        AnchorPane.setTopAnchor(returnToMenu, 15d);
        AnchorPane.setLeftAnchor(returnToMenu, 15d);
        returnToMenu.setOnMouseClicked(event -> {
            try {
                controller.returnToMenu();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        root.getChildren().add(returnToMenu);

        windowHeight = (int) (2 * yStartOffset + field.getRowNumber() / 2 * (tileHeight + r));
        windowWidth = (int) (2.5 * xStartOffset + field.getTilesInRow() * tileWidth);
        root.setPrefSize(windowWidth, windowHeight);

        for (Tile[] rows : field.getGrid()) {
            for (Tile tile : rows) {
                StackPane newTile = createClosedTile(tile);
                root.getChildren().add(newTile);
            }
        }

        return root;
    }

    public void redraw() {
        for (Tile[] rows : field.getGrid()) {
            for (Tile tile : rows) {
                root.getChildren().remove(tile);

                StackPane newTile = tile.isOpened()
                        ? createOpenedTile(tile)
                        : createClosedTile(tile);
                root.getChildren().add(newTile);
            }
        }
    }

    public StackPane createClosedTile(Tile tile) {
        tile.getChildren().clear();

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

        tile.getChildren().addAll(cover, flag);
        if (field.isLost() && tile.hasBomb() && !tile.isMarked()) {
            Text bomb = new Text("X");
            bomb.setFont(Font.font(16));
            bomb.setFill(Color.RED);

            tile.getChildren().add(bomb);
        }

        tile.setOnMouseClicked(event -> {
            switch (event.getButton()) {
                case PRIMARY:
                    controller.mouseClickLeft(tile);
                    break;
                case SECONDARY:
                    controller.mouseClickRight(tile);
                    break;
            }
        });

        tile.setTranslateY(tile.getY() * tileHeight * 0.75 + yStartOffset);
        tile.setTranslateX(tile.getX() * tileWidth + (tile.getY() % 2) * n + xStartOffset);

        return tile;
    }

    public StackPane createOpenedTile(Tile tile) {
        tile.getChildren().clear();

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

        tile.getChildren().addAll(hex, bombs);
        tile.setTranslateY(tile.getY() * tileHeight * 0.75 + yStartOffset);
        tile.setTranslateX(tile.getX() * tileWidth + (tile.getY() % 2) * n + xStartOffset);

        tile.setOnMouseClicked(event -> {
            if (event.getButton() == MouseButton.MIDDLE)
                controller.mouseClickMiddle(tile);
        });

        return tile;
    }

    public boolean isDifficultySet() {
        return difficultySet;
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
