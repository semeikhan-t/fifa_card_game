package com.fifa.controller;

import com.fifa.model.MatchEvent;
import com.fifa.model.MatchResult;
import com.fifa.util.SceneManager;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class ResultController {
    @FXML private Label resultLabel;
    @FXML private ListView<MatchEvent> eventsList;

    @FXML
    public void initialize() {
        MatchResult result = SceneManager.getLastResult();
        if (result == null) return;

        resultLabel.setText(result.getHomeScore() + " - " + result.getAwayScore());
        eventsList.setItems(FXCollections.observableArrayList(result.getEvents()));
    }

    @FXML
    private void onMenuClicked() {
        SceneManager.loadScene("MainMenu.fxml", "FIFA Card Game 2026");
    }

    @FXML
    private void onStatsClicked() {
        SceneManager.loadScene("Stats.fxml", "Статистика матчей");
    }
}