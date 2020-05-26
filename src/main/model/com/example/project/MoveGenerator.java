package com.example.project;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

//данный класс представляет методы является ли ход шашкой корректным или нужно его пропустить
public class MoveGenerator {

    //возвращает список конечных точек перемещения для заданного индекса
    public static List<Point> getMoves(Board board, Point start) {
        return getMoves(board, Board.toIndex(start));
    }

    //возвращает список конечных точек перемещения для заданного индекса
    public static List<Point> getMoves(Board board, int startIndex) {

        //Простые случаи
        List<Point> endPoints = new ArrayList<>();
        if (board == null || !board.isValidIndex(startIndex)) {
            return endPoints;
        }

        //описывает возможные точки
        Chip id = board.get(startIndex);
        Point p = board.toPoint(startIndex);
        addPoints(endPoints, p, id, 1);

        //удаляем некорректные точки
        for (int i = 0; i < endPoints.size(); i++) {
            Point end = endPoints.get(i);
            if (board.get(end.x, end.y) != Chip.BLANK) {
                endPoints.remove(i--);
            }
        }
        return endPoints;
    }

    //возвращает список конечных точек, на которые мы можем бить
    public static List<Point> getSkips(Board board, Point start) {
        return getSkips(board, Board.toIndex(start));
    }

    //возвращает список конеччных точек, которые мы можем бить
    public static List<Point> getSkips(Board board, int startIndex) {

        //простые случаи
        List<Point> endPoints = new ArrayList<>();
        if (board == null || !Board.isValidIndex(startIndex)) {
            return endPoints;
        }

        //определяем возможные точки
        Chip id = board.get(startIndex);
        Point p = Board.toPoint(startIndex);
        addPoints(endPoints, p, id, 2);

        //удаляем некорректные точки
        for (int i = 0; i < endPoints.size(); i++) {
            Point end = endPoints.get(i);
            if (!isValidSkip(board, startIndex, Board.toIndex(end))) {
                endPoints.remove(i--);
            }
        }
        return endPoints;
    }

    //проверяет корректен ли удар
    public static boolean isValidSkip(Board board, int startIndex, int endIndex) {
        if (board == null) {
            return false;
        }

        //проверяет пустая ли клетка на которую мы встаем после удара
        if (board.get(endIndex) != Chip.BLANK) {
            return false;
        }

        //проверяет что клетка между начальной и конечной(средняя) - вражеская(на ней находится враг)
        Chip id = board.get(startIndex);
        Chip midID = board.get(Board.toIndex(Board.middle(startIndex, endIndex)));
        if (id == Chip.INVALID || id == Chip.BLANK) {
            return false;
        } else if (midID == Chip.INVALID || midID == Chip.BLANK) {
            return false;
        } else if ((midID == Chip.BLACK || midID == Chip.BLACK_KING)
                ^ (id == Chip.WHITE || id == Chip.WHITE_KING)) {
            return false;
        }

        return true;
    }

    //добавляет точки, которые потенциально могут привести к перемещению или удару
    public static void addPoints(List<Point> points, Point p, Chip id, int delta) {

        //добавление точек при перемещении вниз
        boolean isKing = (id == Chip.BLACK_KING || id == Chip.WHITE_KING);
        if (isKing || id == Chip.BLACK) {
            points.add(new Point(p.x + delta, p.y + delta));
            points.add(new Point(p.x - delta, p.y + delta));
        }

        //добавление точки при перемещении вверх
        if (isKing || id == Chip.WHITE) {
            points.add(new Point(p.x + delta, p.y - delta));
            points.add(new Point(p.x - delta, p.y - delta));
        }
    }
}
