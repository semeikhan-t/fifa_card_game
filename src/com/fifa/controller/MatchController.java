package com.fifa.controller;

import com.fifa.dao.MatchDAO;
import com.fifa.model.MatchEvent;
import com.fifa.model.MatchResult;
import com.fifa.model.Team;
import com.fifa.service.MatchService;
import com.fifa.util.SceneManager;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import java.util.List;

public class MatchController {
    @FXML private Label homeTeamLabel;
    @FXML private Label awayTeamLabel;
    @FXML private Label homeScoreLabel;
    @FXML private Label awayScoreLabel;
    @FXML private Label timerLabel;
    @FXML private VBox eventLog;
    @FXML private Button resultButton;

    private MatchService matchService = new MatchService();
    private MatchDAO matchDAO = new MatchDAO();
    private MatchResult finalResult;
    private int currentMinute = 0;
    private int homeScore = 0;
    private int awayScore = 0;

    @FXML
    public void initialize() {
        Team home = SceneManager.getUserTeam();
        Team away = SceneManager.getOpponentTeam();
        
        if (home == null || away == null) return;

        homeTeamLabel.setText(home.getName());
        awayTeamLabel.setText(away.getName());

        finalResult = matchService.simulateMatch(home, away);
        
        startSimulation();
    }

    private void startSimulation() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(200), e -> {
            currentMinute++;
            timerLabel.setText(currentMinute + "''");
            
            checkEvents(currentMinute);
            
            if (currentMinute >= 90) {
                onMatchEnd();
            }
        }));
        timeline.setCycleCount(90);
        timeline.play();
    }

    private void checkEvents(int minute) {
        for (MatchEvent event : finalResult.getEvents()) {
            if (event.getMinute() == minute) {
                addEventToLog(event);
                if (event.getType().equals("GOAL")) {
                    if (event.isHomeTeam()) {
                        homeScore++;
                        homeScoreLabel.setText(String.valueOf(homeScore));
                    } else {
                        awayScore++;
                        awayScoreLabel.setText(String.valueOf(awayScore));
                    }
                }
            }
        }
    }

    private void addEventToLog(MatchEvent event) {
        Label logLabel = new Label(event.toString());
        logLabel.setStyle("-fx-text-fill: #d4af37; -fx-font-weight: bold;");
        eventLog.getChildren().add(0, logLabel); // Добавляем сверху
        if (eventLog.getChildren().size() > 5) {
            eventLog.getChildren().remove(5);
        }
    }

    private void onMatchEnd() {
        Label endLabel = new Label("ФИНАЛЬНЫЙ СВИСТОК!");
        endLabel.setStyle("-fx-text-fill: #ff0000; -fx-font-weight: bold;");
        eventLog.getChildren().add(0, endLabel);
        resultButton.setVisible(true);
        
        // Сохраняем в БД
        matchDAO.save(finalResult);
    }

    @FXML
    private void onResultClicked() {
        SceneManager.setLastResult(finalResult);
        SceneManager.loadScene("Result.fxml", "Итоги матча");
    }
}
