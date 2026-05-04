package com.fifa.controller;

import com.fifa.util.SceneManager;
import javafx.fxml.FXML;

public class ResultController {

    @FXML
    private void handlePlayAgain() {
        SceneManager.loadScene("CountrySelection.fxml", "Select Country");
    }

    @FXML
    private void handleMenu() {
        SceneManager.loadScene("MainMenu.fxml", "FIFA Card Game 2026 - Main Menu");
    }
}
