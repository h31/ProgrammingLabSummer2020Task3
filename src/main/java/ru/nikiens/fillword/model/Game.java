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
                this.board[i][j] = new Cell(i, j);
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
                if (cell.getState() == null) {
                    cell.setText(Character.toString(alphabet[random.nextInt(alphabet.length)]));
                    cell.setState(CellState.UNMARKED);
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

                directions.addAll(WordPlaceholder.getAvailableDirections(x, y, word.length()));
            }

            PlacementDirection direction = directions.get(random.nextInt(directions.size()));
            WordPlaceholder.placeWord(x, y, word, direction);
        }
    }
}
