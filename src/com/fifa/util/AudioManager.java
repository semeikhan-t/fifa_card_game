package com.fifa.util;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class AudioManager {
    private static Map<String, MediaPlayer> players = new HashMap<>();
    private static MediaPlayer backgroundMusic;

    public static void playMusic(String fileName, double volume) {
        playMusic(fileName, volume, 0);
    }

    public static void playMusic(String fileName, double volume, double startSeconds) {
        System.out.println("Trying to play music: " + fileName + " from " + startSeconds + "s");
        if (backgroundMusic != null) {
            backgroundMusic.stop();
        }
        
        MediaPlayer player = getPlayer(fileName);
        if (player != null) {
            player.setVolume(volume);
            player.setCycleCount(MediaPlayer.INDEFINITE);
            player.seek(Duration.seconds(startSeconds));
            player.play();
            backgroundMusic = player;
            System.out.println("Music playing: " + fileName);
        }
    }

    public static void playSound(String fileName, double volume) {
        System.out.println("Trying to play sound: " + fileName);
        MediaPlayer player = getPlayer(fileName);
        if (player != null) {
            player.setVolume(volume);
            player.setCycleCount(1);
            player.stop(); 
            player.seek(Duration.ZERO);
            player.play();
            System.out.println("Sound playing: " + fileName);
        }
    }

    public static void stopAll() {
        if (backgroundMusic != null) {
            backgroundMusic.stop();
        }
        players.values().forEach(MediaPlayer::stop);
    }

    private static MediaPlayer getPlayer(String fileName) {
        if (players.containsKey(fileName)) {
            return players.get(fileName);
        }

        try {
            URL resource = AudioManager.class.getResource("/sounds/" + fileName);
            if (resource == null) {
                System.err.println("Sound file not found: " + fileName);
                return null;
            }
            Media media = new Media(resource.toExternalForm());
            MediaPlayer player = new MediaPlayer(media);
            
            
            player.setOnError(() -> {
                System.err.println("MediaPlayer error for " + fileName + ": " + player.getError().getMessage());
            });

            players.put(fileName, player);
            return player;
        } catch (Exception e) {
            System.err.println("Error loading sound: " + fileName);
            e.printStackTrace();
            return null;
        }
    }
}