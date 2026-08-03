package ru.yandex.practicum;

public class WordNotFoundInDictionary extends RuntimeException {
    public WordNotFoundInDictionary(String word) {
        super("Слово '" + word + "' отсутствует в словаре. Попробуйте другое.");
    }
}