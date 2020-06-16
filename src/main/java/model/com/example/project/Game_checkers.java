package model.com.example.project;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Game_checkers {

    /** Текущее состояние доски */
    private Board board;

    /** Флаг, показывающий, что ход 1 игрока */
    private boolean isP1Turn;

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
        this.isP1Turn = true; //!!! Первыми ходят белые
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
     * @return возвращает истину, если был сделан ход
     */
    public boolean move(int startIndex, int endIndex) {

        // проверяем валидность хода
        if (!isValidMove(board, startIndex, endIndex)) {
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
        if (!midValid || getAttackList(
                    board.copy(), endIndex).isEmpty()) {
            switchTurn = true;
        }
        if (switchTurn) {
            this.isP1Turn = !isP1Turn;
            this.skipIndex = -1;
        }

        return true;
    }

    public boolean isValidMove(Board b, int startIndex, int endIndex){
        //начальная и конечная точка хода корректны
        if (!Board.isValidIndex(startIndex) || !Board.isValidIndex(endIndex)) {
            return false;
        }
        //если ход черных, то ходят только черные
        if (isP1Turn == false && (b.get(startIndex) != Chip.BLACK && b.get(startIndex) != Chip.BLACK_KING)){
            return false;
        }
        //если ход белых, то ходят только белые
        if (isP1Turn == true && (b.get(startIndex) != Chip.WHITE && b.get(startIndex) != Chip.WHITE_KING)){
            return false;
        }
        //на начальной точке есть фигура, а конечная точка пустая
        if (b.get(startIndex) == Chip.BLANK || b.get(endIndex) != Chip.BLANK) {
            return false;
        }
        //белые ходят только вверх, поэтому индекс уменьшается
        if (b.get(startIndex) == Chip.WHITE && startIndex <= endIndex)
            return false;
        //черные ходят только вниз, поэтому индекс увеличивается
        if (b.get(startIndex) == Chip.BLACK && startIndex >= endIndex) {
            return false;
        }
        //смотрим, есть ли ходы на взятие
        if (!getAttackList(b).isEmpty()){
            List<Segment> seg = getAttackList(b, startIndex);
            //проверяем, наш ход это ход на взятие
            if (!seg.contains(new Segment(Board.toPoint(startIndex), Board.toPoint(endIndex)))){
                return false;
            }
        }

        return true;
    }

    /*
    Получаем список "взятий" для всех пешек
     */
    public List<Segment> getAttackList(Board b){
        List<Point> checkers;
        List<Segment> AttackList = new ArrayList<>();

        //ход черных
        if (!isP1Turn) {
            //берем все черные шашки
            checkers = b.find(Chip.BLACK);
            //а также черные дамки
            checkers.addAll(b.find(Chip.BLACK_KING));
        }
        else {
            //берем все белые шашки
            checkers = b.find(Chip.WHITE);
            //а также белые дамки
            checkers.addAll(b.find(Chip.WHITE_KING));
        }
        for(int i = 0; i < checkers.size(); i++){
            Point p = checkers.get(i);
            AttackList.addAll(getAttackList(b, Board.toIndex(p)));
        }

        return AttackList;
    }

    /*
    Получаем список "взятий" для пешки index
     */
    public List<Segment> getAttackList(Board b, int index){
        List<Segment> AttackList = new ArrayList<>();
        int delta = 2;

        Point p = Board.toPoint(index);
        // находим цвет пешки противника
        Chip enemy = b.get(index) == Chip.WHITE || b.get(index) == Chip.WHITE_KING ? Chip.BLACK : Chip.WHITE;
        // находим цвет дамки противника
        Chip enemyKing = b.get(index) == Chip.WHITE || b.get(index) == Chip.WHITE_KING ? Chip.BLACK_KING : Chip.WHITE_KING;
        if (b.get(index) == Chip.WHITE || b.get(index) == Chip.WHITE_KING || b.get(index) == Chip.BLACK_KING) {
            //смотрим диагональ, может ли шашка бить
            //вверх в лево
            if (Board.isValidPoint(new Point(p.x - delta, p.y - delta))) {
                if (b.get(Board.toIndex(new Point(p.x - delta, p.y - delta))) == Chip.BLANK &&
                        (b.get(Board.toIndex(new Point(p.x - (delta - 1), p.y - (delta - 1)))) == enemy ||
                        b.get(Board.toIndex(new Point(p.x - (delta - 1), p.y - (delta - 1)))) == enemyKing)) {
                    AttackList.add(new Segment(new Point(p.x, p.y), new Point(p.x - delta, p.y - delta)));
                }
            }
            //вверх в право
            if (Board.isValidPoint(new Point(p.x - delta, p.y + delta))) {
                if (b.get(Board.toIndex(new Point(p.x - delta, p.y + delta))) == Chip.BLANK &&
                        (b.get(Board.toIndex(new Point(p.x - 1, p.y + 1))) == enemy ||
                        b.get(Board.toIndex(new Point(p.x - 1, p.y - 1))) == enemyKing)) {
                    AttackList.add(new Segment(new Point(p.x, p.y), new Point(p.x - delta, p.y + delta)));
                }
            }
        }
        if(b.get(index) == Chip.BLACK || b.get(index) == Chip.WHITE_KING || b.get(index) == Chip.BLACK_KING) {
            //смотрим диагональ, может ли шашка бить
            //в низ в лево
            if (Board.isValidPoint( new Point(p.x + delta, p.y - delta))){
                if (b.get(Board.toIndex(new Point(p.x + delta, p.y - delta))) == Chip.BLANK &&
                        (b.get(Board.toIndex(new Point(p.x + 1, p.y - 1))) == enemy ||
                        b.get(Board.toIndex(new Point(p.x + 1, p.y - 1))) == enemyKing)){
                    AttackList.add(new Segment(new Point(p.x, p.y), new Point(p.x + delta, p.y - delta)));
                }
            }
            //в низ в право
            if (Board.isValidPoint(new Point(p.x + delta, p.y + delta))) {
                if (b.get(Board.toIndex(new Point(p.x + delta, p.y + delta))) == Chip.BLANK &&
                        (b.get(Board.toIndex(new Point(p.x + 1, p.y + 1))) == enemy ||
                        b.get(Board.toIndex(new Point(p.x + 1, p.y + 1))) == enemyKing)) {
                    AttackList.add(new Segment(new Point(p.x, p.y), new Point(p.x + delta, p.y + delta)));
                }
            }
        }

        return AttackList;

    }

    /*
    Возвращаем список всех доступных ходов
     */
    public List<Segment> getMoveList(Board b){
        List<Point> checkers;
        List<Segment> MoveList = new ArrayList<>();

        //ход черных
        if (isP1Turn == false) {
            //берем все черные шашки
            checkers = b.find(Chip.BLACK);
            //а также черные дамки
            checkers.addAll(b.find(Chip.BLACK_KING));
        }
        else {
            //берем все белые шашки
            checkers = b.find(Chip.WHITE);
            //а также белые дамки
            checkers.addAll(b.find(Chip.WHITE_KING));

        }
        //получаем список ходов для каждой пешки
        for(int i = 0; i < checkers.size(); i++){
            Point p = checkers.get(i);
            MoveList.addAll(getMoveList(b, Board.toIndex(p)));
        }

        return MoveList;
    }

    /*
    Возвращаем список возможных ходов для пешки index
     */
    public List<Segment> getMoveList(Board b, int index){
        List<Segment> MoveList = new ArrayList<>();
        int delta = 1;

        Point p = Board.toPoint(index);
        if (b.get(index) == Chip.WHITE || b.get(index) == Chip.WHITE_KING || b.get(index) == Chip.BLACK_KING) {
            //смотрим диагональ, может ли шашка ходить
            //вверх в лево
            if (Board.isValidPoint(new Point(p.x - delta, p.y - delta))) {
                if (b.get(Board.toIndex(new Point(p.x - delta, p.y - delta))) == Chip.BLANK) {
                    MoveList.add(new Segment(new Point(p.x, p.y), new Point(p.x - delta, p.y - delta)));
                }
            }
            //вверх в право
            if (Board.isValidPoint(new Point(p.x - delta, p.y + delta))) {
                if (b.get(Board.toIndex(new Point(p.x - delta, p.y + delta))) == Chip.BLANK) {
                    MoveList.add(new Segment(new Point(p.x, p.y), new Point(p.x - delta, p.y + delta)));
                }
            }
        }
        if(b.get(index) == Chip.BLACK || b.get(index) == Chip.WHITE_KING || b.get(index) == Chip.BLACK_KING) {
            //смотрим диагональ, может ли шашка ходить
            //в низ в лево
            if (Board.isValidPoint( new Point(p.x + delta, p.y - delta))){
                if (b.get(Board.toIndex(new Point(p.x + delta, p.y - delta))) == Chip.BLANK){
                    MoveList.add(new Segment(new Point(p.x, p.y), new Point(p.x + delta, p.y - delta)));
                }
            }
            //в низ в право
            if (Board.isValidPoint(new Point(p.x + delta, p.y + delta))) {
                if (b.get(Board.toIndex(new Point(p.x + delta, p.y + delta))) == Chip.BLANK) {
                    MoveList.add(new Segment(new Point(p.x, p.y), new Point(p.x + delta, p.y + delta)));
                }
            }
        }

        return MoveList;

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

        // Проверка, что текущий игрок может ходить
        if (!getMoveList(board).isEmpty() || !getAttackList(board).isEmpty()) {
            return false;
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
     * Восстанавливает из строки state состояние доски
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
