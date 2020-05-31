package controller;

import core.Cell;
import core.Ship;
import core.Shoot;
import javafx.scene.input.MouseButton;
import view.BattleShipView;

public class BoardController {
    private BattleShipView view;
    private int[] shipLength = {4, 3, 3, 2, 2, 2, 1, 1, 1, 1};
    private int i = 0;
    Shoot shoot;

    public BoardController(BattleShipView view) {
        this.view = view;
        shoot = new Shoot(false, view);
    }

    public void placePlayerShips() {
        view.playerBoard.cellSetAction(e -> {
            Cell cell = (Cell) e.getSource();
            Ship ship = new Ship(shipLength[i], e.getButton() == MouseButton.PRIMARY, view.playerBoard);
            if (ship.canPlaceShip(cell.x, cell.y)) {
                for (Cell element : ship.cells) {
                    Cell shipCell = view.playerBoard.getCell(element.x, element.y);
                    view.reaction.setShipColor(shipCell);
                    shipCell.ship = ship;
                }
                if (++i == shipLength.length) {
                    view.reaction.justPhrase(false);
                    playerGame();
                }
            }
        });
    }

    public void playerGame() {
        view.enemyBoard.cellSetAction((event -> {
            Cell cell = (Cell) event.getSource();
            shoot.move(cell.x, cell.y);
        }));
    }
}

