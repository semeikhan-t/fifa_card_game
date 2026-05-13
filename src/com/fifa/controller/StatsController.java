package com.fifa.controller;

import com.fifa.dao.MatchDAO;
import com.fifa.model.MatchResult;
import com.fifa.util.SceneManager;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class StatsController {
    @FXML private TableView<MatchResult> statsTable;
    @FXML private TableColumn<MatchResult, String> dateCol;
    @FXML private TableColumn<MatchResult, String> outcomeCol;
    @FXML private TableColumn<MatchResult, String> homeCol;
    @FXML private TableColumn<MatchResult, String> scoreCol;
    @FXML private TableColumn<MatchResult, String> awayCol;


    @FXML private Label totalMatchesLabel;
    @FXML private Label winsLabel;
    @FXML private Label lossesLabel;
    @FXML private Label winRateLabel;

    private MatchDAO matchDAO = new MatchDAO();
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    @FXML
    public void initialize() {
        dateCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getPlayedAt().format(formatter)));
        homeCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getHomeName()));
        scoreCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getHomeScore() + " - " + data.getValue().getAwayScore()));
        awayCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getAwayName()));

        outcomeCol.setCellValueFactory(data -> {
            int h = data.getValue().getHomeScore();
            int a = data.getValue().getAwayScore();
            if (h > a) return new SimpleStringProperty("WIN");
            if (h < a) return new SimpleStringProperty("LOSS");
            return new SimpleStringProperty("DRAW");
        });

        outcomeCol.setCellFactory(column -> new TableCell<MatchResult, String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    getStyleClass().removeAll("outcome-win", "outcome-loss", "outcome-draw");
                } else {
                    setText(item);
                    getStyleClass().removeAll("outcome-win", "outcome-loss", "outcome-draw");
                    if (item.equals("WIN")) getStyleClass().add("outcome-win");
                    else if (item.equals("LOSS")) getStyleClass().add("outcome-loss");
                    else getStyleClass().add("outcome-draw");
                }
            }
        });

        List<MatchResult> matches = matchDAO.findAll();
        statsTable.setItems(FXCollections.observableArrayList(matches));
        
        calculateStats(matches);
    }


    private void calculateStats(List<MatchResult> matches) {
        int total = matches.size();
        int wins = 0;
        int losses = 0;

        for (MatchResult match : matches) {
            if (match.getHomeScore() > match.getAwayScore()) {
                wins++;
            } else if (match.getHomeScore() < match.getAwayScore()) {
                losses++;
            }
        }

        totalMatchesLabel.setText(String.valueOf(total));
        winsLabel.setText(String.valueOf(wins));
        lossesLabel.setText(String.valueOf(losses));
        
        if (total > 0) {
            double rate = (double) wins / total * 100;
            winRateLabel.setText(String.format("%.1f%%", rate));
        } else {
            winRateLabel.setText("0%");
        }
    }


    @FXML
    private void onBackClicked() {
        SceneManager.loadScene("MainMenu.fxml", "FIFA Card Game 2026");
    }
}
