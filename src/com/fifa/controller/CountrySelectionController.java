package com.fifa.controller;

import com.fifa.dao.TeamDAO;
import com.fifa.model.Team;
import com.fifa.util.SceneManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import com.fifa.util.ImageLoader;
import java.util.List;

public class CountrySelectionController {
    @FXML private TilePane countriesPane;
    @FXML private Button nextButton;

    private TeamDAO teamDAO = new TeamDAO();
    private Team selectedTeam;

    @FXML
    public void initialize() {
        List<Team> teams = teamDAO.findAll();
        for (Team team : teams) {
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
        
        ImageView flagView = new ImageView();
        flagView.setFitWidth(60);
        flagView.setFitHeight(40);
        flagView.setPreserveRatio(true);
        // "South Korea" -> "south_korea"
        String flagName = team.getName().toLowerCase().replace(" ", "_");
        flagView.setImage(ImageLoader.loadFlag(flagName));
        
        card.getChildren().addAll(flagView, nameLabel);
        
        card.setOnMouseClicked(e -> {
            selectedTeam = team;
            nextButton.setDisable(false);
            // Highlight selected card
            countriesPane.getChildren().forEach(n -> n.setStyle("-fx-border-color: transparent;"));
            card.setStyle("-fx-border-color: #d4af37; -fx-border-width: 3; -fx-border-radius: 5;");
        });
        
        return card;
    }

    @FXML
    private void onNextClicked() {
        SceneManager.setUserTeam(selectedTeam);
        SceneManager.loadScene("Squad.fxml", "Сборка состава - " + selectedTeam.getName());
    }

    @FXML
    private void onBackClicked() {
        SceneManager.loadScene("MainMenu.fxml", "FIFA Card Game 2026");
    }
}
