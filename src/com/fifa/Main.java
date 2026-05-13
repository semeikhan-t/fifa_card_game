package com.fifa;

import com.fifa.util.SceneManager;
import com.fifa.util.DatabaseInitializer;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        // Инициализируем БД перед запуском UI
        DatabaseInitializer.initialize();
        
        SceneManager.setPrimaryStage(primaryStage);
        SceneManager.loadScene("MainMenu.fxml", "FIFA Card Game 2026 - Main Menu");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
