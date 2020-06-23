package com.example.project;

import javafx.scene.paint.Color;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TetrisModelTest {
    TetrisModel model = new TetrisModel();
    TetrisController controller = new TetrisController();
    TetrisView view = new TetrisView();
    @Test
    void can() {
        assertTrue(model.can(controller.array, 1, 0, new int[]{0, 0, 0, 0}, new int[]{480, 510, 540, 570}));
        assertFalse(model.can(controller.array, 0, 1, new int[]{0, 0, 0, 0}, new int[]{480, 510, 540, 570}));
        assertTrue(model.can(controller.array, -1, 0, new int[]{30, 30, 30, 30}, new int[]{480, 510, 540, 570}));
    }

    @Test
    void transfer() {
        int[] x = new int[]{0, 0, 0, 0};
        int[] y = new int[]{450, 480, 510, 540};
        model.transfer(1, 0, x, y);
        assertEquals(30, x[0]);
        model.transfer(0, 1, x, y);
        assertEquals(540, y[2]);
    }

    @Test
    void rotate() {
        int[] rotateXY = model.rotate(new int[]{90, 90, 90, 90}, new int[]{480, 510, 540, 570});
        assertEquals(60, rotateXY[0]);
        assertEquals(120, rotateXY[2]);
        assertEquals(510, rotateXY[6]);

    }

    @Test
    void canRotate() {
        assertTrue(model.canRotate(controller.array, new int[]{60, 90, 120, 150, 510, 510, 510, 510}));
        assertTrue(model.canRotate(controller.array, new int[]{0, 0, 0, 0, 480, 510, 540, 570}));
        assertFalse(model.canRotate(controller.array, new int[]{0, 0, 0, 0, 540, 570, 600, 630}));
    }

    @Test
    void inArray() {
        model.inArray(controller.array, Color.BLUEVIOLET, controller.filled, new int[]{0, 0, 0, 0}, new int[]{480, 510, 540, 570});
        assertEquals(Color.BLUEVIOLET, controller.array[0][16]);
        assertEquals(1, controller.filled[16]);
        model.inArray(controller.array, Color.BLUEVIOLET, controller.filled, new int[]{30, 60, 90, 120}, new int[]{570, 570, 570, 570});
        model.inArray(controller.array, Color.BLUEVIOLET, controller.filled, new int[]{150, 180, 210, 240}, new int[]{570, 570, 570, 570});
        model.inArray(controller.array, Color.BLUEVIOLET, controller.filled, new int[]{270, 270, 270, 270}, new int[]{480, 510, 540, 570});
        assertEquals(10, controller.filled[19]);
    }
}