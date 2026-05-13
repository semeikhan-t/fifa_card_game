package com.fifa.controller;

import com.fifa.util.AudioManager;
import com.fifa.util.SceneManager;
import javafx.application.Platform;
import javafx.fxml.FXML;

public class MainMenuController {
    @FXML
    public void initialize() {
        System.out.println("MainMenuController initialized!");
        AudioManager.playMusic("main.mp3", 0.2);
    }

    @FXML
    private void onPlayClicked() {
        System.out.println("PLAY button clicked!");
        SceneManager.loadScene("CountrySelection.fxml", "Select Nation");
    }

    @FXML
    private void onStatsClicked() {
        System.out.println("STATS button clicked!");
        SceneManager.loadScene("Stats.fxml", "Match Stats");
    }

    @FXML
    private void onExitClicked() {
        Platform.exit();
    }
}
