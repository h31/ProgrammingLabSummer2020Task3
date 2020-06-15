package project.models;

import java.util.ArrayList;
import java.util.List;

public class Field {
    private int rowNumber;
    private int tilesInRow;
    private int bombs;
    private int flagsLeft;
    private boolean gameOver = false;
    private boolean lost = false;
    private boolean fieldInitialized = false;

    private Tile[][] grid;

    public Field(int rows, int tiles, int bombs) {
        this.rowNumber = rows;
        this.tilesInRow = tiles;
        this.bombs = bombs;
        this.flagsLeft = bombs;

        this.grid = new Tile[tiles][rows];

        for (int y = 0; y < rowNumber; y++) {
            for (int x = 0; x < tilesInRow; x++) {
                Tile tile = new Tile(x, y);
                grid[x][y] = tile;
            }
        }
    }

    public int getRowNumber() {
        return rowNumber;
    }

    public int getTilesInRow() {
        return tilesInRow;
    }

    public int getBombs() {
        return bombs;
    }

    public int getFlagsLeft() {
        return flagsLeft;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public boolean isLost() {
        return lost;
    }

    public boolean isFieldInitialized() {
        return fieldInitialized;
    }

    public Tile[][] getGrid() {
        return grid;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public void setLost(boolean lost) {
        this.lost = lost;
    }

    public boolean winCheck() {
        for (Tile[] row: grid) {
            for (Tile tile: row) {
                if (!tile.isOpened() && !tile.hasBomb())
                    return false;
            }
        }

        return true;
    }

    public void initialiseField(Tile tile) {
        int i = bombs;

        while (i > 0) {
            int x = 0 + (int) (Math.random() * tilesInRow);
            int y = 0 + (int) (Math.random() * rowNumber);

            if (!grid[x][y].hasBomb() && !(x == tile.getX() && y == tile.getY())) {
                grid[x][y].setBomb(true);
                i--;
            }
        }

        for (int y = 0; y < rowNumber; y++) {
            for (int x = 0; x < tilesInRow; x++) {
                Tile emptyTile = grid[x][y];

                if (!emptyTile.hasBomb()) {
                    long bombs = getNeighbours(emptyTile)
                            .stream()
                            .filter(Tile::hasBomb)
                            .count();
                    emptyTile.setBombsAround((int) bombs);
                }
            }
        }
    }

    public void openTile(Tile tile) {
        if (!fieldInitialized) {
            initialiseField(tile);
            fieldInitialized = true;
        }

        if (tile.isMarked() || isGameOver())
            return;

        if (!tile.isOpened()) {
            tile.setOpened(true);

            if (winCheck()) {
                setGameOver(true);
            }

            if (tile.hasBomb()) {
                setGameOver(true);
                setLost(true);
                return;
            }

            if (tile.getBombsAround() == 0) {
                getNeighbours(tile).forEach(this::openTile);
            }
        }
    }

    public void markTile(Tile tile) {
        if (isGameOver() || !fieldInitialized)
            return;

        if (!tile.isOpened()) {
            if (tile.isMarked()) {
                tile.setFlag(false);
                flagsLeft++;
            } else {
                if (flagsLeft == 0)
                    return;
                tile.setFlag(true);
                flagsLeft--;
            }
        }
    }

    public void openNearTiles(Tile tile) {
        if (isGameOver() || !fieldInitialized)
            return;

        if (tile.isOpened()) {
            if (tile.getBombsAround() == 0)
                return;

            List<Tile> neighbours = getNeighbours(tile);
            long marks = neighbours.stream().filter(Tile::isMarked).count();

            if (marks == tile.getBombsAround()) {
                neighbours.forEach(this::openTile);
            }
        }
    }

    private List<Tile> getNeighbours(Tile tile) {
        List<Tile> neighbours = new ArrayList<>();

        int[][] pointsOddRow = new int[][]{
                {-1, -1},
                {-1, 0},
                {-1, 1},
                {0, -1},
                {1, 0},
                {0, 1}
        };

        int[][] pointsEvenRow = new int[][]{
                {0, -1},
                {-1, 0},
                {0, 1},
                {1, -1},
                {1, 0},
                {1, 1}
        };

        for (int i = 0; i < pointsOddRow.length; i++) {
            int x = tile.getY() % 2 == 0
                    ? tile.getX() + pointsOddRow[i][0]
                    : tile.getX() + pointsEvenRow[i][0];
            int y = tile.getY() % 2 == 0
                    ? tile.getY() + pointsOddRow[i][1]
                    : tile.getY() + pointsEvenRow[i][1];

            if (x >= 0 && x < this.tilesInRow && y >= 0 && y < this.rowNumber) {
                neighbours.add(grid[x][y]);
            }
        }

        return neighbours;
    }
}
