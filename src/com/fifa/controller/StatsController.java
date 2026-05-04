package com.fifa.controller;

import com.fifa.dao.MatchDAO;
import com.fifa.model.MatchResult;
import com.fifa.util.SceneManager;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import java.time.format.DateTimeFormatter;

public class StatsController {
    @FXML private TableView<MatchResult> statsTable;
    @FXML private TableColumn<MatchResult, String> dateCol;
    @FXML private TableColumn<MatchResult, String> homeCol;
    @FXML private TableColumn<MatchResult, String> scoreCol;
    @FXML private TableColumn<MatchResult, String> awayCol;

    private MatchDAO matchDAO = new MatchDAO();
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    @FXML
    public void initialize() {
        dateCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getPlayedAt().format(formatter)));
        homeCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getHomeName()));
        scoreCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getHomeScore() + " - " + data.getValue().getAwayScore()));
        awayCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getAwayName()));

        statsTable.setItems(FXCollections.observableArrayList(matchDAO.findAll()));
    }

    @FXML
    private void onBackClicked() {
        SceneManager.loadScene("MainMenu.fxml", "FIFA Card Game 2026");
    }
}
