package com.fifa.controller;

import com.fifa.dao.TeamDAO;
import com.fifa.model.Team;
import com.fifa.service.SquadService;
import com.fifa.util.SceneManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.TilePane;
import java.util.List;

public class CountrySelectionController {

    @FXML
    private TilePane teamsContainer;

    private TeamDAO teamDAO = new TeamDAO();

    @FXML
    public void initialize() {
        List<Team> teams = teamDAO.findAll();
        for (Team team : teams) {
            Button btn = new Button(team.getName());
            btn.getStyleClass().add("menu-btn");
            btn.setOnAction(e -> handleSelectTeam(team));
            teamsContainer.getChildren().add(btn);
        }
    }

    private void handleSelectTeam(Team team) {
        SquadService.getInstance().setSelectedTeam(team);
        
        // Pick random opponent
        List<Team> allTeams = teamDAO.findAll();
        allTeams.removeIf(t -> t.getId() == team.getId());
        java.util.Collections.shuffle(allTeams);
        SquadService.getInstance().setOpponentTeam(allTeams.get(0));
        
        SceneManager.loadScene("Squad.fxml", "Squad Selection");
    }

    @FXML
    private void handleBack() {
        SceneManager.loadScene("MainMenu.fxml", "FIFA Card Game 2026 - Main Menu");
    }
}
