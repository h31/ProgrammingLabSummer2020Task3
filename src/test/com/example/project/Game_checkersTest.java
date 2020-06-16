package com.example.project;

import model.com.example.project.Board;
import model.com.example.project.Game_checkers;
import model.com.example.project.Segment;
import org.junit.jupiter.api.Test;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

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
    @Test
    void isValidMove() {
        Game_checkers game = new Game_checkers();
        assertFalse(game.isValidMove(game.getBoard(), 1,3));
        assertTrue(game.isValidMove(game.getBoard(), 23,19));
    }
    @Test
    void getMoveList() {
        Game_checkers game = new Game_checkers();
        List<Segment>  moveListExpected = new ArrayList<>();
        moveListExpected.add(new Segment(new Point(5, 0), new Point(4, 1)));
        List<Segment> moveList = game.getMoveList(game.getBoard(),20);
        assertEquals(moveListExpected.get(0), moveList.get(0));
        moveList = game.getMoveList(game.getBoard());
        assertEquals(7, moveList.size());
    }
    @Test
    void getAttackList() {
        Game_checkers game = new Game_checkers();
        assertEquals( 0, game.getAttackList(game.getBoard()).size());
        game.setGameState("BLACK,BLACK,BLACK,BLACK,BLACK,BLACK,BLACK,BLACK,BLANK,BLACK,BLACK,BLACK,BLANK,BLANK,BLANK,BLANK," +
                "BLACK,BLANK,BLANK,BLANK,WHITE,WHITE,WHITE,WHITE,WHITE,WHITE,WHITE,WHITE,WHITE,WHITE,WHITE,WHITE,1,-1");
        List<Segment>  attackListExpected = new ArrayList<>();
        attackListExpected.add(new Segment(new Point(5, 0), new Point(3, 2)));
        List<Segment> attackList = game.getAttackList(game.getBoard(),20);
        assertEquals(attackListExpected.get(0), attackList.get(0));
    }
}
