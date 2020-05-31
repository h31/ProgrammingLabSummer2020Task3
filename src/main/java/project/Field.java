package project;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;


class Field {
    private final static double r = 20;
    private final static double n = r * Math.sqrt(0.75);
    private final static double tileHeight = 2 * r;
    private final static double tileWidth = 2 * n;

    private final static int windowHeight = 600;
    private final static int windowWidth = 800;

    private final static int xStartOffset = 40;
    private final static int yStartOffset = 40;

    private final static int rowNumber = (int) ((windowHeight - 2 * yStartOffset) / (3 * r / 2));
    private final static int tilesPerRow = (int) ((windowWidth - 2 * xStartOffset) / tileWidth);

    private static Tile[][] grid = new Tile[tilesPerRow][rowNumber];

    private static Scene scene;

    private static Parent createContent() {
        AnchorPane root = new AnchorPane();
        root.setPrefSize(windowWidth, windowHeight);

        for (int y = 0; y < rowNumber; y++) {
            for (int x = 0; x < tilesPerRow; x++) {
                Tile tile = new Tile(x, y, Math.random() < 0.2);

                grid[x][y] = tile;
                root.getChildren().add(tile);
            }
        }

        for (int y = 0; y < rowNumber; y++) {
            for (int x = 0; x < tilesPerRow; x++) {
                Tile tile = grid[x][y];

                if (!tile.hasBomb) {
                    long bombs = getNeighbours(tile)
                            .stream()
                            .filter(t -> t.hasBomb)
                            .count();
                    if (bombs > 0)
                        tile.bombInf.setText(String.valueOf(bombs));
                }
            }
        }

        return root;
    }

    private static List<Tile> getNeighbours(Tile tile) {
        List<Tile> neighbours = new ArrayList<>();

        int[] pointsOddRow = new int[]{
                -1, -1,
                -1, 0,
                -1, 1,
                0, -1,
                1, 0,
                0, 1
        };

        int[] pointsEvenRow = new int[]{
                0, -1,
                -1, 0,
                0, 1,
                1, -1,
                1, 0,
                1, 1
        };

        for (int i = 0; i < pointsOddRow.length; i++) {
            int x = tile.y % 2 == 0
                    ? tile.x + pointsOddRow[i]
                    : tile.x + pointsEvenRow[i];
            int y = tile.y % 2 == 0
                    ? tile.y + pointsOddRow[++i]
                    : tile.y + pointsEvenRow[++i];

            if (x >= 0 && x < tilesPerRow && y >= 0 && y < rowNumber) {
                neighbours.add(grid[x][y]);
            }
        }

        return neighbours;
    }

    static Scene setField() {
        scene = new Scene(createContent());

        return scene;
    }

    private static class Tile extends StackPane {
        private int x, y;
        private boolean hasBomb;
        private boolean isOpen = false;
        private boolean hasFlag = false;

        private Hexagon hex = new Hexagon(x, y);
        private Text bombInf = new Text();
        private Text flag = new Text();

        Tile(int x, int y, boolean hasBomb) {
            this.x = x;
            this.y = y;
            this.hasBomb = hasBomb;
            bombInf.setText(hasBomb ? "X" : "");
            bombInf.setVisible(false);

            flag.setText("V");
            flag.setFont(Font.font(18));
            flag.setFill(Color.WHITE);
            flag.setVisible(false);

            hex.setFill(Color.DARKBLUE);
            hex.setStrokeWidth(1);
            hex.setStroke(Color.LIGHTBLUE);

            getChildren().addAll(hex, flag, bombInf);

            setTranslateY(y * tileHeight * 0.75 + yStartOffset);
            setTranslateX(x * tileWidth + (y % 2) * n + xStartOffset);

            setOnMouseClicked(e -> {
                if (e.getButton() == MouseButton.SECONDARY) {
                    setFlag();
                } else {
                    open();
                }
            });
        }

        public void setFlag() {
            if (isOpen)
                return;

            if (!hasFlag) {
                flag.setVisible(true);
                hasFlag = true;
            } else {
                flag.setVisible(false);
                hasFlag = false;
            }
        }

        public void open() {
            if (isOpen || hasFlag)
                return;

            if (hasBomb) {
                scene.setRoot(createContent());
                return;
            }

            isOpen = true;
            bombInf.setVisible(true);
            hex.setFill(Color.WHITE);
            hex.setStroke(Color.BLACK);

            if (bombInf.getText().isEmpty()) {
                getNeighbours(this).forEach(Tile::open);
            }
        }
    }

    private static class Hexagon extends Polygon {
        Hexagon(double x, double y) {
            getPoints().addAll(
                    x, y + r,
                    x + n, y + r * 0.5,
                    x + n, y - r * 0.5,
                    x, y - r,
                    x - n, y - r * 0.5,
                    x - n, y + r * 0.5
            );
        }
    }
}
