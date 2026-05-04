package com.fifa.controller;

import com.fifa.model.Player;
import com.fifa.service.SquadService;
import com.fifa.util.SceneManager;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;

public class SquadController {

    @FXML
    private Label teamLabel;

    @FXML
    private Label opponentLabel;

    @FXML
    private ListView<String> startersListView;

    @FXML
    private ListView<String> subsListView;

    @FXML
    public void initialize() {
        SquadService service = SquadService.getInstance();
        teamLabel.setText("Your Team: " + service.getSelectedTeam().getName());
        opponentLabel.setText("Opponent: " + service.getOpponentTeam().getName());

        for (Player p : service.getSquad()) {
            if (p.isStarter()) {
                startersListView.getItems().add(p.toString());
            } else {
                subsListView.getItems().add(p.toString());
            }
        }
    }

    @FXML
    private void handlePlayMatch() {
        SceneManager.loadScene("Match.fxml", "Match");
    }

    @FXML
    private void handleBack() {
        SceneManager.loadScene("CountrySelection.fxml", "Select Country");
    }
}
