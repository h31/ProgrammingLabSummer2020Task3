package com.example.project;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

/**
 * Этот класс описывает, что считать валидным ходом с учётом правил шашек */
public class MoveLogic {

    /**
     * Описывает определенные ходы в соответсвии с правилами игры
     * @param game			игру, которую проверяем(доску)
     * @param startIndex	начальный индекс хода
     * @param endIndex		конечный индекс хода
     * @return возвращает истину, если ход делается в соответсвии с правилами
     */
    public static boolean isValidMove(Game_checkers game,
                                      int startIndex, int endIndex) {
        return game == null? false : isValidMove(game.getBoard(),
                game.isP1Turn(), startIndex, endIndex, game.getSkipIndex());
    }

    /**
     * Описывает валидный ли определённый ход с учетом правил
     * @param board			текущая доска
     * @param isP1Turn		флаг, что ход первого игрока
     * @param startIndex	стартовый индекс хода
     * @param endIndex		конечный индекс хода
     * @param skipIndex		индекс последнего пропуска(skip) в этом ходу
     * @return возвращает истину, если ход легален */
    public static boolean isValidMove(Board board, boolean isP1Turn,
                                      int startIndex, int endIndex, int skipIndex) {

        // базовые проверки
        if (board == null || !Board.isValidIndex(startIndex) ||
                !Board.isValidIndex(endIndex)) {
            return false;
        } else if (startIndex == endIndex) {
            return false;
        } else if (Board.isValidIndex(skipIndex) && skipIndex != startIndex) {
            return false;
        }

        // Тесты для подтверждения перемещения
        if (!validateIDs(board, isP1Turn, startIndex, endIndex)) {
            return false;
        } else if (!validateDistance(board, isP1Turn, startIndex, endIndex)) {
            return false;
        }

        // проверены все варианты
        return true;
    }

    /**
     * Проверяет все связанные с идентификатором значения для начала, конца и середины (если
     * * перемещение-это пропуск)
     *
     * @param board			текущая доска
     * @param isP1Turn		флаг, что ход первого игрока
     * @param startIndex	стартовый индекс хода
     * @param endIndex		конечный индекс хода
     * @return возвращает истину, если только все идентификаторы валидны */
    protected static boolean validateIDs(Board board, boolean isP1Turn,
                                       int startIndex, int endIndex) {

        // Проверяет пустая ли конечная точка
        if (board.get(endIndex) != Chip.BLANK) {
            return false;
        }

        // Проверяет правильность ID
        Chip id = board.get(startIndex);
        if ((isP1Turn && id != Chip.BLACK && id != Chip.BLACK_KING)
                || (!isP1Turn && id != Chip.WHITE
                && id != Chip.WHITE_KING)) {
            return false;
        }

        // Проверяет середину
        Point middle = Board.middle(startIndex, endIndex);
        Chip midID = board.get(Board.toIndex(middle));
        if (midID != Chip.INVALID && ((!isP1Turn &&
                midID != Chip.BLACK && midID != Chip.BLACK_KING) ||
                (isP1Turn && midID != Chip.WHITE &&
                        midID != Chip.WHITE_KING))) {
            return false;
        }

        // прошли все проверки
        return true;
    }

    /**
     * Проверяет, что движение является диагональным и величиной 1 или 2 в правильном направлении.
     * Если величина не равна 2 (т. е. не пропуск), он проверяет, что
     * никакие скипы не доступны другим шашкам того же игрока.
     *
     * @param board			текущая доска
     * @param isP1Turn		флаг, что ход первого игрока
     * @param startIndex	стартовый индекс хода
     * @param endIndex		конечный индекс хода
     * @return возвращает истину только тогда, когда допустимое расстояние для перемещения
     */
    protected static boolean validateDistance(Board board, boolean isP1Turn,
                                            int startIndex, int endIndex) {

        // Проверяет, что это диагональный ход
        Point start = Board.toPoint(startIndex);
        Point end = Board.toPoint(endIndex);
        int dx = end.x - start.x;
        int dy = end.y - start.y;
        if (Math.abs(dx) != Math.abs(dy) || Math.abs(dx) > 2 || dx == 0) {
            return false;
        }

        // Проверяет, что ход в правильном направлении
        Chip id = board.get(startIndex);
        if ((id == Chip.WHITE && dx > 0) ||
                (id == Chip.BLACK && dx < 0)) {
            return false;
        }

        // Если ход не пропуск(скип), то таких скипов нет в наличии
        Point middle = Board.middle(startIndex, endIndex);
        Chip midID = board.get(Board.toIndex(middle));
        if (midID == Chip.INVALID) {

            // получить корректные шашки
            List<Point> checkers;
            if (isP1Turn) {
                checkers = board.find(Chip.BLACK);
                checkers.addAll(board.find(Chip.BLACK_KING));
            } else {
                checkers = board.find(Chip.WHITE);
                checkers.addAll(board.find(Chip.WHITE_KING));
            }

            // Проверить, если у кого-нибудь из них свободный пропуск(скип)
            // Check if any of them have a skip available
            for (Point p : checkers) {
                int index = Board.toIndex(p);
                if (!MoveGenerator.getSkips(board, index).isEmpty()) {
                    return false;
                }
            }
        }

        // Проверены все варианты
        return true;
    }

    /**
     * Проверяет, в безопасности ли определенная шашка(то есть соперник не может её пропустить)
     * @param board		текущее состояние доски
     * @param checker	точка, которую проверяем и на которой есть шашка
     * @return возвращает истину только, если шашка на указанной точке в безопасности
     */
    public static boolean isSafe(Board board, Point checker) {

        // Простые случаи
        if (board == null || checker == null) {
            return true;
        }
        int index = Board.toIndex(checker);
        if (index < 0) {
            return true;
        }
        Chip id = board.get(index);
        if (id == Chip.BLACK) {
            return true;
        }

        // Определяем, можно ли её(шашку) пропустить
        boolean isBlack = (id == Chip.BLACK || id == Chip.BLACK_KING);
        List<Point> check = new ArrayList<>();
        MoveGenerator.addPoints(check, checker, Chip.BLACK_KING, 1);
        for (Point p : check) {
            int start = Board.toIndex(p);
            Chip tid = board.get(start);

            // Ничего не происходит
            if (tid == Chip.BLANK || tid == Chip.INVALID) {
                continue;
            }

            // Проверяем идентификатор
            boolean isWhite = (tid == Chip.WHITE ||
                    tid == Chip.WHITE_KING);
            if (isBlack && !isWhite) {
                continue;
            }
            boolean isKing = (tid == Chip.WHITE_KING || tid == Chip.BLACK_KING);

            // Определяем, является ли допустимым направление пропуска(скипа)
            int dx = (checker.x - p.x) * 2;
            int dy = (checker.y - p.y) * 2;
            if (!isKing && (isWhite ^ (dy < 0))) {
                continue;
            }
            int endIndex = Board.toIndex(new Point(p.x + dx, p.y + dy));
            if (MoveGenerator.isValidSkip(board, start, endIndex)) {
                return false;
            }
        }

        return true;
    }
}
