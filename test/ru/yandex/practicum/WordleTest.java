package ru.yandex.practicum;

/*
С пониманием написания тестов в данном задании есть определенные трудности.
Написал несколько простых тестов.
Просьба написать, какие тесты нужны для обязательного покрытия.
*/

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.PrintWriter;
import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.*;

class WordleTest {

    private WordleDictionary dictionary;
    private PrintWriter logger;
    private WordleGame game;

    @BeforeEach
    void setUp() {
        dictionary = new WordleDictionary(null);
        dictionary.addWord("топор");
        dictionary.addWord("банан");

        StringWriter writer = new StringWriter();
        logger = new PrintWriter(writer);

        game = new WordleGame(dictionary, logger, "топор");
    }

   @Test
    void testSimpleWin() {

        String feedback = game.checkWord("топор");

        assertEquals("+++++", feedback, "Ожидаем пять плюсов при правильном слове");

        assertTrue(game.isWon(), "должен быть true");

        assertTrue(game.isGameOver(), "Игра должна завершиться");
    }

      @Test
    void testSimpleWrongWord() {
        String feedback = game.checkWord("банан");

        assertEquals("-----", feedback, "Ожидание при неправильном слове");


    }
}