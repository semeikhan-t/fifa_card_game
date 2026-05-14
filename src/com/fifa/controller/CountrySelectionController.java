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
        com.fifa.util.AudioManager.playMusic("main.mp3", 0.2, 11.0);
        List<Team> teams = teamDAO.findAll();
        for (Team team : teams) {
            VBox card = createCountryCard(team);
            countriesPane.getChildren().add(card);
        }
    }

    private VBox createCountryCard(Team team) {
        VBox card = new VBox(8);
        card.getStyleClass().add("country-card");
        card.setAlignment(javafx.geometry.Pos.CENTER);
        card.setPrefSize(100, 130);
        card.setPadding(new javafx.geometry.Insets(8));
        
        Label nameLabel = new Label(team.getName().toUpperCase());
        nameLabel.setStyle("-fx-text-fill: white; -fx-font-size: 11px; -fx-font-weight: 900; -fx-font-style: italic; -fx-letter-spacing: 1px;");
        
        ImageView flagView = new ImageView();
        flagView.setFitWidth(60);
        flagView.setFitHeight(40);
        flagView.setPreserveRatio(true);
        
        String flagName = team.getName().toLowerCase().replace(" ", "_");
        flagView.setImage(ImageLoader.loadFlag(flagName));
        flagView.setEffect(new javafx.scene.effect.DropShadow(15, javafx.scene.paint.Color.BLACK));
        
        card.getChildren().addAll(flagView, nameLabel);
        
        javafx.animation.TranslateTransition hoverIn = new javafx.animation.TranslateTransition(javafx.util.Duration.millis(200), card);
        hoverIn.setToY(-10);
        javafx.animation.TranslateTransition hoverOut = new javafx.animation.TranslateTransition(javafx.util.Duration.millis(200), card);
        hoverOut.setToY(0);

        card.setOnMouseEntered(e -> {
            if (selectedTeam != team) {
                hoverIn.playFromStart();
            }
        });
        
        card.setOnMouseExited(e -> {
            if (selectedTeam != team) {
                hoverOut.playFromStart();
            }
        });

        card.setOnMouseClicked(e -> {
            selectedTeam = team;
            nextButton.setDisable(false);
            
            
            countriesPane.getChildren().forEach(n -> {
                n.getStyleClass().remove("country-card-selected");
                n.setTranslateY(0);
            });
            card.getStyleClass().add("country-card-selected");
            card.setTranslateY(-15);
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