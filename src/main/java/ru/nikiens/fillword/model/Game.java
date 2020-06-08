package ru.nikiens.fillword.model;

import ru.nikiens.fillword.model.util.RandomizedReader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Game {
    private static Game inst;

    private Path source;
    private Cell[][] board;
    private BoardSize boardSize;

    private Set<String> words = new HashSet<>();
    private String category;

    private Game() {
    }

    public static Game getInstance() {
        if (inst == null) {
            inst = new Game();
        }
        return inst;
    }

    public Path getSource() {
        return source;
    }

    public void setSource(Path source) {
        this.source = source;
    }

    public BoardSize getBoardSize() {
        return boardSize;
    }

    public void setBoardSize(BoardSize boardSize) {
        this.boardSize = boardSize;
    }

    public Set<String> getWords() {
        return words;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Cell getCell(int x, int y) {
        return board[x][y];
    }

    public void initializeCategory() throws IOException {
        String category = Files.newBufferedReader(getSource()).readLine();
        RandomizedReader rr = new RandomizedReader(getSource());

        while (words.size() < boardSize.value() / 2) {
            String line = rr.readLine();
            if (line != null && !line.equals(category)) {
                words.add(line.toUpperCase());
            }
        }
        setCategory(category);
    }

    public void initializeBoard() {
        this.board = new Cell[getBoardSize().value()][getBoardSize().value()];
        for (int i = 0; i < getBoardSize().value(); i++) {
            for (int j = 0; j < getBoardSize().value(); j++) {
                this.board[i][j] = new Cell();
            }
        }
    }

    public void fillWithLetters() {
        Random random = new Random();

        char[] alphabet = new char[26];
        for (char c = 'A'; c <= 'Z'; c++) {
            alphabet[c - 'A'] = c;
        }

        for (int i = 0; i < getBoardSize().value(); i++) {
            for (int j = 0; j < getBoardSize().value(); j++) {
                Cell cell = getCell(i, j);
                if (cell.getLetter() == 0) {
                    cell.setLetter(alphabet[random.nextInt(alphabet.length)]);
                }
            }
        }
    }

    public void fillWithWords() {
        Random random = new Random();

        for (String word : this.words) {
            int x = 0;
            int y = 0;

            List<PlacementDirection> directions = new ArrayList<>();

            while (directions.isEmpty()) {
                x = random.nextInt(getBoardSize().value());
                y = random.nextInt(getBoardSize().value());

                directions.addAll(getAvailableDirections(x, y, word.length()));
            }

            PlacementDirection direction = directions.get(random.nextInt(directions.size()));
            placeWord(x, y, word, direction);
        }
    }

    private static boolean checkVertical(int x, int y, int length) {
        for (int i = 0; i < length; i++) {
            if (x + i < Game.getInstance().getBoardSize().value() && x >= 0 &&
                    y < Game.getInstance().getBoardSize().value() && y >= 0) {
                Cell cell = Game.getInstance().getCell(x + i, y);

                if (cell.getLetter() != 0) {
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

                if (cell.getLetter() != 0) {
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

                if (cell.getLetter() != 0) {
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

    public static void placeWord(int x, int y, String word, PlacementDirection pd) {
        for (int i = 0; i < word.length(); i++) {
            Cell cell = null;
            switch (pd) {
                case VERTICAL:
                    cell = Game.getInstance().getCell(x + i, y);
                    break;
                case HORIZONTAL:
                    cell = Game.getInstance().getCell(x, y + i);
                    break;
                case DIAGONAL:
                    cell = Game.getInstance().getCell(x + i, y + i);
                    break;
            }
            cell.setLetter(word.charAt(i));
        }
    }
}