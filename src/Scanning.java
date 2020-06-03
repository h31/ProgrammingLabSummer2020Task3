import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

class Scanning {

    static Scanning play;
    static Pane zone;
    private static Polygon[][] square;
    private Field field;
    private boolean gameOver;
    private boolean win;

    private static ImagePattern num1 = new ImagePattern(new Image("Image/number_1.png"));
    private static ImagePattern num2 = new ImagePattern(new Image("Image/number_2.png"));
    private static ImagePattern num3 = new ImagePattern(new Image("Image/number_3.png"));
    private static ImagePattern num4 = new ImagePattern(new Image("Image/number_4.png"));
    private static ImagePattern num5 = new ImagePattern(new Image("Image/number_5.png"));
    private static ImagePattern num6 = new ImagePattern(new Image("Image/number_6.png"));

    Scanning() { field = new Field(); }

    private Field getField() { return field; }

    private boolean gameOver() { return gameOver; }

    private boolean win() { return win; }

    private void clickHexagon(int x, int y) {
        if (gameOver || win) return;
        if (!field.clickHexagon(x, y)) gameOver = true;
        if (field.getClosed() == 58) win = true;
        Scanning.update();
    }

    private void changeFlag(int x, int y) {
        field.changeFlag(x, y);
        Scanning.update();
    }

    static void init() {
        zone.getChildren().clear();
        square = new Polygon[40][10];
        for (int i = 0; i < 40; i++) {
            for (int j = 0; j < 10; j++) {
                final int column = j;
                final int row = i;
                int newJ;
                if (i % 2 != 0) { newJ = 37 + 45 * j;
                } else { newJ = 15 + 45 * j; }
                square[i][j] = draw(newJ, i);
                square[i][j].setStroke(Color.DARKVIOLET);
                square[i][j].setFill(Color.VIOLET);
                zone.getChildren().add(square[i][j]);
                square[i][j].setOnMouseClicked(event -> {
                    switch (event.getButton()) {
                        case PRIMARY:
                            play.clickHexagon(column, row);
                            break;
                        case SECONDARY:
                            play.changeFlag(column, row);
                            break;
                    }
                });
            }
        }
    }

    static void update() {
        for (int i = 0; i < 40; i++) {
            for (int j = 0; j < 10; j++) {
                Hexagon cell = play.getField().getHexagon()[i][j];
                if (cell.flag()) { square[i][j].setFill(Color.DARKVIOLET);
                } else { square[i][j].setFill(Color.VIOLET);
                }
                if (cell.getVisibility()) { square[i][j].setFill(Color.LIGHTGRAY);
                    if (cell.getMine()) { square[i][j].setFill(Color.DEEPPINK);
                    } else if (cell.getMinesCount() != 0) {
                        switch (cell.getMinesCount()) {
                            case 1:
                                square[i][j].setFill(num1);
                                break;
                            case 2:
                                square[i][j].setFill(num2);
                                break;
                            case 3:
                                square[i][j].setFill(num3);
                                break;
                            case 4:
                                square[i][j].setFill(num4);
                                break;
                            case 5:
                                square[i][j].setFill(num5);
                                break;
                            case 6:
                                square[i][j].setFill(num6);
                                break;
                        }
                    }
                }
            }
        }
        if (play.gameOver()) {
            Polygon rest = color();
            zone.getChildren().add(rest);
            Text text = new Text(zone.getWidth() / 2 - 130, zone.getHeight() / 2, "Game over(((");
            text.setFont(new Font(45));
            text.setTextAlignment(TextAlignment.LEFT);
            zone.getChildren().add(text);
        }
        if (play.win()) {
            color();
            Text txt = new Text(zone.getWidth() / 2 - 70, zone.getHeight() / 2, "Winning!");
            txt.setFont(new Font(45));
            txt.setTextAlignment(TextAlignment.LEFT);
            zone.getChildren().add(txt);
        }
    }
    private static Polygon color() {
        Polygon poly = new Polygon(
                90,165,
                365,165,
                365,300,
                90,300);
        poly.setFill(Color.YELLOW);
        return poly;
    }
    private static Polygon draw(int x, int y) {
        int r = 15;
        int a = r / 2;
        double b = r * 0.80;
        y *= 12;
        y += 12;
        Polygon polygon = new Polygon(
                x - r, y,
                x - a, y - b,
                x + a, y - b,
                x + r, y,
                x + a, y + b,
                x - a, y + b);
        return polygon;
    }
}