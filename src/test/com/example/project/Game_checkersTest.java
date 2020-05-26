package com.example.project;

import org.junit.jupiter.api.Test;

import java.awt.Point;

import static org.junit.jupiter.api.Assertions.*;
public class Game_checkersTest {
    @Test
    void copy() {
        Game_checkers game = new Game_checkers();
        assertEquals(game, game.copy());
    }
    @Test
    void move() {
        Game_checkers game = new Game_checkers();
        assertTrue(game.move(new Point(5,0), new Point(4,1)));
        assertFalse(game.move(new Point(5,0), new Point(5,1)));
    }
    @Test
    void getBoard() {
        Game_checkers game = new Game_checkers();
        assertEquals(new Board(), game.getBoard());
    }
    @Test
    void isGameOver() {
        Game_checkers game = new Game_checkers();
        assertFalse(game.isGameOver());
    }
    @Test
    void isP1Turn() {
        Game_checkers game = new Game_checkers();
        assertFalse(game.isP1Turn());
    }
    @Test
    void getSkipIndex() {
        Game_checkers game = new Game_checkers();
        int skipIndex = game.getSkipIndex();
        assertEquals(-1, skipIndex);
    }
    @Test
    void getGameState() {
        Game_checkers game = new Game_checkers();
        String str = "BLACK,BLACK,BLACK,BLACK,BLACK,BLACK,BLACK,BLACK,BLACK,BLACK,BLACK,BLACK,BLANK,BLANK,BLANK,BLANK," +
                "BLANK,BLANK,BLANK,BLANK,WHITE,WHITE,WHITE,WHITE,WHITE,WHITE,WHITE,WHITE,WHITE,WHITE,WHITE,WHITE,0,-1";
        assertEquals(str, game.getGameState());
    }
    @Test
    void setGameState() {
        Game_checkers game = new Game_checkers();
        game.setGameState("BLACK,BLACK,BLACK,BLACK,BLACK,BLACK,BLACK,BLACK,BLACK,BLACK,BLACK,BLACK,BLANK,BLANK,BLANK,BLANK," +
                "BLANK,BLANK,BLANK,BLANK,WHITE,WHITE,WHITE,WHITE,WHITE,WHITE,WHITE,WHITE,WHITE,WHITE,WHITE,WHITE,1,-1");
        String str = game.getGameState();
        assertEquals("BLACK,BLACK,BLACK,BLACK,BLACK,BLACK,BLACK,BLACK,BLACK,BLACK,BLACK,BLACK,BLANK,BLANK,BLANK,BLANK," +
                "BLANK,BLANK,BLANK,BLANK,WHITE,WHITE,WHITE,WHITE,WHITE,WHITE,WHITE,WHITE,WHITE,WHITE,WHITE,WHITE,1,-1", str);
    }
}
