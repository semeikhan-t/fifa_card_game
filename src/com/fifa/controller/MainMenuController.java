package com.fifa.controller;

import com.fifa.util.SceneManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MainMenuController {

    @FXML
    private void handlePlay() {
        SceneManager.loadScene("CountrySelection.fxml", "Select Country");
    }

    @FXML
    private void handleStats() {
        SceneManager.loadScene("Stats.fxml", "Statistics");
    }

    @FXML
    private void handleExit() {
        System.exit(0);
    }
}
