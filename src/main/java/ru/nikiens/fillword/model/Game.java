package ru.nikiens.fillword.model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Game {
    private static Game inst;

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

    public void initializeCategory(Path source) throws IOException {
        Random random = new Random();

        try (Stream<String> lines = Files.lines(source)) {
            lines.findFirst().ifPresent(this::setCategory);
        }

        while (words.size() <= boardSize.value() / 2) {
            try (Stream<String> lines = Files.lines(source)) {
                Set<String> wds = lines.skip(1)
                        .filter(it -> random.nextInt(3) == 0)
                        .limit(boardSize.value() / 2 + 1)
                        .map(String::toUpperCase)
                        .collect(Collectors.toSet());

                words.addAll(wds);
            }
        }

        words = words.stream()
                .sorted()
                .collect(Collectors.toCollection(LinkedHashSet::new));
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

    private PlacementDirection checkDirection(int x, int y, int length, PlacementDirection pd) {
        for (int i = 0; i < length; i++) {
            int x1 = -1;
            int y1 = -1;

            switch (pd) {
                case VERTICAL:
                    x1 = x + i;
                    y1 = y;
                    break;
                case HORIZONTAL:
                    x1 = x;
                    y1 = y + i;
                    break;
                case DIAGONAL:
                    x1 = x + i;
                    y1 = y + i;
                    break;
            }

            if (x1 < getBoardSize().value() && x >= 0 &&
                    y1 < getBoardSize().value() && y >= 0) {
                Cell cell = getCell(x1, y1);

                if (cell.getLetter() != 0) {
                    return null;
                }
            } else {
                return null;
            }
        }
        return pd;
    }

    private List<PlacementDirection> getAvailableDirections(int x, int y, int length) {
        List<PlacementDirection> pds = new ArrayList<>();

        for (PlacementDirection value : PlacementDirection.values()) {
            PlacementDirection pd = checkDirection(x, y, length, value);
            if (pd != null) {
                pds.add(pd);
            }
        }

        return pds;
    }

    private void placeWord(int x, int y, String word, PlacementDirection pd) {
        for (int i = 0; i < word.length(); i++) {
            Cell cell = null;
            switch (pd) {
                case VERTICAL:
                    cell = getCell(x + i, y);
                    break;
                case HORIZONTAL:
                    cell = getCell(x, y + i);
                    break;
                case DIAGONAL:
                    cell = getCell(x + i, y + i);
                    break;
            }
            cell.setLetter(word.charAt(i));
        }
    }
}