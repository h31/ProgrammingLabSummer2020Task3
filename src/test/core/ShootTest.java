package core;

import org.junit.jupiter.api.Test;
import view.BattleShipView;

import static org.junit.jupiter.api.Assertions.*;

class ShootTest {

    @Test
    void move() {
        BattleShipView view = new BattleShipView();
        Shoot shootEnemy = new Shoot(true, view);
        Shoot shootPlayer = new Shoot(false,view);
        view.playerBoard.getCell(3,3).ship = new Ship(1,true,view.playerBoard);
        shootEnemy.move(3,3);
        view.enemyBoard.getCell(4,6).ship = new Ship(2,false,view.enemyBoard);
        shootPlayer.move(5,6);
        assertTrue(view.playerBoard.getCell(3,3).wasHit);
        assertTrue(view.enemyBoard.getCell(5,6).wasHit);
        assertFalse(view.enemyBoard.getCell(7,9).wasHit);
    }
}