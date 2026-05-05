package com.fifa.controller;

import com.fifa.model.Player;
import com.fifa.model.Team;
import com.fifa.service.SquadService;
import com.fifa.util.SceneManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.util.stream.Collectors;
import javafx.animation.TranslateTransition;
import javafx.util.Duration;

import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.Node;
import javafx.fxml.FXMLLoader;
import java.util.*;

public class SquadController {
    @FXML private Label startersCountLabel;
    @FXML private AnchorPane starterSlotsPane;
    @FXML private HBox benchBox;
    @FXML private VBox benchContainer;
    @FXML private Button playButton;
    
    private boolean isBenchVisible = false;

    private SquadService squadService = new SquadService();
    private Team currentTeam;
    private List<Player> starters = new ArrayList<>();
    private List<Player> bench = new ArrayList<>();

    // Positions for 4-3-3 Formation
    private static final Map<String, Position> FORMATION_433 = new LinkedHashMap<>() {{
        put("GK", new Position(460, 340));
        put("LB", new Position(100, 280));
        put("LCB", new Position(350, 300));
        put("RCB", new Position(570, 300));
        put("RB", new Position(820, 280));
        put("LCM", new Position(250, 180));
        put("CM", new Position(460, 160));
        put("RCM", new Position(670, 180));
        put("LW", new Position(200, 50));
        put("ST", new Position(460, 30));
        put("RW", new Position(720, 50));
    }};

    private Map<String, Player> pitchPlayers = new HashMap<>();

    @FXML
    public void initialize() {
        currentTeam = SceneManager.getUserTeam();
        if (currentTeam == null) return;

        squadService.loadSquad(currentTeam);

        // Separate starters and bench - CLEAR FIRST
        starters.clear();
        bench.clear();
        for (Player p : currentTeam.getPlayers()) {
            if (p.isStarter()) {
                starters.add(p);
            } else {
                bench.add(p);
            }
        }

        refreshUI();
    }

    private void refreshUI() {
        starterSlotsPane.getChildren().clear();
        benchBox.getChildren().clear();
        pitchPlayers.clear();

        // Setup pitch slots
        int starterIdx = 0;
        List<Map.Entry<String, Position>> formationEntries = new ArrayList<>(FORMATION_433.entrySet());
        
        for (Map.Entry<String, Position> entry : formationEntries) {
            String posName = entry.getKey();
            Position pos = entry.getValue();

            VBox slot = createSlot(posName, pos);
            starterSlotsPane.getChildren().add(slot);

            if (starterIdx < starters.size()) {
                Player p = starters.get(starterIdx);
                pitchPlayers.put(posName, p);
                Node card = createPlayerCard(p);
                slot.getChildren().add(card);
                starterIdx++;
            }
        }

        // Setup bench
        for (Player p : bench) {
            Node card = createPlayerCard(p);
            benchBox.getChildren().add(card);
        }

        updateLabels();
    }

    private VBox createSlot(String posName, Position pos) {
        VBox slot = new VBox();
        slot.setAlignment(javafx.geometry.Pos.CENTER);
        slot.setPrefSize(80, 110);
        AnchorPane.setLeftAnchor(slot, pos.x);
        AnchorPane.setTopAnchor(slot, pos.y);
        
        Label lbl = new Label(posName);
        lbl.setStyle("-fx-text-fill: rgba(255,255,255,0.3); -fx-font-weight: bold;");
        slot.getChildren().add(lbl);

        // Drag and Drop handlers for slot
        slot.setOnDragOver(e -> {
            if (e.getGestureSource() != slot && e.getDragboard().hasString()) {
                e.acceptTransferModes(TransferMode.MOVE);
            }
            e.consume();
        });

        slot.setOnDragDropped(e -> {
            Dragboard db = e.getDragboard();
            if (db.hasString()) {
                int playerId = Integer.parseInt(db.getString());
                handlePlayerMove(playerId, posName);
                e.setDropCompleted(true);
            }
            e.consume();
        });

        return slot;
    }

    private Node createPlayerCard(Player p) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/PlayerCard.fxml"));
            Node card = loader.load();
            
            PlayerCardController controller = loader.getController();
            
            // Get country name for flag. We already have currentTeam which is the team of all these players!
            String countryName = currentTeam.getName().toLowerCase();
            
            controller.setPlayer(p, countryName, p.getPhotoPath());

            card.setOnDragDetected(e -> {
                Dragboard db = card.startDragAndDrop(TransferMode.MOVE);
                ClipboardContent content = new ClipboardContent();
                content.putString(String.valueOf(p.getId()));
                db.setContent(content);
                e.consume();
            });

            return card;
        } catch (Exception e) {
            e.printStackTrace();
            return new Label(p.getName());
        }
    }


    private void handlePlayerMove(int playerId, String targetPos) {
        Player movingPlayer = currentTeam.getPlayers().stream()
                .filter(p -> p.getId() == playerId)
                .findFirst().orElse(null);

        if (movingPlayer == null) return;

        // If player was on pitch, swap or move
        Player playerAtTarget = pitchPlayers.get(targetPos);
        
        if (playerAtTarget != null) {
            // Swap
            playerAtTarget.setStarter(movingPlayer.isStarter());
            movingPlayer.setStarter(true);
        } else {
            movingPlayer.setStarter(true);
        }

        // Re-calculate starters and bench lists
        starters.clear();
        bench.clear();
        for (Player p : currentTeam.getPlayers()) {
            if (p.isStarter()) starters.add(p);
            else bench.add(p);
        }

        // Limit starters to 11
        if (starters.size() > 11) {
            movingPlayer.setStarter(false);
            starters.remove(movingPlayer);
            bench.add(movingPlayer);
        }

        refreshUI();
    }

    private void updateLabels() {
        startersCountLabel.setText("Selected: " + starters.size() + "/11");
        playButton.setDisable(starters.size() != 11);
    }

    @FXML
    private void onPlayClicked() {
        SceneManager.loadScene("OpponentSelection.fxml", "Выбор соперника");
    }

    @FXML
    private void onBackClicked() {
        SceneManager.loadScene("CountrySelection.fxml", "Выбор сборной");
    }

    @FXML
    private void onToggleBenchClicked() {
        TranslateTransition tt = new TranslateTransition(Duration.millis(300), benchContainer);
        if (isBenchVisible) {
            tt.setToY(250); // Hide
            isBenchVisible = false;
        } else {
            tt.setToY(0); // Show
            isBenchVisible = true;
        }
        tt.play();
    }

    private static class Position {
        double x, y;
        Position(double x, double y) { this.x = x; this.y = y; }
    }
}

