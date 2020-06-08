package ru.nikiens.fillword.model.util;

import ru.nikiens.fillword.model.BoardSize;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SourceVerifier {
    private Path source;

    public SourceVerifier(Path source) {
        this.source = source;
    }

    public void verify(BoardSize boardSize) throws IllegalSourceFormatException, IOException {
        Map<Integer, Integer> hashes = new HashMap<> ();
        Map<Integer, Integer> duplicates = new HashMap<>();

        Pattern pattern = Pattern.compile("(?i:[a-z]+)");
        Matcher matcher;
        Integer prevHash;
        String line;

        int hash;
        int count = 0;

        try (BufferedReader br = Files.newBufferedReader(source)) {
            br.readLine();

            while ((line = br.readLine()) != null) {
                matcher = pattern.matcher(line);
                hash = line.hashCode();
                prevHash = hashes.get(hash);

                if (prevHash != null) {
                    duplicates.put(count, prevHash);
                } else {
                    hashes.put(hash, count);
                }

                if (!matcher.matches() || line.length() > boardSize.value()) {
                    throw new IllegalSourceFormatException("Incorrect word format" + line);
                }
                ++count;
            }
        }

        if (!duplicates.isEmpty()) {
            throw new IllegalSourceFormatException("Repeated words are not allowed");
        }

        if (count < boardSize.value() / 2) {
            throw new IllegalSourceFormatException("Too few letters");
        }
    }
}
