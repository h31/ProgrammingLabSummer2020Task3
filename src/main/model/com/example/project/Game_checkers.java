package com.example.project;

import java.awt.Point;
import java.util.List;
import java.util.Objects;


/**
 * Этот класс представляет собой игру в шашки и гарантирует, что все сделанные ходы валидны в соответсвии с правилами
 */
public class Game_checkers {

    /** Текущее состояние доски */
    private Board board;

    /** Флаг, показывающий, что ход 1 игрока */
    private boolean isP1Turn;

    /** Индекс последнего пропуска(скипа), чтобы учесть несколько пропусков в ходе */
    private int skipIndex;

    public Game_checkers() {
        restart();
    }

    public Game_checkers(String state) {
        setGameState(state);
    }

    public Game_checkers(Board board, boolean isP1Turn, int skipIndex) {
        this.board = (board == null)? new Board() : board;
        this.isP1Turn = isP1Turn;
        this.skipIndex = skipIndex;
    }

    /**
     * Создаёт копию этой игры таким образом, что изменения в неё не влияют на оригинал
     * @return возвращает точную копию этой игры
     */
    public Game_checkers copy() {
        Game_checkers g = new Game_checkers();
        g.board = board.copy();
        g.isP1Turn = isP1Turn;
        g.skipIndex = skipIndex;
        return g;
    }

    /**
     * Сбрасывает игру в первоначальное состояние
     */
    public void restart() {
        this.board = new Board();
        this.isP1Turn = false; //!!!
        this.skipIndex = -1;
    }

    /** Попытки сделать ход из начальной точки в конечную
     * @param start	стартовая точка для хода
     * @param end   конечная точка для хода
     * @return возвращает истину, если было сделано обновление состояния игры
     */
    public boolean move(Point start, Point end) {
        if (start == null || end == null) {
            return false;
        }
        return move(Board.toIndex(start), Board.toIndex(end));
    }

    /**
     Попытки сделать ход из начальной точки в конечную
     * @param startIndex	стартовый индекс для хода
     * @param endIndex   конечный индекс для хода
     * @return возвращает истину, если было сделано обновление состояния игры
     */
    public boolean move(int startIndex, int endIndex) {

        // Валидность хода
        if (!MoveLogic.isValidMove(this, startIndex, endIndex)) {
            return false;
        }

        // Делаем ход
        Point middle = Board.middle(startIndex, endIndex);
        int midIndex = Board.toIndex(middle);
        this.board.set(endIndex, board.get(startIndex));
        this.board.set(midIndex, Chip.BLANK);
        this.board.set(startIndex, Chip.BLANK);

        // Делаем шашку дамкой, если требуется
        Point end = Board.toPoint(endIndex);
        Chip id = board.get(endIndex);
        boolean switchTurn = false;
        if (end.x == 0 && id == Chip.WHITE) {
            this.board.set(endIndex, Chip.WHITE_KING);
            switchTurn = true;
        } else if (end.x == 7 && id == Chip.BLACK) {
            this.board.set(endIndex, Chip.BLACK_KING);
            switchTurn = true;
        }

        // Проверяем, если следует передать ход
        boolean midValid = Board.isValidIndex(midIndex);
        if (midValid) {
            this.skipIndex = endIndex;
        }
        if (!midValid || MoveGenerator.getSkips(
                board.copy(), endIndex).isEmpty()) {
            switchTurn = true;
        }
        if (switchTurn) {
            this.isP1Turn = !isP1Turn;
            this.skipIndex = -1;
        }

        return true;
    }

    /**
     * Получаем копию текущей доски
     *
     * @return копию состояния доски
     */
    public Board getBoard() {
        return board.copy();
    }

    /**
     * Определяет, закончилась ли игра. Игра заканчивается, если один или оба игрока
     * не может сделать ни одного движения во время своего хода.
     *
     * @return возвращает истину, если игра закончена
     */
    public boolean isGameOver() {


        List<Point> black = board.find(Chip.BLACK);
        black.addAll(board.find(Chip.BLACK_KING));
        if (black.isEmpty()) {
            return true;
        }
        List<Point> white = board.find(Chip.WHITE);
        white.addAll(board.find(Chip.WHITE_KING));
        if (white.isEmpty()) {
            return true;
        }

        // Проверка, что текущий игрок может ходить
        List<Point> test = isP1Turn? black : white;
        for (Point p : test) {
            int i = Board.toIndex(p);
            if (!MoveGenerator.getMoves(board, i).isEmpty() ||
                    !MoveGenerator.getSkips(board, i).isEmpty()) {
                return false;
            }
        }


        // Нет ходов
        return true;
    }

    public boolean isP1Turn() {
        return isP1Turn;
    }

    public void setP1Turn(boolean isP1Turn) {
        this.isP1Turn = isP1Turn;
    }

    public int getSkipIndex() {
        return skipIndex;
    }

    /**
     * Получаем состояние игры в виде строки данных
     * @return возвращает строковое представление текущего состояния игры
     */
    public String getGameState() {

        // Добавляем игровое поле
        String state = "";
        for (int i = 0; i < 32; i ++) {
            state += "" + board.get(i) + ",";
        }

        // Добавляем другую информацию
        state += (isP1Turn? "1" : "0") + ",";
        state += skipIndex;

        return state;
    }

    /**
     * Анализирует строку, которая была сгенерирована из
     * {@link #getGameState()}.
     * @param state	состояние игры
     */
    public void setGameState(String state) {

        restart();

        // Простые случаи
        if (state == null || state.isEmpty()) {
            return;
        }

        // Обновляем поле
        String[] items = state.split(",");
        int n = items.length;
        for (int i = 0; i < 32 && i < n; i ++) {
            try {
                Chip id = Chip.valueOf(items[i]); // Integer.parseInt("" + state.charAt(i));
                this.board.set(i, id);
            } catch (NumberFormatException e) {}
        }

        // Обновляем другую инфу
        if (n > 32) {
            this.isP1Turn = (items[32].charAt(0) == '1');
        }
        if (n > 33) {
            try {
                this.skipIndex = Integer.parseInt(items[33]);
            } catch (NumberFormatException e) {
                this.skipIndex = -1;
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game_checkers that = (Game_checkers) o;
        return isP1Turn == that.isP1Turn &&
                skipIndex == that.skipIndex &&
                Objects.equals(board, that.board);
    }

    @Override
    public int hashCode() {
        return Objects.hash(board, isP1Turn, skipIndex);
    }
}
