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

    Tetris tetris = new Tetris();

    int countScore = 0;
    Label score = new Label();

    public GameField() {
        //отрисовка поля
        for (int i = 0; i < columnSize; i++) {
            for (int j = 0; j < rowSize; j++) {
                gameField[i][j] = Elements.EmptyCell;
            }
        }

         for (int i = 0; i < gameField.length * widthCell; i += widthCell) {
           tetris.getCanvas().getGraphicsContext2D().strokeLine(i, 0, i, widthCell * gameField.length);
        }
         for (int j = 0; j < gameField.length * heightCell; j += heightCell) {
           tetris.getCanvas().getGraphicsContext2D().strokeLine(0, j, heightCell * gameField.length, j);
        }
        //  tetris.getCanvas().getGraphicsContext2D().setFill(Color.BLACK);



        score.setTextFill(Color.GREEN);
        score.setFont(new Font(21));
        score.setLayoutX(500);
        score.setLayoutY(20);


        //добавление поля в граф сцены
        getChildren().addAll(tetris.getCanvas(), score);


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
                }
            }
        }
    }

}
