package ru.nikiens.fillword.model;

import java.util.ArrayList;
import java.util.List;

public class WordPlaceholder {
    private WordPlaceholder() {
    }

    private static boolean checkVertical(int x, int y, int length) {
        for (int i = 0; i < length; i++) {
            if (x + i < Game.getInstance().getBoardSize().value() && x >= 0 &&
                    y < Game.getInstance().getBoardSize().value() && y >= 0) {
                Cell cell = Game.getInstance().getCell(x + i, y);

                if (cell.getState() != null) {
                    return false;
                }
            } else {
                return false;
            }
        }
        return true;
    }

    private static boolean checkHorizontal(int x, int y, int length) {
        for (int i = 0; i < length; i++) {
            if (y + i < Game.getInstance().getBoardSize().value() && y >= 0 &&
                    x < Game.getInstance().getBoardSize().value() && x >= 0) {
                Cell cell = Game.getInstance().getCell(x, y + i);

                if (cell.getState() != null) {
                    return false;
                }
            } else {
                return false;
            }
        }
        return true;
    }

    private static boolean checkDiagonal(int x, int y, int length) {
        for (int i = 0; i < length; i++) {
            if (x + i < Game.getInstance().getBoardSize().value() && x >= 0 &&
                    y + i < Game.getInstance().getBoardSize().value() && y >= 0) {
                Cell cell = Game.getInstance().getCell(x + i, y + i);

                if (cell.getState() != null) {
                    return false;
                }
            } else {
                return false;
            }
        }
        return true;
    }

    public static List<PlacementDirection> getAvailableDirections(int x, int y, int length) {
        List<PlacementDirection> list = new ArrayList<>();

        if (checkHorizontal(x, y, length)) list.add(PlacementDirection.HORIZONTAL);
        if (checkVertical(x, y, length)) list.add(PlacementDirection.VERTICAL);
        if (checkDiagonal(x, y, length)) list.add(PlacementDirection.DIAGONAL);

        return list;
    }

    private static void fillVertical(int x, int y, String word) {
        for (int i = 0; i < word.length(); i++) {
            Cell cell = Game.getInstance().getCell(x + i, y);
            cell.setText(Character.toString(word.charAt(i)));
            cell.setState(CellState.UNMARKED);
        }
    }

    private static void fillHorizontal(int x, int y, String word) {
        for (int i = 0; i < word.length(); i++) {
            Cell cell = Game.getInstance().getCell(x, y + i);
            cell.setText(Character.toString(word.charAt(i)));
            cell.setState(CellState.UNMARKED);
        }
    }

    private static void fillMajorDiagonal(int x, int y, String word) {
        for (int i = 0; i < word.length(); i++) {
            Cell cell = Game.getInstance().getCell(x + i, y + i);
            cell.setText(Character.toString(word.charAt(i)));
            cell.setState(CellState.UNMARKED);
        }
    }

    public static void placeWord(int x, int y, String word, PlacementDirection pd) {
        switch (pd) {
            case VERTICAL:
                fillVertical(x, y, word);
                break;
            case HORIZONTAL:
                fillHorizontal(x, y, word);
                break;
            case DIAGONAL:
                fillMajorDiagonal(x, y, word);
                break;
        }
    }
}
