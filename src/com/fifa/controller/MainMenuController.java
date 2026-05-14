package com.fifa.controller;

import com.fifa.util.AudioManager;
import com.fifa.util.SceneManager;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.ScaleTransition;
import javafx.util.Duration;
import java.net.URL;

public class MainMenuController {
    @FXML
    private MediaView backgroundMediaView;
    
    @FXML
    private ImageView fallbackImageView;

    @FXML
    private VBox menuItemsBox;

    @FXML
    private Button replayButton;

    private static boolean hasPlayedVideo = false;
    private MediaPlayer mediaPlayer;
    private ScaleTransition pulse;

    @FXML
    public void initialize() {
        System.out.println("MainMenuController initialized!");
        AudioManager.stopAll(); 
        
        
        boolean videoLoaded = false;
        try {
            URL videoUrl = getClass().getResource("/video/bg_video.mp4");
            if (videoUrl != null) {
                Media media = new Media(videoUrl.toExternalForm());
                mediaPlayer = new MediaPlayer(media);
                
                mediaPlayer.setOnReady(() -> {
                    System.out.println("Video is ready!");
                    fallbackImageView.setVisible(false);
                    
                    if (hasPlayedVideo) {
                        
                        mediaPlayer.seek(media.getDuration());
                        menuItemsBox.setOpacity(1.0);
                        replayButton.setVisible(true);
                        startPulsingEffect();
                    } else {
                        
                        menuItemsBox.setOpacity(0.4);
                        replayButton.setVisible(false);
                        mediaPlayer.play();
                        hasPlayedVideo = true;
                    }
                });

                mediaPlayer.setOnEndOfMedia(() -> {
                    System.out.println("Video reached the end. Starting pulse effect!");
                    replayButton.setVisible(true);
                    startPulsingEffect();
                });

                mediaPlayer.setOnError(() -> {
                    System.err.println("MediaPlayer error: " + mediaPlayer.getError().getMessage());
                    fallbackImageView.setVisible(true);
                    replayButton.setVisible(false);
                });

                mediaPlayer.setCycleCount(1);
                mediaPlayer.setMute(false);
                mediaPlayer.setVolume(0.4);
                backgroundMediaView.setMediaPlayer(mediaPlayer);
                videoLoaded = true;
            } else {
                System.err.println("Video file not found in resources!");
            }
        } catch (Throwable t) {
            System.err.println("Could not initialize video system: " + t.getMessage());
        }

        if (!videoLoaded) {
            fallbackImageView.setVisible(true);
            backgroundMediaView.setVisible(false);
            menuItemsBox.setOpacity(1.0);
            replayButton.setVisible(true);
            startPulsingEffect();
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
    private void onReplayClicked() {
        System.out.println("REPLAY button clicked!");
        if (mediaPlayer != null) {
            if (pulse != null) pulse.stop();
            menuItemsBox.setOpacity(0.4);
            replayButton.setVisible(false);
            mediaPlayer.seek(Duration.ZERO);
            mediaPlayer.play();
        }
    }

    @FXML
    private void onExitClicked() {
        stopVideo();
        Platform.exit();
    }

    private void stopVideo() {
        if (pulse != null) pulse.stop();
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.dispose();
        }
        AudioManager.stopAll();
    }

    private void startPulsingEffect() {
        if (pulse != null) pulse.stop();

        
        FadeTransition fadeIn = new FadeTransition(Duration.seconds(1.5), menuItemsBox);
        fadeIn.setFromValue(menuItemsBox.getOpacity());
        fadeIn.setToValue(1.0);
        
        
        pulse = new ScaleTransition(Duration.seconds(1.2), menuItemsBox);
        pulse.setToX(1.05);
        pulse.setToY(1.05);
        pulse.setCycleCount(Animation.INDEFINITE);
        pulse.setAutoReverse(true);
        
        fadeIn.setOnFinished(e -> pulse.play());
        fadeIn.play();
    }
}