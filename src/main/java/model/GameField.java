package model;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import view.Tetris;


/**
 * Класс "игровое поле"
 * Отвечает за заполнение клеток, отрисовку фигур, очистку заполненных клеток
 */
public class GameField extends Pane {
    private static int countScore;
    private final int cellSize = 25;//размер клетки

    public enum Elements {//элементы игрового поля
        FigureS, FigureJ, FigureI, FigureZ, FigureL, FigureO, FigureT, EmptyCell
    }

    private static final int columnSize = 24;//количество клеток по вертикали
    private static final int rowSize = 16;//количество клеток по горизонтали
    private final Tetris tetris = new Tetris();

    public static int getCountScore() {
        return countScore;
    }

    public static void setCountScore(int countScore) {
        GameField.countScore = countScore;
    }

    private static final Elements[][] gameField = new Elements[columnSize][rowSize];//игровое поле в виде двумерного массива

    public int getRowSize() {
        return rowSize;
    }

    public static Elements[][] getGameField() {
        return gameField;
    }

    /**
     * Перерисовка поля
     */
    public void repaintField() {
        for (int i = 0; i < columnSize; i++) {
            for (int j = 0; j < rowSize; j++) {
                getGameField()[i][j] = Elements.EmptyCell;
            }
        }
    }

    public GameField() {
        //отрисовка поля
        repaintField();

        //добавление сетки на поле
        for (int i = 0; i < gameField.length * cellSize; i += cellSize) {
            tetris.getCanvas().getGraphicsContext2D().strokeLine(i, 0, i, cellSize * gameField.length);
        }
        for (int j = 0; j < gameField.length * cellSize; j += cellSize) {
            tetris.getCanvas().getGraphicsContext2D().strokeLine(0, j, cellSize * gameField.length, j);
        }
        //добавление в граф сцены
        getChildren().add(tetris.getCanvas());
    }

    /**
     * Метод, отвечающий на окончание игры
     *
     * @return закончилась ли игра
     */
    public boolean endGame() {
        boolean ended = false;
        for (int i = 0; i < rowSize; i++) {
            if (getGameField()[1][i] != Elements.EmptyCell) {
                ended = true;
                break;
            }
        }
        return ended;
    }

    /**
     * Очищение заполненных строк
     */
    public void clearRow() {
        int count = 0;

        for (int i = 0; i < getGameField().length; i++) {//счетчик подряд идущих элементов
            for (int j = 0; j < getRowSize(); j++) {
                if (getGameField()[i][j] != Elements.EmptyCell) {
                    count++;
                } else {
                    count = 0;
                    break;
                }
            }
            //Очищение и перемещение фигур
            if (count == rowSize) {
                for (int k = i; k > 0; k--) {
                    for (int j = 0; j < getRowSize(); j++) {
                        getGameField()[k][j] = getGameField()[k - 1][j];
                        tetris.getCanvas().getGraphicsContext2D().clearRect(j * cellSize, k * cellSize, cellSize, cellSize);
                    }
                }
                countScore += 100;
            }
        }
    }

    /**
     * Очищение незаполненных клеток во время падения фигуры
     */
    public void clear() {
        for (int i = 0; i < columnSize; i++) {
            for (int j = 0; j < rowSize; j++) {
                if (getGameField()[i][j] == Elements.EmptyCell) {
                    tetris.getCanvas().getGraphicsContext2D().clearRect(j * cellSize, i * cellSize, cellSize, cellSize);
                }
            }
        }
    }

    /**
     * Отрисовка падающей фигуры
     */
    public void drawFigure() {
        clear();

        for (int i = 0; i < 4; i++) {
            if (Figure.elements == Elements.FigureI) {
                tetris.getCanvas().getGraphicsContext2D().setFill(Color.GREENYELLOW);
            } else if (Figure.elements == Elements.FigureO) {
                tetris.getCanvas().getGraphicsContext2D().setFill(Color.RED);
            } else if (Figure.elements == Elements.FigureL) {
                tetris.getCanvas().getGraphicsContext2D().setFill(Color.YELLOW);
            } else if (Figure.elements == Elements.FigureS) {
                tetris.getCanvas().getGraphicsContext2D().setFill(Color.ORANGE);
            } else if (Figure.elements == Elements.FigureT) {
                tetris.getCanvas().getGraphicsContext2D().setFill(Color.PURPLE);
            } else if (Figure.elements == Elements.FigureJ) {
                tetris.getCanvas().getGraphicsContext2D().setFill(Color.BLUE);
            } else if (Figure.elements == Elements.FigureZ) {
                tetris.getCanvas().getGraphicsContext2D().setFill(Color.SKYBLUE);
            }
            tetris.getCanvas().getGraphicsContext2D().fillRect(Figure.shape[0][i] * cellSize, Figure.shape[1][i] * cellSize, cellSize, cellSize);
        }
    }

    /**
     * Отрисовка упавшей фигуры
     */
    public void drawField() {
        for (int i = 0; i < getGameField().length; i++) {
            for (int j = 0; j < rowSize; j++) {
                if (getGameField()[i][j] == Elements.FigureI) {
                    tetris.getCanvas().getGraphicsContext2D().setFill(Color.GREENYELLOW);
                } else if (getGameField()[i][j] == Elements.FigureL) {
                    tetris.getCanvas().getGraphicsContext2D().setFill(Color.YELLOW);
                } else if (getGameField()[i][j] == Elements.FigureO) {
                    tetris.getCanvas().getGraphicsContext2D().setFill(Color.RED);
                } else if (getGameField()[i][j] == Elements.FigureS) {
                    tetris.getCanvas().getGraphicsContext2D().setFill(Color.ORANGE);
                } else if (getGameField()[i][j] == Elements.FigureT) {
                    tetris.getCanvas().getGraphicsContext2D().setFill(Color.PURPLE);
                } else if (getGameField()[i][j] == Elements.FigureJ) {
                    tetris.getCanvas().getGraphicsContext2D().setFill(Color.BLUE);
                } else if (getGameField()[i][j] == Elements.FigureZ) {
                    tetris.getCanvas().getGraphicsContext2D().setFill(Color.SKYBLUE);
                }
                tetris.getCanvas().getGraphicsContext2D().fillRect(j * cellSize, i * cellSize, cellSize, cellSize);
            }
        }
    }
}
