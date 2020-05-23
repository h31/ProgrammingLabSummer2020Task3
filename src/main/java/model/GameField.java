package model;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import view.Tetris;

public class GameField extends Pane {
    private final int widthCell = 25;
    private final int heightCell = 25;

    public int getWidthCell() {
        return widthCell;
    }

    public int getHeightCell() {
        return heightCell;
    }

    private int delta = 25;

    public int getDelta() {
        return delta;
    }

    public void setDelta(int delta) {
        this.delta = delta;
    }

    public enum Elements {
        FigureI, FigureZ, FigureL, FigureO, FigureT, EmptyCell
    }

    private static final int columnSize = 20;
    private final static int rowSize = 16;

    public static int getRowSize() {
        return rowSize;
    }

    private static final Elements[][] gameField = new Elements[columnSize][rowSize];

    public Elements[][] getGameField() {
        return gameField;
    }

    private final Tetris tetris = new Tetris();

    private int countScore = 0;
    private final Label score = new Label();
    private final Label endGame = new Label("GAME OVER");

    /**
     * Перерисовка поля
     */
    public void repaintField() {
        for (int i = 0; i < columnSize; i++) {
            for (int j = 0; j < rowSize; j++) {
                gameField[i][j] = Elements.EmptyCell;
            }
        }
    }

    public GameField() {
        //отрисовка поля
        repaintField();

        //добавление сетки на поле
        for (int i = 0; i < gameField.length * widthCell; i += widthCell) {
            tetris.getCanvas().getGraphicsContext2D().strokeLine(i, 0, i, widthCell * gameField.length);
        }
        for (int j = 0; j < gameField.length * heightCell; j += heightCell) {
            tetris.getCanvas().getGraphicsContext2D().strokeLine(0, j, heightCell * gameField.length, j);
        }

        score.setTextFill(Color.GREEN);
        score.setFont(new Font(21));
        score.setLayoutX(500);
        score.setLayoutY(20);

        endGame.setStyle("-fx-background-color: #0a0a0a");
        endGame.setTextFill(Color.WHITE);
        endGame.setFont(new Font(35.3));
        endGame.setVisible(false);

        endGame.setLayoutX(128);
        endGame.setLayoutY(150);

        //добавление поля в граф сцены
        getChildren().addAll(tetris.getCanvas(), score, endGame);

    }

    /**
     * @return закончилась ли игра
     */
    public boolean endGame() {
        boolean ended = false;
        for (int i = 0; i < rowSize; i++) {
            if (gameField[1][i] != Elements.EmptyCell) {
                delta = 0;
                endGame.setVisible(true);
                ended = true;
            }
        }
        endGame.setVisible(ended);
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
            if (count == 16) {
                for (int k = i; k > 0; k--) {
                    for (int j = 0; j < getRowSize(); j++) {
                        getGameField()[k][j] = getGameField()[k - 1][j];
                        tetris.getCanvas().getGraphicsContext2D().clearRect(j * 25, k * 25, 25, 25);
                    }
                }
                countScore += 100;

                score.setText(String.valueOf(countScore));
            }
        }
    }

    /**
     * Отрисовка фигуры на поле
     */
    public void drawFigure() {
        for (int i = 0; i < getGameField().length; i++) {
            for (int j = 0; j < getRowSize(); j++) {
                if (getGameField()[i][j] == Elements.FigureI) {
                    tetris.getCanvas().getGraphicsContext2D().setFill(Color.GREENYELLOW);
                    tetris.getCanvas().getGraphicsContext2D().fillRect(j * 25, i * 25, 25, 25);
                } else if (getGameField()[i][j] == Elements.FigureL) {
                    tetris.getCanvas().getGraphicsContext2D().setFill(Color.YELLOW);
                    tetris.getCanvas().getGraphicsContext2D().fillRect(j * 25, i * 25, 25, 25);
                } else if (getGameField()[i][j] == Elements.FigureO) {
                    tetris.getCanvas().getGraphicsContext2D().setFill(Color.RED);
                    tetris.getCanvas().getGraphicsContext2D().fillRect(j * 25, i * 25, 25, 25);
                } else if (getGameField()[i][j] == Elements.FigureZ) {
                    tetris.getCanvas().getGraphicsContext2D().setFill(Color.ORANGE);
                    tetris.getCanvas().getGraphicsContext2D().fillRect(j * 25, i * 25, 25, 25);
                } else if (getGameField()[i][j] == Elements.FigureT) {
                    tetris.getCanvas().getGraphicsContext2D().setFill(Color.PURPLE);
                    tetris.getCanvas().getGraphicsContext2D().fillRect(j * 25, i * 25, 25, 25);
                } else {
                    tetris.getCanvas().getGraphicsContext2D().clearRect(j * 25, i * 25, 25, 25);
                }

            }
        }
    }

}
