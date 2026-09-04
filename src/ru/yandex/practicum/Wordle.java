package ru.yandex.practicum;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Wordle {

    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);
        PrintWriter logger = null;
        try {

            logger = new PrintWriter(new FileWriter("application.log", true));
            System.out.println("Игра запущена. Логи пишутся в application.log");

            WordleDictionary dictionary = WordleDictionaryLoader.load("words_ru.txt", logger);

            if (dictionary.getSize() == 0) {
                System.out.println("Ошибка: Словарь пуст");
                return;
            }

            WordleGame game = new WordleGame(dictionary, logger);
            System.out.println("Игра началась! У вас есть 6 попыток");
            hello();

            while (!game.isGameOver()) {
                System.out.println("Введите слово:");
                String word = scanner.nextLine();
                String correctedWord = word.trim().toLowerCase().replace('ё', 'е');

                if (correctedWord.isEmpty()) {
                    String hint = game.getHint();
                    if (hint != null) {
                        System.out.println("Попробуйте слово '" + hint + "'");
                    } else {
                        System.out.println("Словарь пуст, не могу дать подсказку.");
                    }

                    continue;
                }
                if (!WordleDictionary.isRussianWord(correctedWord)) {
                    System.out.println("Такого слова не существует, введите корректное русское слово из 5 букв");
                    System.out.println("Осталось " + game.getStepsLeft() + " попыток");
                    continue;
                }
                try {
                    String feedback = game.checkWord(correctedWord);
                    System.out.println(feedback);
                    if (game.isWon()) {
                        System.out.println("Вы угадали слово!");
                        break;
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println("Ошибка: " + e.getMessage());
                } catch (WordNotFoundInDictionary e) {
                    System.out.println("Ошибка: " + e.getMessage());
                    System.out.println("Осталось " + game.getStepsLeft() + " попыток");
                    System.out.println("Пожалуйста, введите существующее русское слово из 5 букв.\n");
                }


                if (!game.isWon() && game.isGameOver()) {
                    System.out.println("Вы проиграли.");
                    System.out.println("Загаданное слово было: " + game.getAnswer());
                    logger.println("Игрок проиграл. Загаданное слово: " + game.getAnswer());
                }


            }
        } catch (IOException e) {
            System.err.println("Не удалось создать лог-файл!");
            e.printStackTrace();
        } finally {
            scanner.close();
            if (logger != null) logger.close();
        }


    }

    public static void hello() {
        System.out.println("Правила подсказок:");
        System.out.println("Вводится слово ровно из 5 букв.");
        System.out.println("- '+' : буква есть и стоит на своём месте");
        System.out.println("- '^' : буква есть, но стоит не там");
        System.out.println("- '-' : такой буквы нет в слове");
        System.out.println("----------------------------------------");
    }
}




