package model;

public class Logic {

    private int[][] cells;
    private int score;

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
        score = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                cells[i][j] = 0;
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
                            break;
                        }
                    }
                }
            }
        }
    }

    public void down() {
        for (int i = 3; i >= 0; i--) {
            for (int j = 3; j >= 0; j--) {
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
                            break;
                        }
                    }
                }
            }
        }
    }

    public void right() {
        for (int i = 3; i >= 0; i--) {
            for (int j = 3; j >= 0; j--) {
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
                            break;
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
                            break;
                        }
                    }
                }
            }
        }
    }

    private boolean isZero(int [][] cells) {
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
            if (!done) {
                done = !isZero(cells);
            }
        }
    }
}
