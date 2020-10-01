package model;

public class Logic {

    private int[][] cells;
    private int score;

    public Logic(int[][] cells) {
        this.cells = cells;
        this.score = 0;
    }

    public int getScore() {
        return score;
    }

    public int[][] getCells() {
        return cells;
    }

    public void start(int[][] cells) {
        score = 0;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                cells[i][j] = 0;
            }
        }
        randomCell(cells);
        randomCell(cells);
    }

    public boolean isWin(int[][] board) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (board[i][j] == 2048)
                    return true;
            }
        }
        return false;
    }

    public void move(int[][] cells, String key) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (key.equals("Up") || key.equals("Down")) {
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
                            if ((k - 1 == j && cells[k][i] == cells[j][i]) ||
                                    (cells[k - 1][i] == 0 && cells[j + 1][i] == 0 && cells[k][i] == cells[j][i])) {
                                cells[j][i] += cells[k][i];
                                score += cells[j][i];
                                cells[k][i] = 0;
                                break;
                            }
                        }
                    }
                } else if (key.equals("Right") || key.equals("Left")) {
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
                            if ((k - 1 == j && cells[i][k] == cells[i][j]) ||
                                    (cells[i][k - 1] == 0 && cells[i][j + 1] == 0 && cells[i][k] == cells[i][j])) {
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
    }

    private boolean isZero(int[][] cells) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (cells[i][j] == 0)
                    return true;
            }
        }
        return false;
    }

    public void randomCell(int[][] cells) {
        boolean done = true;
        while (done) {
            int randomX = (int) (Math.random() * 4);
            int randomY = (int) (Math.random() * 4);
            if (cells[randomX][randomY] == 0) {
                cells[randomX][randomY] = Math.random() < 0.9 ? 2 : 4;
                return;
            }
            done = isZero(cells);
        }
    }

    public static int[][] rotate(int[][] cells) {
        int[][] b = new int[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                b[i][j] = cells[3 - j][i];
            }
        }
        return b;
    }
}
