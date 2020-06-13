package controller;

import core.*;

import java.io.IOException;
import java.util.Random;

import javafx.fxml.FXML;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.VBox;
import view.Board_view;
import view.Cell;
import view.Reaction;

public class BattleShipApp {
    public Reaction reaction;
    private int i = 0;
    public int j = 0;
    private Random r = new Random();
    public boolean isEnemyMove;
    public Board playerArrayBoard = new Board();
    public Board computerArrayBoard = new Board();

    @FXML
    public VBox message;

    @FXML
    public Board_view playerBoard;

    @FXML
    public Board_view enemyBoard;

    public BattleShipApp() throws IOException {
        this.reaction = new Reaction(this);
    }

    @FXML
    public void placePlayerShips() {
        if (i < 10) {
            playerBoard.cellSetAction(event -> {
                Cell cell = (Cell) event.getSource();
                Ship ship = new Ship(playerArrayBoard.shipLength[i], event.getButton() == MouseButton.PRIMARY, playerArrayBoard);
                if (ship.canPlaceShip(cell.x, cell.y)) {
                    for (Board.Cell element : ship.cells) {
                        Cell shipCell = playerBoard.getCell(element.x, element.y);
                        reaction.setShipColor(shipCell);
                        playerArrayBoard.boardsCells[element.x][element.y].ship = ship;
                    }
                    if (++i == playerArrayBoard.counter_ships) {
                        placeComputerShips();
                        playerBoard.cellSetAction(e -> {
                        });
                    }
                }
            });
        }
    }

    @FXML
    public void placeComputerShips() {
        while (j < 10) {
            int x = r.nextInt(10);
            int y = r.nextInt(10);
            boolean direction = r.nextBoolean();
            Ship ship = new Ship(computerArrayBoard.shipLength[j], direction, computerArrayBoard);
            if (ship.canPlaceShip(x, y)) {
                j++;
                for (Board.Cell cell : ship.cells) {
                    computerArrayBoard.boardsCells[cell.x][cell.y].ship = ship;
                }
                if (j == computerArrayBoard.counter_ships) {
                    reaction.showState(reaction.properties.getProperty("afterComputerPlaceShip"));
                    playerGame();
                }
            }
        }
    }

    @FXML
    public void playerGame() {
        isEnemyMove = false;
        enemyBoard.cellSetAction((event -> {
            Cell cell = (Cell) event.getSource();
            try {
                move(cell.x, cell.y, computerArrayBoard, enemyBoard);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }));
    }

    public void computerGame() throws IOException {
        isEnemyMove = true;
        int x = r.nextInt(10);
        int y = r.nextInt(10);
        move(x, y, playerArrayBoard, playerBoard);
    }

    public void move(int x, int y, Board board, Board_view board_view) throws IOException {
        Cell cell_view = board_view.getCell(x, y);
        Board.Cell cell = board.boardsCells[x][y];
        if (!cell.wasHit) {
            cell.wasHit = true;
            if (cell.ship != null) {
                reaction.setColorInjured(cell_view);
                Ship ship = cell.ship;
                ship.injured();
                if (ship.isDead()) reaction.killingShip(ship, board, board_view);
                else {
                    if (isEnemyMove) {
                        if (Board.isPoint(x, y + 1)) move(x, y + 1, board, board_view);
                    }
                }
            } else {
                reaction.setColorMissed(cell_view);
                missed();
            }
        }
        hitTheShip();
    }

    public void missed() throws IOException {
        if (isEnemyMove) playerGame();
        else computerGame();
    }

    public void hitTheShip() throws IOException {
        if (isEnemyMove) computerGame();
        else playerGame();
    }

    @FXML
    void initialize() {
        assert message != null : "fx:id=\"message\" was not injected: check your FXML file 'BattleShipApp.fxml'.";
        assert playerBoard != null : "fx:id=\"playerBoard\" was not injected: check your FXML file 'BattleShipApp.fxml'.";
        assert enemyBoard != null : "fx:id=\"enemyBoard\" was not injected: check your FXML file 'BattleShipApp.fxml'.";
    }


}

