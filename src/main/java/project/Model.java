package project;

import javafx.util.Pair;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class Model {
    //i - вертикальная ось (строки), j - горизонтальная (столбцы)

    //Массив для поля
    final private char[][] field = new char[5][5];

    //Счёт игроков
    private int firstPlayerScore;
    private int secondPlayerScore;

    //Чей ход? T - первого, F - второго
    private boolean firstToMove;

    //Множество клеток, в которые можно поставить букву
    private final Set<Pair<Integer, Integer>> possibleMoves = new HashSet<>();

    // ??? Множество занятых клеток
    //private static Set<Pair<Integer, Integer>> filledCells = new HashSet<>();

    public void fillField() {
        final String word = getFirstWord();

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (i == 2)
                    field[i][j] = word.charAt(j);
                else field[i][j] = ' ';
            }
        }

        for (int j = 0; j < 5; j++) {
            field[1][j] = '+';
            possibleMoves.add(new Pair<>(1, j));
        }
        for (int j = 0; j < 5; j++) {
            field[3][j] = '+';
            possibleMoves.add(new Pair<>(3, j));
        }

        firstToMove = true;

        firstPlayerScore = 0;
        secondPlayerScore = 0;
    }

    public void setPossibleMovesAround(int i, int j) {
        Pair<Integer, Integer> oldCoordinates = new Pair<>(i, j);
        possibleMoves.remove(oldCoordinates);

        if (i < 4 && field[i + 1][j] != ' ') {
            field[i + 1][j] = '+';
            possibleMoves.add(new Pair<>(i + 1, j));
        }
        if (i > 0 && field[i - 1][j] != ' ') {
            field[i - 1][j] = '+';
            possibleMoves.add(new Pair<>(i - 1, j));
        }
        if (j < 4 && field[i][j + 1] != ' ') {
            field[i][j + 1] = '+';
            possibleMoves.add(new Pair<>(i, j + 1));
        }
        if (j > 0 && field[i][j - 1] != ' ') {
            field[i][j - 1] = '+';
            possibleMoves.add(new Pair<>(i, j - 1));
        }
    }


    //Методы для поиска, добавления слов

    private int getNumberOfLines(String filePath) {
        int res = 0;
        try(BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String string = reader.readLine();
            while (string != null) {
                res++;
                string = reader.readLine();
            }
            return res;
        } catch (IOException e) {
            return 0;
        }
    }

    private String getFirstWord()  {
        String filePath = "/five_let_words.txt";
        BufferedReader reader =
                new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream(filePath)));
        int numOfLines = getNumberOfLines("src/main/resources" + filePath);
        int random = (int)(( Math.random() * numOfLines + 1));
        int i = 1;
        try (reader) {
            String string = reader.readLine();
            while (i != random) {
                string = reader.readLine();
                i++;
            }
            return string;
        } catch (IOException e) {
            return "балда";
        }
    }

    private boolean isWord(String checking) throws IOException {
        BufferedReader reader =
                new BufferedReader(new InputStreamReader(this.getClass().getResourceAsStream("/dictionary.txt")));
        try(reader) {
            String string = reader.readLine();
            while (string != null) {
                if (string.equals(checking)) return true;
                string = reader.readLine();
            }
            return false;
        }
    }

    public boolean addWordToDict(String word) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/dictionary.txt", true));
        try (writer) {
            writer.write("\n" + word);
        }
        return isWord(word);
    }

    //Геттеры, сеттеры

    public char getCharFromField(int i, int j) {
        return field[i][j];
    }

    public Set<Pair<Integer, Integer>> getPossibleMoves() {
        return possibleMoves;
    }

    public void setSymbol(int i, int j, char symbol) {
        field[i][j] = symbol;
    }

    public char getSymbol(int i, int j) {
        return field[i][j];
    }
}
