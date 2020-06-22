package com.example.project;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Figure {
    static int[] x;
    static int[] y;


    public Figure(int x, int y) {
        Figure.x = new int[]{x, 0, 0, 0};
        Figure.y = new int[]{y, 0, 0, 0};
    }

    public void startIndex(GraphicsContext gc, int random) {
        if (random == 1) {
            gc.setFill(Color.BLUEVIOLET);
            x[1] = x[0];
            y[1] = y[0] + 30;
            x[2] = x[1];
            y[2] = y[1] + 30;
            x[3] = x[2];
            y[3] = y[2] + 30;

        }
        if (random == 2) {
            gc.setFill(Color.INDIGO);
            x[1] = x[0];
            y[1] = y[0] + 30;
            x[2] = x[1];
            y[2] = y[1] + 30;
            x[3] = x[2] + 30;
            y[3] = y[2];
        }
        if (random == 3) {
            gc.setFill(Color.DARKVIOLET);
            x[1] = x[0];
            y[1] = y[0] + 30;
            x[2] = x[1];
            y[2] = y[1] + 30;
            x[3] = x[2] - 30;
            y[3] = y[2];
        }
        if (random == 4) {
            gc.setFill(Color.MEDIUMPURPLE);
            x[1] = x[0];
            y[1] = y[0] + 30;
            x[2] = x[1] + 30;
            y[2] = y[1] - 30;
            x[3] = x[2];
            y[3] = y[2] + 30;
        }
        if (random == 5) {
            gc.setFill(Color.DARKMAGENTA);
            x[1] = x[0];
            y[1] = y[0] + 30;
            x[2] = x[1] - 30;
            y[2] = y[1] - 30;
            x[3] = x[2] + 60;
            y[3] = y[2];
        }
        if (random == 6) {
            gc.setFill(Color.MEDIUMORCHID);
            x[1] = x[0];
            y[1] = y[0] + 30;
            x[2] = x[1] + 30;
            y[2] = y[1];
            x[3] = x[2] - 60;
            y[3] = y[2] - 30;
        }
        if (random == 7) {
            gc.setFill(Color.PURPLE);
            x[1] = x[0];
            y[1] = y[0] + 30;
            x[2] = x[1] - 30;
            y[2] = y[1];
            x[3] = x[2] + 60;
            y[3] = y[2] - 30;
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

    public boolean can(Integer[][] array, int side, int down) {
        try {
            if (array[x[0] / 30 + side][y[0] / 30 + down] != 0) return false;
            if (array[x[1] / 30 + side][y[1] / 30 + down] != 0) return false;
            if (array[x[2] / 30 + side][y[2] / 30 + down] != 0) return false;
            return array[x[3] / 30 + side][y[3] / 30 + down] == 0;
        } catch (Exception e) {
            return false;
        }
    }

    public void transfer(GraphicsContext gc, int side, int down) {
        this.clear(gc);
        x[0] += side * 30;
        x[1] += side * 30;
        x[2] += side * 30;
        x[3] += side * 30;

        y[0] += down * 30;
        y[1] += down * 30;
        y[2] += down * 30;
        y[3] += down * 30;
        this.draw(gc);
    }

    public void inArray(Integer[][] array, int random) {
        array[x[0] / 30][y[0] / 30] = random;
        array[x[1] / 30][y[1] / 30] = random;
        array[x[2] / 30][y[2] / 30] = random;
        array[x[3] / 30][y[3] / 30] = random;
        int last = array.length - 1;
        array[last][y[0] / 30] -= 10;
        array[last][y[1] / 30] -= 10;
        array[last][y[2] / 30] -= 10;
        array[last][y[3] / 30] -= 10;
    }

    public void rotate(GraphicsContext gc, Integer[][] array) {
        int x0 = x[1] + y[0] - y[1];
        int y0 = y[1] - x[0] + x[1];
        int x2 = x[1] + y[2] - y[1];
        int y2 = y[1] - x[2] + x[1];
        int x3 = x[1] + y[3] - y[1];
        int y3 = y[1] - x[3] + x[1];
        try {
            if (array[x0 / 30][y0 / 30] == 0 && array[x2 / 30][y2 / 30] == 0 && array[x3 / 30][y3 / 30] == 0) {
                this.clear(gc);
                x[0] = x0;
                y[0] = y0;
                x[2] = x2;
                y[2] = y2;
                x[3] = x3;
                y[3] = y3;
                this.draw(gc);
            }
        } catch (Exception ignored) {
        }
    }


}
