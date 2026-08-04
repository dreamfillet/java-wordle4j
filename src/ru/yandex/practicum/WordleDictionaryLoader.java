package ru.yandex.practicum;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class WordleDictionaryLoader {

    public static WordleDictionary load(String path, PrintWriter logger) throws IOException {
        WordleDictionary dict = new WordleDictionary(logger);

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(path), "UTF-8"))) {

            String line;

            while ((line = reader.readLine()) != null) {

                String word = line.trim().toLowerCase().replace('ё', 'е');

                if (word.matches("^[а-я]{5}$")) {
                    dict.addWord(word);
                }

            }
        }

        return dict;
    }
}
