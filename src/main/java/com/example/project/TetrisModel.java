package com.example.project;

import javafx.scene.paint.Color;

public class TetrisModel {

    public void startIndex(int xFigure, int yFigure, int[] x, int[] y, int random) {
        if (random == 1) {
            x[0] = xFigure;
            y[0] = yFigure;
            x[1] = x[0];
            y[1] = y[0] + 30;
            x[2] = x[1];
            y[2] = y[1] + 30;
            x[3] = x[2];
            y[3] = y[2] + 30;

        }
        if (random == 2) {
            x[0] = xFigure;
            y[0] = yFigure;
            x[1] = x[0];
            y[1] = y[0] + 30;
            x[2] = x[1];
            y[2] = y[1] + 30;
            x[3] = x[2] + 30;
            y[3] = y[2];
        }
        if (random == 3) {
            x[0] = xFigure;
            y[0] = yFigure;
            x[1] = x[0];
            y[1] = y[0] + 30;
            x[2] = x[1];
            y[2] = y[1] + 30;
            x[3] = x[2] - 30;
            y[3] = y[2];
        }
        if (random == 4) {
            x[0] = xFigure;
            y[0] = yFigure;
            x[1] = x[0];
            y[1] = y[0] + 30;
            x[2] = x[1] + 30;
            y[2] = y[1] - 30;
            x[3] = x[2];
            y[3] = y[2] + 30;
        }
        if (random == 5) {
            x[0] = xFigure;
            y[0] = yFigure;
            x[1] = x[0];
            y[1] = y[0] + 30;
            x[2] = x[1] - 30;
            y[2] = y[1] - 30;
            x[3] = x[2] + 60;
            y[3] = y[2];
        }
        if (random == 6) {
            x[0] = xFigure;
            y[0] = yFigure;
            x[1] = x[0];
            y[1] = y[0] + 30;
            x[2] = x[1] + 30;
            y[2] = y[1];
            x[3] = x[2] - 60;
            y[3] = y[2] - 30;
        }
        if (random == 7) {
            x[0] = xFigure;
            y[0] = yFigure;
            x[1] = x[0];
            y[1] = y[0] + 30;
            x[2] = x[1] - 30;
            y[2] = y[1];
            x[3] = x[2] + 60;
            y[3] = y[2] - 30;
        }
    }

    public boolean can(Color[][] array, int side, int down, int[] x, int[] y) {
        for (int i = 0; i < 4; i++) {
            if (x[i] / 30 + side >= array.length || x[i] / 30 + side < 0 ||
                    y[i] / 30 + down < 0 || y[i] / 30 + down >= array[i].length) return false;
            else if (array[x[i] / 30 + side][y[i] / 30 + down] != null) return false;
        }
        return true;
    }

    public void transfer(int side, int down, int[] x, int[] y) {
        for (int i = 0; i < 4; i++) {
            x[i] += side * 30;
            y[i] += down * 30;
        }
    }

    public int[] rotate(int[] x, int[] y) {
        int[] rotateXY = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
        rotateXY[0] = x[1] + y[0] - y[1];
        rotateXY[1] = x[1];
        rotateXY[2] = x[1] + y[2] - y[1];
        rotateXY[3] = x[1] + y[3] - y[1];
        rotateXY[4] = y[1] - x[0] + x[1];
        rotateXY[5] = y[1];
        rotateXY[6] = y[1] - x[2] + x[1];
        rotateXY[7] = y[1] - x[3] + x[1];
        return rotateXY;
    }

    public boolean canRotate(Color[][] array, int[] rotateXY) {
        for (int i = 0; i < 4; i++) {
            if (rotateXY[i] < 0 || rotateXY[i + 4] < 0) return false;
            if (rotateXY[i] / 30 >= array.length || rotateXY[i + 4] / 30 >= array[i].length) return false;
            if (array[rotateXY[i] / 30][rotateXY[i + 4] / 30] != null) return false;
        }
        return true;
    }

    public void inArray(Color[][] array, Color color, int[] filled, int[] x, int[] y) {
        for (int i = 0; i < 4; i++) {
            array[x[i] / 30][y[i] / 30] = color;
            filled[y[i] / 30] += 1;
        }
    }
}
