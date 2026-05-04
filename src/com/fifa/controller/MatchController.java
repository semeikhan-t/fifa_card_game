package com.fifa.controller;

import com.fifa.dao.PlayerDAO;
import com.fifa.model.MatchEvent;
import com.fifa.model.MatchResult;
import com.fifa.model.Player;
import com.fifa.model.Team;
import com.fifa.service.MatchService;
import com.fifa.service.SquadService;
import com.fifa.util.SceneManager;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.util.Duration;

import java.util.List;

public class MatchController {

    @FXML private Label scoreLabel;
    @FXML private Label teamsLabel;
    @FXML private Label timeLabel;
    @FXML private ListView<String> eventsListView;
    @FXML private Button finishBtn;

    private MatchService matchService = new MatchService();
    private PlayerDAO playerDAO = new PlayerDAO();

    @FXML
    public void initialize() {
        finishBtn.setVisible(false);
        SquadService ss = SquadService.getInstance();
        Team home = ss.getSelectedTeam();
        Team away = ss.getOpponentTeam();
        List<Player> homeSquad = ss.getStartingEleven();
        List<Player> awaySquad = playerDAO.findByCountryId(away.getId());

        teamsLabel.setText(home.getName() + " vs " + away.getName());
        scoreLabel.setText("0 - 0");
        timeLabel.setText("0'");

        MatchResult result = matchService.simulate(home, homeSquad, away, awaySquad);
        List<MatchEvent> events = matchService.generateEvents(result, home, homeSquad, away, awaySquad);

        playAnimation(result, events);
    }

    private void playAnimation(MatchResult result, List<MatchEvent> events) {
        Timeline timeline = new Timeline();
        int currentHomeScore = 0;
        int currentAwayScore = 0;

        for (int i = 0; i <= 90; i++) {
            final int min = i;
            // 90 mins in max 40 seconds -> each min is roughly 0.4 seconds
            KeyFrame kf = new KeyFrame(Duration.seconds(min * 0.4), e -> {
                timeLabel.setText(min + "'");
                for (MatchEvent ev : events) {
                    if (ev.getMinute() == min) {
                        String msg = min + "' GOAL! " + ev.getPlayer().getName() + " (" + ev.getTeam().getName() + ")";
                        eventsListView.getItems().add(msg);
                    }
                }
                
                // Calculate current score
                int h = 0, a = 0;
                for (MatchEvent ev : events) {
                    if (ev.getMinute() <= min) {
                        if (ev.getTeam().getId() == result.getHomeId()) h++;
                        else a++;
                    }
                }
                scoreLabel.setText(h + " - " + a);

                if (min == 90) {
                    timeLabel.setText("FT");
                    finishBtn.setVisible(true);
                }
            });
            timeline.getKeyFrames().add(kf);
        }
        timeline.play();
    }

    @FXML
    private void handleFinish() {
        SceneManager.loadScene("Result.fxml", "Match Result");
    }
}
