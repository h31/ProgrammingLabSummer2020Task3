package com.example.project;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class TetrisController {
    TetrisView view = new TetrisView();
    TetrisModel model = new TetrisModel();
    public int iArray = view.iArray;
    public int jArray = view.jArray;
    public int[] x = new int[]{0, 0, 0, 0};
    public int[] y = new int[]{0, 0, 0, 0};
    public int[] lines = {0};
    public int[] score = {0};

    public int[] filled = view.filled();

    public Color[][] array = view.array();

    public int xCurrentFigure = iArray / 2 * 30;

    public int xNextFigure = view.width + 90;

    public Color color(int random) {
        Color color;
        if (random == 1) color = Color.BLUEVIOLET;
        else if (random == 2) color = Color.INDIGO;
        else if (random == 3) color = Color.DARKVIOLET;
        else if (random == 4) color = Color.MEDIUMPURPLE;
        else if (random == 5) color = Color.DARKMAGENTA;
        else if (random == 6) color = Color.MEDIUMORCHID;
        else if (random == 7) color = Color.PURPLE;
        else color = Color.WHITE;
        return color;
    }

    public boolean can(int side, int down) {
        return model.can(array, side, down, x, y);
    }

    public void draw(GraphicsContext gc) {
        gc.fillRect(x[0], y[0], 30, 30);
        gc.fillRect(x[1], y[1], 30, 30);
        gc.fillRect(x[2], y[2], 30, 30);
        gc.fillRect(x[3], y[3], 30, 30);
    }

    public void clear(GraphicsContext gc) {
        gc.clearRect(x[0], y[0], 30, 30);
        gc.clearRect(x[1], y[1], 30, 30);
        gc.clearRect(x[2], y[2], 30, 30);
        gc.clearRect(x[3], y[3], 30, 30);
    }

    public void createFigure(GraphicsContext gc, int xFigure, int yFigure, int random) {
        model.startIndex(xFigure, yFigure, x, y, random);
        this.draw(gc);
    }

    public void transfer(GraphicsContext gc, int side, int down) {
        this.clear(gc);
        model.transfer(side, down, x, y);
        this.draw(gc);
    }

    public void inArray(int random) {
        model.inArray(array, color(random), filled, x, y);
    }

    public void rotate(GraphicsContext gc) {
        int[] rotateXY = model.rotate(x, y);
        if (model.canRotate(array, rotateXY)) {
            this.clear(gc);
            x[0] = rotateXY[0];
            x[1] = rotateXY[1];
            x[2] = rotateXY[2];
            x[3] = rotateXY[3];
            y[0] = rotateXY[4];
            y[1] = rotateXY[5];
            y[2] = rotateXY[6];
            y[3] = rotateXY[7];
            this.draw(gc);
        }
    }

    public void deleteLines(GraphicsContext gc) {
        for (int jj = 0; jj < jArray; jj++) {
            if (filled[jj] == iArray) {
                lines[0] += 1;
                for (int j = jj; j > 0; j--) {
                    for (int i = iArray - 1; i >= 0; i--) {
                        gc.clearRect(i * 30, j * 30, 30, 30);
                        Color higherColor = array[i][j - 1];
                        if (higherColor != null) {
                            gc.setFill(higherColor);
                            gc.fillRect((i) * 30, (j) * 30, 30, 30);
                        }
                        array[i][j] = higherColor;
                    }
                    filled[j] = filled[j - 1];
                }
            }
        }
        if (lines[0] == 1) score[0] += 100;
        if (lines[0] == 2) score[0] += 300;
        if (lines[0] == 3) score[0] += 700;
        if (lines[0] == 4) score[0] += 1500;
        lines[0] = 0;
        gc.setFill(Color.LAVENDER);
        gc.fillRect(view.width + 75, 300, 100, 30);
        gc.setFill(Color.BLUEVIOLET);
        gc.fillText(String.valueOf(score[0]), view.width + 75, 330);
    }
}
