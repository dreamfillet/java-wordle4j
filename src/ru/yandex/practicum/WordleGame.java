package ru.yandex.practicum;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;


public class WordleGame {

    private String answer; //загаданное слово
    private int stepsMade; //количество сделанных попыток
    private int maxSteps = 6;
    private WordleDictionary dictionary; //ссылка на словарь
    private PrintWriter logger;
    private List<String> history = new ArrayList<>();//история ходов
    private boolean isWon = false;
    private boolean isGameOver = false;

    public WordleGame(WordleDictionary dictionary, PrintWriter logger) {
        this.dictionary = dictionary;
        this.logger = logger;
        String randomWord = dictionary.getRandomWord();
        if (randomWord == null) {
            throw new IllegalStateException("Словарь пуст! Нечего загадывать.");
        }
        this.answer = randomWord;
    }

    public WordleGame(WordleDictionary dictionary, PrintWriter logger, String secretWord) {
        this.dictionary = dictionary;
        this.logger = logger;
        this.answer = secretWord;
        this.maxSteps = 6;
        this.stepsMade = 0;
        this.history = new ArrayList<>();

        if (logger != null) {
            logger.println("Игра начата. Загаданное слово: " + answer);
        }
    }

    public boolean isWon() {
        return isWon;
    }

    public boolean isGameOver() {
        return isGameOver;
    }

    String checkWord(String guess) {
        if (!dictionary.containsWord(guess)) {
            throw new WordNotFoundInDictionary(guess);
        }
        boolean[] usedInAnswer = new boolean[5];
        if (guess == null || guess.length() != 5) {
            throw new IllegalArgumentException("Неверный ввод");
        }

        char[] answers = answer.toCharArray();
        char[] guesses = guess.toCharArray();
        char[] result = new char[5];

        //поиск точных совпадений
        for (int i = 0; i < 5; i++) {
            if (guesses[i] == answers[i]) {
                result[i] = '+';
                usedInAnswer[i] = true;
            } else {
                result[i] = '-';
            }
        }

        //поиск неточных совпадений
        for (int i = 0; i < 5; i++) {
            if (result[i] == '+') {
                continue;
            }


            // Ищем эту букву в загаданном слове
            for (int j = 0; j < 5; j++) {
                if (guesses[i] == answers[j] && i != j && !usedInAnswer[j]) {
                    result[i] = '^';
                    usedInAnswer[j] = true;
                    break;
                }

            }
        }
        String resultAnswer = new String(result);
        stepsMade++;
        history.add(guess);
        if (resultAnswer.equals("+++++")) {
            isWon = true;
            isGameOver = true;
        } else if (stepsMade >= maxSteps) {
            isGameOver = true;
        }
        return resultAnswer;
    }

    public String getAnswer() {
        return answer;
    }


    public String getHint() {
        return dictionary.getRandomWord();
    }

}


