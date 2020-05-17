package project;

import javafx.util.Pair;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class Model {
    //Массив для поля
    final private static char[][] field = new char[5][5];

    //Счёт игроков
    private static short firstPlayerScore;
    private static short secondPlayerScore;

    //Чей ход? T - первого, F - второго
    private static boolean firstToMove;

    //Множество клеток, в которые можно поставить букву
    private static Set<Pair<Short, Short>> possibleMoves = new HashSet<>();

    public void fillField() throws IOException {
        final String word = getFirstWord();

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (i == 0)
                    field[i][j] = word.charAt(i);
                else field[i][j] = ' ';
            }
        }

        firstToMove = true;

        firstPlayerScore = 0;
        secondPlayerScore = 0;
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

    private String getFirstWord() throws IOException {
        String filePath = "src/resources/five_let_words.txt";
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        int numOfLines = getNumberOfLines(filePath);
        int random = (int)(( Math.random() * numOfLines + 1));
        int i = 1;
        String res;
        try(reader) {
            String string = reader.readLine();
            while (i != random) {
                string = reader.readLine();
                i++;
            }
            res = string;
        }
        return res;
    }

    private boolean isWord(String checking) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("src/resources/dictionary.txt"));
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
        BufferedWriter writer = new BufferedWriter(new FileWriter("src/resources/dictionary.txt", true));
        try (writer) {
            writer.write("\n" + word);
        }
        return isWord(word);
    }
}
