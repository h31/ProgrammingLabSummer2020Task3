package project;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Controller {

    public void launch() {

    }

    private boolean isWord(String checking) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("src/resources/dictionary.txt"));
        String string = reader.readLine();
        while (string != null) {
            if (string.equals(checking)) return true;
            string = reader.readLine();
        }
        return false;
    }

    public boolean addWordToDict(String word) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("src/resources/dictionary.txt"));
        writer.write(word + "\n");
        return isWord(word);
    }
}
