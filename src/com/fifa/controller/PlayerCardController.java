package com.fifa.controller;

import com.fifa.model.Player;
import com.fifa.util.ImageLoader;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.control.Label;
import javafx.scene.shape.Rectangle;

/**
 * Контроллер для карточки игрока (PlayerCard.fxml)
 * 
 * Отображает:
 * - Фото игрока
 * - Рейтинг overall
 * - Имя и позицию
 * - Флаг страны
 */
public class PlayerCardController {
    
    @FXML private ImageView playerImage;
    @FXML private ImageView flagImage;
    @FXML private Label ratingLabel;
    @FXML private Label nameLabel;
    @FXML private Label posLabel;
    @FXML private Rectangle cardBg;

    /**
     * Инициализировать карточку для игрока
     */
    public void setPlayer(Player player, String countryName, String photoFileName) {
        nameLabel.setText(player.getName());
        ratingLabel.setText(String.valueOf(player.getOverall()));
        posLabel.setText(player.getPosition());
        
        // Загрузить фото игрока
        if (photoFileName != null && !photoFileName.isEmpty()) {
            var playerImg = ImageLoader.loadPlayer(photoFileName);
            if (playerImg != null) {
                playerImage.setImage(playerImg);
            }
        } else {
            // Если фото не указано — покажется placeholder
            playerImage.setImage(ImageLoader.loadPlayer(""));
        }
        
        // Загрузить флаг страны
        if (countryName != null && !countryName.isEmpty()) {
            var flagImg = ImageLoader.loadFlag(countryName);
            if (flagImg != null) {
                flagImage.setImage(flagImg);
            }
        }
        
        // Цветной фон в зависимости от рейтинга
        applyCardTheme(player.getOverall());
    }

    /**
     * Изменить цвет фона карточки в зависимости от рейтинга
     */
    private void applyCardTheme(int overall) {
        cardBg.getStyleClass().clear();
        cardBg.getStyleClass().add("player-card-bg");
        
        if (overall >= 85) {
            cardBg.getStyleClass().add("player-card-bg-gold");
        } else if (overall >= 80) {
            cardBg.getStyleClass().add("player-card-bg-silver");
        } else {
            cardBg.getStyleClass().add("player-card-bg-bronze");
        }
    }

    /**
     * Получить фото игрока (для drag-and-drop)
     */
    public ImageView getPlayerImage() {
        return playerImage;
    }
}
