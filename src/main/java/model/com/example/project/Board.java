package model.com.example.project;

import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.awt.*;
import java.util.*;
import java.util.List;

import static java.lang.Math.abs;

public class Board {
    private final int width;
    private final int height;
    //public static final int INVALID = -1;

    private final Map<Cell, Chip> chips = new HashMap<>();
    public Board(int width, int height) {
        this.width = width;
        this.height = height;
        for (int i = 0; i < height; i++) { //заполнение поля шашками
            for (int j = 0; j < width; j++) {
                if (i < 3)  {
                    if (((i % 2 == 0) && (j % 2 != 0)) || ((i % 2 != 0) && (j % 2 == 0))) {
                        chips.put(new Cell(i, j), Chip.BLACK);
                    //} else if ((i % 2 != 0) && (j % 2 != 0)) {
                        //chips.put(new Cell(i, j), Chip.BLACK);
                    } else {
                        chips.put(new Cell(i, j), Chip.BLANK);
                    }
                }
                if ((i > 2) && (i < height - 3)) {
                    chips.put(new Cell(i, j), Chip.BLANK);
                }
                if (i > height - 4) {
                    if (((i % 2 == 0) && (j % 2 != 0)) || ((i % 2 != 0) && (j % 2 == 0))) {
                        chips.put(new Cell(i, j), Chip.WHITE);
                    //} else if ((i % 2 != 0) && (j % 2 != 0)) {
                        //chips.put(new Cell(i, j), Chip.WHITE);
                    } else {
                        chips.put(new Cell(i, j), Chip.BLANK);
                    }
                }
            }
        }
    }

    public Board() {
        this(8, 8);
    }

    public Board copy() {
        Board copy = new Board();
        copy.chips.putAll(chips);
        return copy;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
    public Chip getCell(int x, int y) { // получение шашки конкретного цвета по клетке
        return chips.get(new Cell(x, y));
    }
    public void selectCellGraphics(int rowIndex, int colIndex, GridPane gridPane) {
        //Node source = e.getPickResult().getIntersectedNode();
        //Integer colIndex = gridPane.getColumnIndex(source);
        //Integer rowIndex = gridPane.getRowIndex(source);
        Rectangle rectangle = new Rectangle(48, 48);
        rectangle.setStroke(Color.YELLOW);
        rectangle.setFill(Color.TRANSPARENT);
        rectangle.setStrokeWidth(2.0);
        gridPane.add(rectangle, colIndex, rowIndex);

    }
    public void cleanCell(int rowIndex, int colIndex) {
        chips.put(new Cell(rowIndex, colIndex), Chip.BLANK);
    }

    public static boolean isValidIndex(int testIndex) {
        return testIndex >= 0 && testIndex < 32;
    }

    // Получаем идентификатор, соотвествующий указанной точке на доске
    public Chip get(int x, int y) {
        return get(toIndex(x, y));
    }

    /*устанавливает идентификатор в черную клетку по координатам. Если координаты не соответствует черной клетке, то
    ничего не происходит. Если идентификатор меньше нуля, то значит эту клетку стоит установить как пустую.
     */
    public void set (int x, int y, Chip id) {
        set(toIndex(x, y), id);
    }


    // Тоже самое, что и выше. Индекс - число для черных клеток от 0 до 31 включительно
    public void set(int index, Chip id) {

        if (!isValidIndex(index)) {
            return;
        }

        // как писалось ранее ставим пустой
        if (id == Chip.INVALID) {
            id = Chip.BLANK;
        }
        chips.put(new Cell(toPoint(index)), id);
    }

    // конвертирует черную клетку из точки с координатами в индекс
    public static int toIndex(int x, int y) {
        // некорректные координаты либо это белая клетка
        if (!isValidPoint(new Point(x, y))) {
            return -1;
        }

        return x * 8 / 2 + y / 2; //8 * y + x;
    }

    // тоже самое, что выше
    public static int toIndex(Point p) {
        return (p == null) ? -1 : toIndex(p.x , p.y);
    }

    // получаем среднюю точку между двумя другими
    public static Point middle(Point p1, Point p2) {

        if ((p1 == null) || (p2 == null)) {
            return new Point(-1, -1);
        }

        return middle(p1.x, p1.y, p2.x, p2.y);
    }


    // тоже самое, что выше
    public static Point middle(int index1, int index2) {
        return middle(toPoint(index1), toPoint(index2));
    }

    // тоже самое, что выше
    public static Point middle(int x1, int y1, int x2, int y2) {
        int dx = x2 - x1;
        int dy = y2 - y1;
        if ( x1 < 0 || y1 < 0 || x2 < 0 || y2 < 0 || x1 > 7 || y1 > 7 || x2 > 7 || y2 > 7) {
            return new Point(-1, -1);
        }
        else if (x1 % 2 == y1 % 2 || x2 % 2 == y2 % 2) {
            return new Point(-1, -1);
        }
        else if (abs(dx) != abs(dy) || abs(dx) != 2) {
            return new Point(-1, -1);
        }

        return new Point((x1 + x2) / 2, (y1 + y2) / 2);
    }

    public static boolean isValidPoint(Point testPoint) {

        if (testPoint == null) {
            return false;
        }

        int x = testPoint.x;
        int y = testPoint.y;
        if (x < 0 || x > 7 || y < 0 || y > 7) {
            return false;
        }

        // на белых клетках
        if (x % 2 == y % 2) {
            return false;
        }

        return true;

    }

    // тоже самое, что и выше. Индекс по прежнему число для черных клеток от 0 до 31 включительно
    public Chip get(int index) {
        if (!isValidIndex(index)) {
            return Chip.INVALID;
        }
        //int y = index / 8;
        int x = index / 4;
        int y = (x % 2 == 0) ? (index % 4) * 2 + 1 : (index % 4) * 2;
        return chips.get(new Cell(x, y));
    }

    // конвертирует индекс черной клетки в точку с координатами(индекс 0 - точка(1, 0))
    public static Point toPoint(int index) {
        //int y = index / 8;
        //int x = index % 8;
        int x = index / 4;
        int y = (x % 2 == 0) ? (index % 4) * 2 + 1 : (index % 4) * 2;
        return !isValidIndex(index) ? new Point(-1, -1) : new Point(x, y);
    }

    // Ищет на доске черные клетки, которые совпадают с определенным идентификатором. Возращает список таких клеток
    // Если таких клеток нет, то возвращается пустой список
    public java.util.List<Point> find(Chip id) {

        List<Point> points = new ArrayList<>();
        for (int i = 0; i < 32; i++) {
            if (get(i) == id) {
                points.add(toPoint(i));
            }
        }
        return points;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Board board = (Board) o;
        return width == board.width &&
                height == board.height &&
                Objects.equals(chips, board.chips);
    }

    @Override
    public int hashCode() {
        return Objects.hash(width, height, chips);
    }

    @Override
    public String toString() {
        return "Board{" +
                "width=" + width +
                ", height=" + height +
                ", chips=" + chips +
                '}';
    }
}
