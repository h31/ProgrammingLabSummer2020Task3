package ru.nikiens.fillword.model.util;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Path;
import java.util.Random;

public class RandomizedReader {
    private Path source;
    private Random random = new Random();

    public RandomizedReader(Path source) {
        this.source = source;
    }

    public String readLine() throws IOException {
        try (RandomAccessFile raf = new RandomAccessFile(source.toFile(), "r")) {
            long randomPosition = random.nextInt((int) raf.length());

            raf.seek(randomPosition);
            raf.readLine();

            return raf.readLine();
        }
    }

    public Path getSource() {
        return source;
    }

    public void setSource(Path source) {
        this.source = source;
    }
}
