package com.fifa.util;

import javafx.scene.image.Image;
import java.io.InputStream;
import java.nio.file.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Загрузчик изображений для FIFA Card Game 2026
 * 
 * Сначала ищет файл локально (скачанный заранее),
 * если не нашёл — показывает placeholder.
 */
public class ImageLoader {

    // Кэш чтобы не загружать одно фото несколько раз
    private static final Map<String, Image> cache = new HashMap<>();

    private static final String PLAYERS_PATH = "/images/players/";
    private static final String FLAGS_PATH   = "/images/flags/";
    private static final String PLACEHOLDER  = "/images/placeholder_player.png";

    /**
     * Загрузить фото игрока по имени файла
     * Пример: loadPlayer("argentina_lionel_messi_154.png")
     */
    public static Image loadPlayer(String filename) {
        return loadFromResources(PLAYERS_PATH + filename, PLACEHOLDER);
    }

    /**
     * Загрузить флаг страны
     * Пример: loadFlag("argentina")
     */
    public static Image loadFlag(String countryName) {
        return loadFromResources(FLAGS_PATH + countryName + ".png", null);
    }

    /**
     * Загрузить любое изображение из resources
     */
    private static Image loadFromResources(String resourcePath, String fallback) {
        // Проверяем кэш
        if (cache.containsKey(resourcePath)) {
            return cache.get(resourcePath);
        }

        // Пробуем загрузить из resources
        InputStream stream = ImageLoader.class.getResourceAsStream(resourcePath);

        if (stream != null) {
            Image img = new Image(stream);
            cache.put(resourcePath, img);
            return img;
        }

        // Если не нашли — возвращаем placeholder
        if (fallback != null) {
            InputStream fallbackStream = ImageLoader.class.getResourceAsStream(fallback);
            if (fallbackStream != null) {
                return new Image(fallbackStream);
            }
        }

        // Совсем ничего нет — пустое изображение
        System.err.println("⚠️  Изображение не найдено: " + resourcePath);
        return null;
    }

    /**
     * Очистить кэш (например при смене темы)
     */
    public static void clearCache() {
        cache.clear();
    }
}
