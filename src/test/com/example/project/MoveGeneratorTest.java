package com.example.project;

import org.junit.jupiter.api.Test;

import java.awt.Point;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
public class MoveGeneratorTest {
    @Test
    void getMoves() {
        Board board = new Board();
        assertEquals(new ArrayList<Point>(), MoveGenerator.getMoves(board, 0));
    }
    @Test
    void getSkips() {
        Board board = new Board();
        assertEquals(new ArrayList<Point>(), MoveGenerator.getSkips(board, 0));
    }
    @Test
    void isValidSkip() {
        Board board = new Board();
        assertFalse(MoveGenerator.isValidSkip(board, 0, 5));
    }
}
