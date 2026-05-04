package com.fifa.controller;

import com.fifa.dao.MatchDAO;
import com.fifa.dao.TeamDAO;
import com.fifa.model.MatchResult;
import com.fifa.model.Team;
import com.fifa.util.SceneManager;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.util.List;

public class StatsController {

    @FXML private TableView<MatchResult> statsTable;
    @FXML private TableColumn<MatchResult, String> dateCol;
    @FXML private TableColumn<MatchResult, String> matchCol;
    @FXML private TableColumn<MatchResult, String> scoreCol;

    private MatchDAO matchDAO = new MatchDAO();
    private TeamDAO teamDAO = new TeamDAO();

    @FXML
    public void initialize() {
        dateCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getPlayedAt().toString()));
        matchCol.setCellValueFactory(data -> {
            Team home = teamDAO.findById(data.getValue().getHomeId());
            Team away = teamDAO.findById(data.getValue().getAwayId());
            String homeName = home != null ? home.getName() : "Unknown";
            String awayName = away != null ? away.getName() : "Unknown";
            return new SimpleStringProperty(homeName + " vs " + awayName);
        });
        scoreCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getHomeScore() + " - " + data.getValue().getAwayScore()));

        List<MatchResult> matches = matchDAO.findAll();
        statsTable.getItems().addAll(matches);
    }

    @FXML
    private void handleBack() {
        SceneManager.loadScene("MainMenu.fxml", "FIFA Card Game 2026 - Main Menu");
    }
}
