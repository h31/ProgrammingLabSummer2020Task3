package core;

import view.BattleShipView;

public class Shoot {
    public BattleShipView view;
    public int i;
    public boolean isEnemyMove;
    public Board board;

    public Shoot(boolean isEnemyMove, BattleShipView view) {
        this.isEnemyMove = isEnemyMove;
        this.view = view;
        if (isEnemyMove) {
            this.board = view.playerBoard;
        } else this.board = view.enemyBoard;
    }

    public void move(int x, int y) {
        Cell cell = board.getCell(x, y);
        if (!cell.wasHit) {
            cell.wasHit = true;
            if (cell.ship != null) {
                view.reaction.setColorInjured(cell);
                Ship ship = cell.ship;
                ship.injured();
                if (ship.isDead()) {
                    i++;
                    for (Cell e : ship.shipsNeighbours()) {
                        Cell element = board.getCell(e.x, e.y);
                        view.reaction.setColorMissed(element);
                    }
                    if (i == 10) {
                        view.reaction.concludingPhrase(isEnemyMove);
                    }
                } else if (isEnemyMove && Cell.isPoint(x + 1,y))move(x + 1,y);
                if (isEnemyMove) view.computerShips.computerGame();
                else view.controller.playerGame();
            } else {
                view.reaction.setColorMissed(cell);
                if (isEnemyMove) {
                    view.controller.playerGame();
                } else view.computerShips.computerGame();
            }
        } else if (isEnemyMove) {
            view.computerShips.computerGame();
        } else view.controller.playerGame();
    }
}
