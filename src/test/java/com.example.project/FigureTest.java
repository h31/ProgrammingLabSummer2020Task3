package com.example.project;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.testng.Assert.assertTrue;

class FigureTest {
    private Integer[][] array = new Integer[10][10];
    private Canvas canvas = new Canvas(300, 300);
    private GraphicsContext gc = canvas.getGraphicsContext2D();
    private Figure figure = new Figure(90, 90);

    @Test
    void can() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                array[i][j] = 0;
            }
        }
        Figure.x[1] = Figure.x[0];
        Figure.y[1] = Figure.y[0] + 30;
        Figure.x[2] = Figure.x[1];
        Figure.y[2] = Figure.y[1] + 30;
        Figure.x[3] = Figure.x[2];
        Figure.y[3] = Figure.y[2] + 30;
        assertTrue(figure.can(array, 0, 1));
        assertTrue(figure.can(array, 1, 0));
        assertTrue(figure.can(array, -1, 0));
    }

    @Test
    void transfer() {
        Figure.x[1] = Figure.x[0];
        Figure.y[1] = Figure.y[0] + 30;
        Figure.x[2] = Figure.x[1];
        Figure.y[2] = Figure.y[1] + 30;
        Figure.x[3] = Figure.x[2];
        Figure.y[3] = Figure.y[2] + 30;

        figure.transfer(gc, 0, 1);
        assertEquals(210, Figure.y[3]);
        assertEquals(180, Figure.y[2]);

        figure.transfer(gc, 1, 0);
        assertEquals(120, Figure.x[3]);
        assertEquals(210, Figure.y[3]);

        figure.transfer(gc, -1, 0);
        assertEquals(90, Figure.x[3]);
        assertEquals(210, Figure.y[3]);
    }

    @Test
    void inArray() {
        Figure.x[1] = Figure.x[0];
        Figure.y[1] = Figure.y[0] + 30;
        Figure.x[2] = Figure.x[1];
        Figure.y[2] = Figure.y[1] + 30;
        Figure.x[3] = Figure.x[2];
        Figure.y[3] = Figure.y[2] + 30;

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                array[i][j] = 0;
            }
        }

        figure.inArray(array, 1);
        assertEquals(1, array[3][6]);
        assertEquals(1, array[3][5]);
        assertEquals(1, array[3][4]);
        assertEquals(1, array[3][3]);
        assertEquals(0, array[4][6]);
        assertEquals(0, array[3][2]);
    }

    @Test
    void rotate() {
        Figure.x[1] = Figure.x[0];
        Figure.y[1] = Figure.y[0] + 30;
        Figure.x[2] = Figure.x[1];
        Figure.y[2] = Figure.y[1] + 30;
        Figure.x[3] = Figure.x[2];
        Figure.y[3] = Figure.y[2] + 30;

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                array[i][j] = 0;
            }
        }
        figure.inArray(array, 1);
        figure.rotate(gc, array);
        assertEquals(120, Figure.y[0]);
        assertEquals(60, Figure.x[0]);
        assertEquals(120, Figure.y[3]);
        assertEquals(150, Figure.x[3]);
    }
   
}