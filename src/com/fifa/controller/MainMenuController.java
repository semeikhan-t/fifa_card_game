package com.fifa.controller;

import com.fifa.util.SceneManager;
import javafx.application.Platform;
import javafx.fxml.FXML;

public class MainMenuController {
    @FXML
    public void initialize() {
        System.out.println("MainMenuController initialized!");
    }

    @FXML
    private void onPlayClicked() {
        System.out.println("PLAY button clicked!");
        SceneManager.loadScene("CountrySelection.fxml", "Выбор сборной");
    }

    @FXML
    private void onStatsClicked() {
        System.out.println("STATS button clicked!");
        SceneManager.loadScene("Stats.fxml", "Статистика матчей");
    }

    @FXML
    private void onExitClicked() {
        Platform.exit();
    }
}
