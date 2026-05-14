package com.fifa.util;

import com.fifa.model.MatchResult;
import com.fifa.model.Team;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.animation.FadeTransition;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import java.io.IOException;

public class SceneManager {
    private static Stage primaryStage;
    private static Team userTeam;
    private static Team opponentTeam;
    private static MatchResult lastResult;

    public static void setPrimaryStage(Stage stage) {
        primaryStage = stage;
    }

    public static void loadScene(String fxmlFile, String title) {
        if (primaryStage == null) return;

        Scene currentScene = primaryStage.getScene();
        if (currentScene != null && currentScene.getRoot() != null) {
            FadeTransition fadeOut = new FadeTransition(Duration.millis(400), currentScene.getRoot());
            fadeOut.setFromValue(1.0);
            fadeOut.setToValue(0.0);
            fadeOut.setOnFinished(e -> performLoad(fxmlFile, title));
            fadeOut.play();
        } else {
            performLoad(fxmlFile, title);
        }
    }

    private static void performLoad(String fxmlFile, String title) {
        try {
            var fxmlUrl = SceneManager.class.getResource("/fxml/" + fxmlFile);
            if (fxmlUrl == null) {
                System.err.println("FXML file NOT FOUND: /fxml/" + fxmlFile);
                return;
            }
            
            FXMLLoader loader = new FXMLLoader(fxmlUrl);
            Parent root = loader.load();
            
            
            Scene scene = new Scene(root, Color.web("#051005"));
            
            var cssUrl = SceneManager.class.getResource("/css/style.css");
            if (cssUrl != null) {
                scene.getStylesheets().add(cssUrl.toExternalForm());
            }

            root.setOpacity(0.0);
            primaryStage.setTitle(title);
            primaryStage.setScene(scene);
            
            FadeTransition fadeIn = new FadeTransition(Duration.millis(400), root);
            fadeIn.setFromValue(0.0);
            fadeIn.setToValue(1.0);
            fadeIn.play();
            
            if (!primaryStage.isShowing()) {
                primaryStage.show();
            }
        } catch (Exception e) {
            System.err.println("Error loading scene: " + fxmlFile);
            e.printStackTrace();
        }
    }

    public static Team getUserTeam() { return userTeam; }
    public static void setUserTeam(Team team) { userTeam = team; }

    public static Team getOpponentTeam() { return opponentTeam; }
    public static void setOpponentTeam(Team team) { opponentTeam = team; }

    public static MatchResult getLastResult() { return lastResult; }
    public static void setLastResult(MatchResult result) { lastResult = result; }
}