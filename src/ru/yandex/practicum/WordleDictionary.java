package ru.yandex.practicum;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WordleDictionary {

    private final List<String> words = new ArrayList<>();

    private final PrintWriter logger;

    WordleDictionary(PrintWriter logger) {
        this.logger = logger;
    }

    public void addWord(String word) {
        if (!words.contains(word))
            words.add(word);
    }

    public boolean containsWord(String word) {
        return words.contains(word);
    }

    public String getRandomWord() {
        if (words.isEmpty()) {
            return null;
        }

        Random random = new Random();
        int randomIndex = random.nextInt(words.size());
        return words.get(randomIndex);
    }

    public int getSize() {
        return words.size();
    }

    public static boolean isRussianWord(String word) {
        if (word == null || word.isEmpty()) {
            return false;
        }

        for (char c : word.toCharArray()) {
            if (!((c >= 'А' && c <= 'Я') ||
                    (c >= 'а' && c <= 'я') ||
                    c == 'Ё' || c == 'ё')) {
                return false;
            }
        }
        return true;
    }


}
