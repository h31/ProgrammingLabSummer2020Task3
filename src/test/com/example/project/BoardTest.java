package com.example.project;

import model.com.example.project.Board;
import model.com.example.project.Chip;
import org.junit.jupiter.api.Test;

import java.awt.Point;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {
    @Test
    void getWidth() {
        Board board = new Board();
        int width = board.getWidth();
        assertEquals(8, width);
    }
    @Test
    void getHeight() {
        Board board = new Board();
        int height = board.getHeight();
        assertEquals(8, height);
    }
    @Test
    void copy() {
        Board board = new Board();
        Board board2 = board.copy();
        assertEquals(board, board2);
    }
    @Test
    void getCell() {
        Board board = new Board();
        Chip chip = board.getCell(0,0);
        assertEquals(Chip.BLANK, chip);
    }
    @Test
    void isValidIndex() {
        Board board = new Board();
        assertTrue(board.isValidIndex(5));
        assertFalse(board.isValidIndex(-2));
    }
    @Test
    void get() {
        Board board = new Board();
        assertEquals(Chip.INVALID, board.get(board.toIndex(0,0)));
        assertEquals(Chip.BLACK, board.get(board.toIndex(0,1)));
    }
    @Test
    void toIndex() {
        Board board = new Board();
        assertEquals(-1, board.toIndex(0, 0));
        assertEquals(-1, board.toIndex(-1, 0));
        assertEquals(-1, board.toIndex(new Point(0, 0)));
        assertEquals(0, board.toIndex(0, 1));
    }
    @Test
    void middle() {
        Board board = new Board();
        assertEquals(new Point(-1, -1), board.middle(1, 1,3,3));
    }
    @Test
    void isValidPoint() {
        Board board = new Board();
        assertTrue(board.isValidPoint(new Point(0,1)));
        assertFalse(board.isValidPoint(new Point(0, 0)));
    }
    @Test
    void toPoint() {
        Board board = new Board();
        assertEquals(new Point(0, 1), board.toPoint(0));
    }
    @Test
    void find() {
        Board board = new Board();
        List<Point> list = board.find(Chip.WHITE_KING);
        assertTrue(list.isEmpty());
    }

}
