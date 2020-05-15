package project;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Controller {

    public int getNumberOfLines(String filePath) {
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

    public String getFirstWord() throws IOException {
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
