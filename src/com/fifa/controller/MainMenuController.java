package com.fifa.controller;

import com.fifa.util.AudioManager;
import com.fifa.util.SceneManager;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.image.ImageView;
import java.net.URL;

public class MainMenuController {
    @FXML
    private MediaView backgroundMediaView;
    
    @FXML
    private ImageView fallbackImageView;

    private MediaPlayer mediaPlayer;

    @FXML
    public void initialize() {
        System.out.println("MainMenuController initialized!");
        AudioManager.stopAll(); // Ensure no other music plays on main menu
        // AudioManager.playMusic("main.mp3", 0.2); // Removed to use video sound

        // Setup background video
        boolean videoLoaded = false;
        try {
            URL videoUrl = getClass().getResource("/video/bg_video.mp4");
            if (videoUrl != null) {
                Media media = new Media(videoUrl.toExternalForm());
                mediaPlayer = new MediaPlayer(media);
                
                // If we reach here, player is created. Now listen for actual readiness.
                mediaPlayer.setOnReady(() -> {
                    System.out.println("Video is ready to play!");
                    fallbackImageView.setVisible(false);
                });

                mediaPlayer.setOnError(() -> {
                    System.err.println("MediaPlayer error: " + mediaPlayer.getError().getMessage());
                    fallbackImageView.setVisible(true);
                });

                mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
                mediaPlayer.setMute(false); // Enable sound from video
                mediaPlayer.setVolume(0.4); // Adjust volume as needed
                backgroundMediaView.setMediaPlayer(mediaPlayer);
                mediaPlayer.play();
                videoLoaded = true;
            } else {
                System.err.println("Video file not found in resources!");
            }
        } catch (Throwable t) {
            // Use Throwable to catch even Error (like UnsatisfiedLinkError)
            System.err.println("Could not initialize video system: " + t.getMessage());
        }

        if (!videoLoaded) {
            fallbackImageView.setVisible(true);
            backgroundMediaView.setVisible(false);
        }
    }

    @FXML
    private void onPlayClicked() {
        System.out.println("PLAY button clicked!");
        stopVideo();
        SceneManager.loadScene("CountrySelection.fxml", "Select Nation");
    }

    @FXML
    private void onStatsClicked() {
        System.out.println("STATS button clicked!");
        stopVideo();
        SceneManager.loadScene("Stats.fxml", "Match Stats");
    }

    @FXML
    private void onExitClicked() {
        stopVideo();
        Platform.exit();
    }

    private void stopVideo() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.dispose(); // Release resources
        }
        AudioManager.stopAll(); // Stop any other playing music
    }
}
