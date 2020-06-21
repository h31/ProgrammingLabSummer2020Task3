package Model;

import java.util.Arrays;

public class Logic {

    private int[][] cells;
    private int score = 0;
    private int space = 16;

    public Logic (int[][] cells) {
        this.cells = cells;
        this.score = 0;
    }

    public int getScore() {
        return score;
    }

    public int [][] getCells() {
        return cells;
    }

    public void start() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                cells[i][j] = 0;
                score = 0;
                space = 16;
            }
        }
        randomCell(cells);
        randomCell(cells);
    }

    public boolean isWin (int[][] board) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (board[i][j] == 2048)
                    return true;
            }
        }
        return false;
    }

    public boolean canMove() {
        if (space != 0) {
            return true;
        }
        for (int i = 0; i < 4 - 1; i++) {
            for (int j = 0; j < 4 - 1; j++) {
                if (cells[i][j] == cells[i][j+1] || cells[i][j] == cells[i+1][j]) {
                    return true;
                }
            }
        }
        return false;
    }

    public void up() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (cells[i][j] == 0) {
                    for (int k = j + 1; k < 4; k++) {
                        if (cells[i][k] != 0) {
                            cells[i][j] = cells[i][k];
                            cells[i][k] = 0;
                            break;
                        }
                    }
                } else if (cells[i][j] != 0) {
                    for (int k = j + 1; k < 4; k++) {
                        if ((k - 1 == j && cells[i][k] == cells[i][j]) || (cells[i][k-1] == 0 && cells[i][k] == cells[i][j])) {
                            cells[i][j] += cells[i][k];
                            score += cells[i][j];
                            cells[i][k] = 0;
                            space++;
                        }
                    }
                }
            }
        }
    }

    public void down() {
        for (int i = 4 - 1; i >= 0; i--) {
            for (int j = 4 - 1; j >= 0; j--) {
                if (cells[i][j] == 0) {
                    for (int k = j - 1; k >= 0; k--) {
                        if (cells[i][k] != 0) {
                            cells[i][j] = cells[i][k];
                            cells[i][k] = 0;
                            break;
                        }
                    }
                } else if (cells[i][j] != 0) {
                    for (int k = j - 1; k >= 0; k--) {
                        if ((k + 1 == j && cells[i][k] == cells[i][j]) || (cells[i][k+1] == 0 && cells[i][k] == cells[i][j])) {
                            cells[i][j] += cells[i][k];
                            score += cells[i][j];
                            cells[i][k] = 0;
                            space++;
                        }
                    }
                }
            }
        }
    }

    public void right() {
        for (int i =  4 - 1; i >= 0; i--) {
            for (int j = 4 - 1; j >= 0; j--) {
                if (cells[j][i] == 0) {
                    for (int k = j - 1; k >= 0; k--) {
                        if (cells[k][i] != 0) {
                            cells[j][i] = cells[k][i];
                            cells[k][i] = 0;
                            break;
                        }
                    }
                } else if (cells[j][i] != 0) {
                    for (int k = j - 1; k >= 0; k--) {
                        if ((k + 1 == j && cells[k][i] == cells[j][i]) || (cells[k+1][i] == 0 && cells[k][i] == cells[j][i])) {
                            cells[j][i] += cells[k][i];
                            score += cells[j][i];
                            cells[k][i] = 0;
                            space++;
                        }
                    }
                }
            }
        }
    }

    public void left() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (cells[j][i] == 0) {
                    for (int k = j + 1; k < 4; k++) {
                        if (cells[k][i] != 0) {
                            cells[j][i] = cells[k][i];
                            cells[k][i] = 0;
                            break;
                        }
                    }
                } else if (cells[j][i] != 0) {
                    for (int k = j + 1; k < 4; k++) {
                        if ((k - 1 == j && cells[k][i] == cells[j][i]) || (cells[k-1][i] == 0 && cells[k][i] == cells[j][i])) {
                            cells[j][i] += cells[k][i];
                            score += cells[j][i];
                            cells[k][i] = 0;
                            space++;
                        }
                    }
                }
            }
        }
    }

    private boolean isSpots(int [][] cells) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (cells[i][j] == 0)
                    return true;
            }
        }
        return false;
    }

    public void randomCell(int[][] cells) {
        boolean done = false;
        while (!done) {
            int randomX = (int) (Math.random() * 4);
            int randomY = (int) (Math.random() * 4);
            if (cells[randomY][randomX] == 0) {
                cells[randomY][randomX] = Math.random() < 0.9 ? 2 : 4;
                done = true;
            }
            if (!done)
                done = !isSpots(cells);
        }
    }

    public int[][] clone(int[][] cells) {
        int[][] newBoard = new int[4][4];
        Logic board = new Logic(cells);
        for (int i = 0; i < board.cells.length; i++) {
            for (int j = 0; j < board.cells[i].length; j++) {
                newBoard[i][j] = board.cells[i][j];
            }
        }
        return newBoard;
    }

    public boolean looseGame (int[][] cells) {
        int[][] myBoard = new int[4][4];
        Logic test = new Logic(cells);
        myBoard = test.getCells().clone();
        test.up();
        test.down();
        test.right();
        test.left();
        return Arrays.deepEquals(myBoard, test.getCells());
    }

}
