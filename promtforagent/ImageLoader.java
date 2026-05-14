package com.fifa.util;

import javafx.scene.image.Image;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class ImageLoader {

    private static final Map<String, Image> cache = new HashMap<>();

    private static final String PLAYERS_PATH = "/images/players/";
    private static final String FLAGS_PATH   = "/images/flags/";
    private static final String PLACEHOLDER  = "/images/placeholder_player.png";

    
    public static Image loadPlayer(String filename) {
        return loadFromResources(PLAYERS_PATH + filename, PLACEHOLDER);
    }

    
    public static Image loadFlag(String countryName) {
        return loadFromResources(FLAGS_PATH + countryName + ".png", null);
    }

    private static Image loadFromResources(String resourcePath, String fallback) {
        if (cache.containsKey(resourcePath)) {
            return cache.get(resourcePath);
        }

        InputStream stream = ImageLoader.class.getResourceAsStream(resourcePath);

        if (stream != null) {
            Image img = new Image(stream);
            cache.put(resourcePath, img);
            return img;
        }

        if (fallback != null) {
            InputStream fallbackStream = ImageLoader.class.getResourceAsStream(fallback);
            if (fallbackStream != null) {
                return new Image(fallbackStream);
            }
        }

        System.err.println("️  Изображение не найдено: " + resourcePath);
        return null;
    }

    public static void clearCache() {
        cache.clear();
    }
}