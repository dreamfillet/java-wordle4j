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
        dictionary.addWord("ропот");

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
    void testSimpleWrongWord1() {
        String feedback = game.checkWord("банан");

        assertEquals("-----", feedback, "Ожидание при неправильном слове");


    }

    @Test
    void testSimpleWrongWord2() {
        String feedback = game.checkWord("ропот");
        assertEquals("^+++^", feedback, "Ожидание при неправильном слове");
    }

    @Test
    void testHint() {
        game.checkWord("банан");
        String hint = game.getHint();

        assertNotNull(hint, "Подсказка не должна быть null, если словарь не пуст");

        assertEquals(5, hint.length(), "Подсказка должна состоять из 5 букв");
        assertTrue(hint.matches("^[а-я]{5}$"), "Подсказка должна содержать только русские буквы");

        System.out.println("Тест пройден!");
    }
}