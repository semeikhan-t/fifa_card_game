package com.fifa.controller;

import com.fifa.model.Player;
import com.fifa.model.Team;
import com.fifa.service.SquadService;
import com.fifa.util.SceneManager;
import javafx.animation.*;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.Node;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.paint.Color;
import javafx.scene.effect.GaussianBlur;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.*;
import javafx.util.Duration;

import java.util.*;

public class SquadController {
    
    
    @FXML private Label startersCountLabel;
    @FXML private AnchorPane starterSlotsPane;
    @FXML private HBox benchBox;
    @FXML private Button playButton;
    
    
    @FXML private Circle ratingProgressRing;
    @FXML private Label teamRatingLabel;

    @FXML private VBox benchContainer;
    @FXML private Button toggleBenchBtn;
    private boolean isBenchOpen = true;

    private SquadService squadService = new SquadService();
    private Team currentTeam;
    private List<Player> starters = new ArrayList<>();
    private List<Player> bench = new ArrayList<>();

    
    private static final Map<String, Position> FORMATION_433 = new LinkedHashMap<>() {{
        put("GK", new Position(603, 380));
        put("LB", new Position(200, 280));
        put("LCB", new Position(450, 320));
        put("RCB", new Position(756, 320));
        put("RB", new Position(1006, 280));
        put("LCM", new Position(350, 180));
        put("CM", new Position(603, 160));
        put("RCM", new Position(856, 180));
        put("LW", new Position(250, 40));
        put("ST", new Position(603, 20));
        put("RW", new Position(956, 40));
    }};

    private Map<String, Player> pitchPlayers = new HashMap<>();
    private Map<Integer, String> playerSlotAssignment = new HashMap<>();
    private Map<Integer, PlayerCardController> cardControllers = new HashMap<>();

    @FXML
    public void initialize() {
        currentTeam = SceneManager.getUserTeam();
        if (currentTeam == null) return;

        squadService.loadSquad(currentTeam);

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
        cardControllers.clear();

        List<Map.Entry<String, Position>> formationEntries = new ArrayList<>(FORMATION_433.entrySet());
        normalizeStarterAssignments(formationEntries);

        Map<String, Player> assigned = new LinkedHashMap<>();
        for (Map.Entry<String, Position> entry : formationEntries) {
            String posName = entry.getKey();
            Player player = findPlayerAssignedToSlot(posName);
            if (player != null) {
                assigned.put(posName, player);
            }
        }

        for (Map.Entry<String, Position> entry : formationEntries) {
            String posName = entry.getKey();
            Position pos = entry.getValue();

            VBox slot = createSlot(posName, pos);
            starterSlotsPane.getChildren().add(slot);

            Player p = assigned.get(posName);
            if (p != null) {
                pitchPlayers.put(posName, p);
                Node card = createPlayerCard(p);
                slot.getChildren().add(card);
            }
        }

        for (Player p : bench) {
            Node card = createPlayerCard(p);
            benchBox.getChildren().add(card);
        }

        updateLabels();
    }

    private VBox createSlot(String posName, Position pos) {
        VBox slot = new VBox();
        slot.setAlignment(javafx.geometry.Pos.CENTER);
        slot.setPrefSize(80, 116);
        AnchorPane.setLeftAnchor(slot, pos.x);
        AnchorPane.setTopAnchor(slot, pos.y);
        
        
        Label lbl = new Label(posName);
        lbl.setStyle("-fx-text-fill: rgba(0, 255, 102, 0.3); -fx-font-weight: 900; -fx-font-size: 18px; -fx-effect: dropshadow(gaussian, rgba(0,255,102,0.5), 10, 0, 0, 0);");
        
        Circle slotRing = new Circle(30, Color.TRANSPARENT);
        slotRing.setStroke(Color.web("#00ff66", 0.2));
        slotRing.setStrokeWidth(2);
        slotRing.setStrokeDashOffset(10);
        slotRing.getStrokeDashArray().addAll(10d, 5d);
        
        
        ScaleTransition pulse = new ScaleTransition(Duration.millis(1500), slotRing);
        pulse.setByX(0.1);
        pulse.setByY(0.1);
        pulse.setAutoReverse(true);
        pulse.setCycleCount(Animation.INDEFINITE);
        pulse.play();

        StackPane emptyIndicator = new StackPane(slotRing, lbl);
        slot.getChildren().add(emptyIndicator);

        slot.setOnDragOver(e -> {
            if (e.getGestureSource() != slot && e.getDragboard().hasString()) {
                int playerId = Integer.parseInt(e.getDragboard().getString());
                Player dragged = findPlayerById(playerId);
                if (dragged != null && canPlaySlot(dragged, posName)) {
                    e.acceptTransferModes(TransferMode.MOVE);
                }
            }
            e.consume();
        });

        slot.setOnDragDropped(e -> {
            Dragboard db = e.getDragboard();
            if (db.hasString()) {
                int playerId = Integer.parseInt(db.getString());
                if (handlePlayerMove(playerId, posName)) {
                    e.setDropCompleted(true);
                } else {
                    e.setDropCompleted(false);
                }
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
            cardControllers.put(p.getId(), controller);
            
            String countryCode = currentTeam.getCode();
            controller.setPlayer(p, countryCode, p.getPhotoPath());

            card.setOnDragDetected(e -> {
                Dragboard db = card.startDragAndDrop(TransferMode.MOVE);
                ClipboardContent content = new ClipboardContent();
                content.putString(String.valueOf(p.getId()));
                db.setContent(content);
                controller.setSelected(true);
                e.consume();
            });
            
            card.setOnDragDone(e -> {
                controller.setSelected(false);
            });

            return card;
        } catch (Exception e) {
            e.printStackTrace();
            return new Label(p.getName());
        }
    }

    private boolean handlePlayerMove(int playerId, String targetPos) {
        Player movingPlayer = findPlayerById(playerId);
        if (movingPlayer == null || !canPlaySlot(movingPlayer, targetPos)) {
            return false;
        }

        String currentSlot = playerSlotAssignment.get(playerId);
        Player playerAtTarget = findPlayerAssignedToSlot(targetPos);

        if (playerAtTarget != null) {
            if (movingPlayer.isStarter()) {
                playerSlotAssignment.put(playerId, targetPos);
                playerSlotAssignment.put(playerAtTarget.getId(), currentSlot == null ? targetPos : currentSlot);
            } else {
                playerAtTarget.setStarter(false);
                playerSlotAssignment.remove(playerAtTarget.getId());
                movingPlayer.setStarter(true);
                playerSlotAssignment.put(playerId, targetPos);
            }
        } else {
            if (movingPlayer.isStarter()) {
                playerSlotAssignment.put(playerId, targetPos);
            } else {
                movingPlayer.setStarter(true);
                playerSlotAssignment.put(playerId, targetPos);
            }
        }

        List<Map.Entry<String, Position>> formationEntries = new ArrayList<>(FORMATION_433.entrySet());
        normalizeStarterAssignments(formationEntries);
        refreshUI();
        return true;
    }

    private void normalizeStarterAssignments(List<Map.Entry<String, Position>> formationEntries) {
        Iterator<Map.Entry<Integer, String>> iterator = playerSlotAssignment.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, String> entry = iterator.next();
            Player p = findPlayerById(entry.getKey());
            if (p == null || !p.isStarter() || !canPlaySlot(p, entry.getValue())) {
                iterator.remove();
            }
        }

        Set<Integer> assignedPlayers = new HashSet<>(playerSlotAssignment.keySet());
        Set<String> usedSlots = new HashSet<>(playerSlotAssignment.values());

        for (Player p : currentTeam.getPlayers()) {
            if (!p.isStarter() || assignedPlayers.contains(p.getId())) continue;
            for (Map.Entry<String, Position> entry : formationEntries) {
                String posName = entry.getKey();
                if (!usedSlots.contains(posName) && canPlaySlot(p, posName)) {
                    playerSlotAssignment.put(p.getId(), posName);
                    assignedPlayers.add(p.getId());
                    usedSlots.add(posName);
                    break;
                }
            }
        }

        for (Player p : currentTeam.getPlayers()) {
            if (p.isStarter() && !playerSlotAssignment.containsKey(p.getId())) {
                p.setStarter(false);
            }
        }

        playerSlotAssignment.entrySet().removeIf(entry -> {
            Player p = findPlayerById(entry.getKey());
            return p == null || !p.isStarter();
        });

        starters.clear();
        bench.clear();
        for (Player p : currentTeam.getPlayers()) {
            if (p.isStarter()) starters.add(p);
            else bench.add(p);
        }
    }

    private Player findPlayerAssignedToSlot(String slotName) {
        return playerSlotAssignment.entrySet().stream()
                .filter(entry -> entry.getValue().equals(slotName))
                .map(entry -> findPlayerById(entry.getKey()))
                .filter(Objects::nonNull)
                .findFirst().orElse(null);
    }

    private boolean canPlaySlot(Player player, String slotName) {
        String playerGroup = normalizePositionGroup(player.getPosition());
        String slotGroup = normalizeSlotGroup(slotName);
        return playerGroup != null && playerGroup.equals(slotGroup);
    }

    private String normalizePositionGroup(String position) {
        if (position == null) return null;
        String pos = position.trim().toUpperCase();
        return switch (pos) {
            case "GK" -> "GK";
            case "DEF", "DF" -> "DEF";
            case "MID" -> "MID";
            case "FWD", "FW" -> "FWD";
            default -> {
                if (pos.matches(".*GK.*")) yield "GK";
                if (pos.matches(".*DEF.*") || pos.matches("LB|LCB|CB|RCB|RB|RWB|LWB")) yield "DEF";
                if (pos.matches(".*MID.*") || pos.matches("CM|LCM|RCM|CDM|CAM")) yield "MID";
                if (pos.matches(".*FWD.*") || pos.matches("ST|LW|RW|CF|LF|RF")) yield "FWD";
                yield null;
            }
        };
    }

    private String normalizeSlotGroup(String slotName) {
        if (slotName == null) return null;
        return switch (slotName.toUpperCase()) {
            case "GK" -> "GK";
            case "LB", "LCB", "RCB", "RB" -> "DEF";
            case "LCM", "CM", "RCM" -> "MID";
            case "LW", "ST", "RW" -> "FWD";
            default -> null;
        };
    }

    private Player findPlayerById(int playerId) {
        return currentTeam.getPlayers().stream()
                .filter(p -> p.getId() == playerId)
                .findFirst().orElse(null);
    }

    private void updateLabels() {
        startersCountLabel.setText("SELECTED: " + starters.size() + "/11");
        playButton.setDisable(starters.size() != 11);
        
        
        int totalRating = 0;
        for (Player p : starters) {
            totalRating += p.getOverall();
        }
        int avgRating = starters.isEmpty() ? 0 : totalRating / starters.size();
        
        
        teamRatingLabel.setText(String.valueOf(avgRating));
        double targetDashOffset = 120.0 - ((avgRating / 100.0) * 120.0);
        
        Timeline timeline = new Timeline(
            new KeyFrame(Duration.millis(500), new KeyValue(ratingProgressRing.strokeDashOffsetProperty(), targetDashOffset, Interpolator.EASE_OUT))
        );
        timeline.play();
    }

    @FXML
    private void onPlayClicked() {
        SceneManager.loadScene("OpponentSelection.fxml", "Выбор соперника");
    }

    @FXML
    private void onBackClicked() {
        SceneManager.loadScene("CountrySelection.fxml", "Select Nation");
    }

    @FXML
    private void onToggleBenchClicked() {
        isBenchOpen = !isBenchOpen;
        TranslateTransition tt = new TranslateTransition(Duration.millis(300), benchContainer);
        if (isBenchOpen) {
            tt.setToY(0);
            toggleBenchBtn.setText("▼ SUBSTITUTES ▼");
        } else {
            tt.setToY(200); 
            toggleBenchBtn.setText("▲ SUBSTITUTES ▲");
        }
        tt.play();
    }

    private static class Position {
        double x, y;
        Position(double x, double y) { this.x = x; this.y = y; }
    }
}