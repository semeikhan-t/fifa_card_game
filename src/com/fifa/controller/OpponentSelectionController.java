package com.fifa.controller;

import com.fifa.dao.TeamDAO;
import com.fifa.model.Team;
import com.fifa.service.SquadService;
import com.fifa.util.SceneManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import java.util.List;

public class OpponentSelectionController {
    @FXML private TilePane countriesPane;
    @FXML private Button nextButton;

    private TeamDAO teamDAO = new TeamDAO();
    private SquadService squadService = new SquadService();
    private Team selectedOpponent;

    @FXML
    public void initialize() {
        List<Team> teams = teamDAO.findAll();
        Team userTeam = SceneManager.getUserTeam();
        
        for (Team team : teams) {
            if (userTeam != null && team.getId() == userTeam.getId()) continue;
            
            VBox card = createCountryCard(team);
            countriesPane.getChildren().add(card);
        }
    }

    private VBox createCountryCard(Team team) {
        VBox card = new VBox(10);
        card.getStyleClass().add("country-card");
        card.setAlignment(javafx.geometry.Pos.CENTER);
        card.setPrefSize(150, 100);
        
        Label nameLabel = new Label(team.getName());
        nameLabel.setStyle("-fx-text-fill: white; -fx-font-weight: bold;");
        card.getChildren().add(nameLabel);
        
        card.setOnMouseClicked(e -> {
            selectedOpponent = team;
            nextButton.setDisable(false);
            countriesPane.getChildren().forEach(n -> n.setStyle("-fx-border-color: transparent;"));
            card.setStyle("-fx-border-color: #d4af37; -fx-border-width: 3; -fx-border-radius: 5;");
        });
        
        return card;
    }

    @FXML
    private void onNextClicked() {
        squadService.loadSquad(selectedOpponent); // Загружаем игроков соперника для симуляции
        SceneManager.setOpponentTeam(selectedOpponent);
        SceneManager.loadScene("Match.fxml", "МАТЧ: " + SceneManager.getUserTeam().getName() + " vs " + selectedOpponent.getName());
    }

    @FXML
    private void onBackClicked() {
        SceneManager.loadScene("Squad.fxml", "Сборка состава");
    }
}
