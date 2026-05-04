package com.fifa.util;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class SceneManager {
    private static Stage primaryStage;

    public static void setPrimaryStage(Stage stage) {
        primaryStage = stage;
    }
    
    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    public static Object loadScene(String fxmlPath, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(SceneManager.class.getResource("/fxml/" + fxmlPath));
            Parent root = loader.load();
            Scene scene = new Scene(root, 800, 600);
            scene.getStylesheets().add(SceneManager.class.getResource("/css/style.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.setTitle(title);
            primaryStage.show();
            return loader.getController();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
