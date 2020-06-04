package ru.nikiens.fillword.model;

import ru.nikiens.fillword.model.util.RandomizedReader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

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

    public void setWords(Set<String> words) {
        this.words = words;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Cell[][] getBoard() {
        return board;
    }

    public void initCategory() throws IOException {
        String category = Files.newBufferedReader(getSource()).readLine();
        RandomizedReader rr = new RandomizedReader(getSource());

        while (words.size() < boardSize.value() / 2) {
            String line = rr.readLine();
            if (line != null && !line.equals(category)) {
                words.add(line);
            }
        }
        setCategory(category);
    }

    public void generateBoard() {
        this.board = new Cell[getBoardSize().value()][getBoardSize().value()];

        for (int i = 0; i < getBoardSize().value(); i++) {
            for (int j = 0; j < getBoardSize().value(); j++) {
                this.board[i][j] = new Cell(i, j, "s");
            }
        }
    }
}
