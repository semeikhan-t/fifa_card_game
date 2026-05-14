package com.fifa.controller;

import com.fifa.model.Player;
import com.fifa.util.ImageLoader;
import javafx.animation.Animation;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class PlayerCardController {
    
    @FXML private StackPane cardRoot;
    @FXML private StackPane cardBody;
    @FXML private ImageView playerImage;
    @FXML private ImageView flagImage;
    @FXML private Label ratingLabel;
    @FXML private Label nameLabel;
    @FXML private Label posLabel;
    @FXML private Rectangle cardBg;
    @FXML private Rectangle reflectionOverlay;
    @FXML private Rectangle selectionOutline;

    private TranslateTransition idleFloat;
    private ScaleTransition hoverScale;
    
    private boolean isSelected = false;

    @FXML
    public void initialize() {
        setupAnimations();
    }

    private void setupAnimations() {
        
        idleFloat = new TranslateTransition(Duration.millis(1500 + Math.random() * 500), cardBody);
        idleFloat.setByY(-5f); 
        idleFloat.setCycleCount(Animation.INDEFINITE);
        idleFloat.setAutoReverse(true);
        idleFloat.play();

        
        hoverScale = new ScaleTransition(Duration.millis(200), cardRoot);
        
        cardRoot.setOnMouseEntered(e -> {
            idleFloat.pause();
            hoverScale.setToX(1.08);
            hoverScale.setToY(1.08);
            hoverScale.play();
            
            reflectionOverlay.setTranslateX(10);
            reflectionOverlay.setTranslateY(-10);
        });

        cardRoot.setOnMouseExited(e -> {
            hoverScale.setToX(1.0);
            hoverScale.setToY(1.0);
            hoverScale.play();
            reflectionOverlay.setTranslateX(0);
            reflectionOverlay.setTranslateY(0);
            idleFloat.play();
        });
    }

    public void setPlayer(Player player, String countryName, String photoFileName) {
        nameLabel.setText(player.getName());
        ratingLabel.setText(String.valueOf(player.getOverall()));
        posLabel.setText(player.getPosition());
        
        if (photoFileName != null && !photoFileName.isEmpty()) {
            var playerImg = ImageLoader.loadPlayer(photoFileName);
            if (playerImg != null) {
                playerImage.setImage(playerImg);
            }
        } else {
            playerImage.setImage(ImageLoader.loadPlayer(""));
        }
        
        if (countryName != null && !countryName.isEmpty()) {
            var flagImg = ImageLoader.loadFlag(countryName);
            if (flagImg != null) {
                flagImage.setImage(flagImg);
            }
        }
        
        applyCardTheme(player.getOverall());
    }

    private void applyCardTheme(int overall) {
        cardBg.getStyleClass().removeAll("player-card-bg-gold", "player-card-bg-silver", "player-card-bg-bronze");
        
        if (overall >= 75) {
            cardBg.getStyleClass().add("player-card-bg-gold");
        } else if (overall >= 65) {
            cardBg.getStyleClass().add("player-card-bg-silver");
        } else {
            cardBg.getStyleClass().add("player-card-bg-bronze");
        }
    }

    public void setSelected(boolean selected) {
        this.isSelected = selected;
        selectionOutline.setVisible(selected);
        if (selected) {
            cardRoot.setScaleX(1.05);
            cardRoot.setScaleY(1.05);
        } else {
            cardRoot.setScaleX(1.0);
            cardRoot.setScaleY(1.0);
        }
    }

    public ImageView getPlayerImage() {
        return playerImage;
    }
}