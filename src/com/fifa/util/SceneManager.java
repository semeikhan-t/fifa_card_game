package com.fifa.util;

import com.fifa.model.MatchResult;
import com.fifa.model.Team;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
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
        try {
            var fxmlUrl = SceneManager.class.getResource("/fxml/" + fxmlFile);
            if (fxmlUrl == null) {
                System.err.println("FXML file NOT FOUND: /fxml/" + fxmlFile);
                return;
            }
            
            FXMLLoader loader = new FXMLLoader(fxmlUrl);
            Parent root = loader.load();
            Scene scene = new Scene(root);
            
            var cssUrl = SceneManager.class.getResource("/css/style.css");
            if (cssUrl != null) {
                scene.getStylesheets().add(cssUrl.toExternalForm());
            } else {
                System.err.println("CSS file NOT FOUND: /css/style.css");
            }

            primaryStage.setTitle(title);
            primaryStage.setScene(scene);
            primaryStage.show();
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
