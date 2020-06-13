package project.views;

import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import project.controllers.Controller;
import project.models.Field;
import project.models.Tile;

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


    public Parent createContent() {

        root = new AnchorPane();
        int rowNum = 8;
        int tilesPerRow = 10;
        int bombs = 10;

        windowHeight = (int) (yStartOffset + rowNum * tileHeight);
        windowWidth = (int) (2.5 * xStartOffset + tilesPerRow * tileWidth);
        root.setPrefSize(windowWidth, windowHeight);

        field = new Field(rowNum, tilesPerRow, bombs);
        controller = new Controller(field, this);
        field.initialiseField();

        for (Tile[] rows : field.getGrid()) {
            for (Tile tile : rows) {
                StackPane newTile = createTile(tile);

                root.getChildren().add(newTile);
            }
        }

        return root;
    }

    public void redraw() {
        root.getChildren().clear();

        for (Tile[] rows : field.getGrid()) {
            for (Tile tile : rows) {
                StackPane newTile = createTile(tile);

                root.getChildren().add(newTile);
            }
        }
    }

    public StackPane createTile(Tile tile) {
        Hexagon hex = new Hexagon(r, n);
        hex.setFill(Color.WHITE);
        hex.setStrokeWidth(1);
        hex.setStroke(Color.BLACK);

        Text bombsAround = new Text(tile.hasBomb()
                ? "X"
                : tile.getBombsAround() > 0
                ? String.valueOf(tile.getBombsAround())
                : "");
        bombsAround.setFont(Font.font(16));

        Text bomb = new Text("X");
        bomb.setFill(Color.RED);
        bomb.setFont(Font.font(16));
        bomb.setVisible(false);

        Hexagon cover = new Hexagon(r, n);
        cover.setFill(Color.DARKBLUE);
        cover.setStrokeWidth(1);
        cover.setStroke(Color.WHITE);
        if (tile.isOpened())
            cover.setVisible(false);

        Text flag = new Text("P");
        flag.setFill(Color.WHITE);
        flag.setFont(Font.font(16));
        if (!tile.isMarked())
            flag.setVisible(false);

        if (field.isGameOver() && field.isLost()) {
            if (!tile.isMarked() && tile.hasBomb() && !tile.isOpened()) {
                bomb.setVisible(true);
            }
            if (tile.isMarked()) {
                if (tile.hasBomb())
                    flag.setFill(Color.GREEN);
                else
                    flag.setFill(Color.RED);
            }
        }

        tile.getChildren().addAll(hex, bombsAround, cover, flag, bomb);

        tile.setTranslateY(tile.getY() * tileHeight * 0.75 + yStartOffset);
        tile.setTranslateX(tile.getX() * tileWidth + (tile.getY() % 2) * n + xStartOffset);

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

        return tile;
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
