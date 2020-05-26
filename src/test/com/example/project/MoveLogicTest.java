package com.example.project;

import org.junit.jupiter.api.Test;

import java.awt.Point;

import static org.junit.jupiter.api.Assertions.*;

public class MoveLogicTest {
    @Test
    void isValidMove() {
        Game_checkers game = new Game_checkers();
        assertFalse(MoveLogic.isValidMove(game, 1,3));
        assertTrue(MoveLogic.isValidMove(game, 23,19));
    }
    @Test
    void validateIDs() {
        Board board = new Board();
        assertFalse(MoveLogic.validateIDs(board, true, 23, 19));
        assertTrue(MoveLogic.validateIDs(board, false,23,19));
    }
    @Test
    void validateDistance() {
        Board board = new Board();
        assertTrue(MoveLogic.validateDistance(board, true, 23, 19));
        assertFalse(MoveLogic.validateDistance(board, false,15,20));
    }
    @Test
    void isSafe() {
        Board board = new Board();
        assertTrue(MoveLogic.isSafe(board, new Point(3,3)));
    }
}
