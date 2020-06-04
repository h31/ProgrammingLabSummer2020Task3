package com.example.project;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Figure {
    static int[] x;
    static int[] y;


    public Figure(int[] x, int[] y) {
        Figure.x = x;
        Figure.y = y;
    }

    public void for1() {
        Figure.x = new int[]{150, 150, 150, 150};
        Figure.y = new int[]{0, 30, 60, 90};
    }

    public void for2() {
        Figure.x = new int[]{390, 390, 390, 390};
        Figure.y = new int[]{60, 90, 120, 150};
    }

    public void startIndex(GraphicsContext gc, int random) {
        if (random == 1) {
            gc.setFill(Color.BLUEVIOLET);
        }
        if (random == 2) {
            gc.setFill(Color.INDIGO);
            x[3] += 30;
            y[3] -= 30;
        }
        if (random == 3) {
            gc.setFill(Color.DARKVIOLET);
            x[3] -= 30;
            y[3] -= 30;
        }
        if (random == 4) {
            gc.setFill(Color.MEDIUMPURPLE);
            x[2] += 30;
            y[2] -= 60;
            x[3] += 30;
            y[3] -= 60;
        }
        if (random == 5) {
            gc.setFill(Color.DARKMAGENTA);
            x[2] += 30;
            y[2] -= 60;
            x[3] -= 30;
            y[3] -= 90;
        }
        if (random == 6) {
            gc.setFill(Color.MEDIUMORCHID);
            x[2] += 30;
            y[2] -= 60;
            x[3] -= 30;
            y[3] -= 60;
        }
        if (random == 7) {
            gc.setFill(Color.PURPLE);
            x[2] += 30;
            y[2] -= 30;
            x[3] -= 30;
            y[3] -= 90;
        }

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

    public boolean canDown(Integer[][] array) {
        if (array[x[0] / 30 + 1][y[0] / 30 + 1] < 0) return false;
        if (array[x[1] / 30 + 1][y[1] / 30 + 1] < 0) return false;
        if (array[x[2] / 30 + 1][y[2] / 30 + 1] < 0) return false;
        return array[x[3] / 30 + 1][y[3] / 30 + 1] >= 0;
    }

    public boolean canLeft(Integer[][] array) {
        if (array[x[0] / 30][y[0] / 30] < 0) return false;
        if (array[x[1] / 30][y[1] / 30] < 0) return false;
        if (array[x[2] / 30][y[2] / 30] < 0) return false;
        return array[x[3] / 30][y[3] / 30] >= 0;
    }

    public boolean canRight(Integer[][] array) {
        if (array[x[0] / 30 + 2][y[0] / 30] < 0) return false;
        if (array[x[1] / 30 + 2][y[1] / 30] < 0) return false;
        if (array[x[2] / 30 + 2][y[2] / 30] < 0) return false;
        return array[x[3] / 30 + 2][y[3] / 30] >= 0;
    }

    public void inArray(Integer[][] array, int random) {
        array[x[0] / 30 + 1][y[0] / 30] = -random;
        array[x[1] / 30 + 1][y[1] / 30] = -random;
        array[x[2] / 30 + 1][y[2] / 30] = -random;
        array[x[3] / 30 + 1][y[3] / 30] = -random;
        array[12][y[0] / 30] += 1;
        array[12][y[1] / 30] += 1;
        array[12][y[2] / 30] += 1;
        array[12][y[3] / 30] += 1;
    }
}
