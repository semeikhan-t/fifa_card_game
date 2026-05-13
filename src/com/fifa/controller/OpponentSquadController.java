package com.fifa.controller;

import com.fifa.model.Player;
import com.fifa.model.Team;
import com.fifa.service.SquadService;
import com.fifa.util.SceneManager;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.Node;
import javafx.fxml.FXMLLoader;
import javafx.animation.TranslateTransition;
import javafx.util.Duration;
import java.util.*;

public class OpponentSquadController {
    @FXML private Label teamNameLabel;
    @FXML private AnchorPane starterSlotsPane;
    @FXML private HBox benchBox;
    @FXML private VBox benchContainer;
    @FXML private Button backButton;
    @FXML private Button startMatchButton;

    private boolean isBenchVisible = false;

    private SquadService squadService = new SquadService();
    private Team opponentTeam;
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
        opponentTeam = SceneManager.getOpponentTeam();
        if (opponentTeam == null) return;

        teamNameLabel.setText("Состав команды: " + opponentTeam.getName());

        squadService.loadSquad(opponentTeam);

        // Separate starters and bench
        starters.clear();
        bench.clear();
        for (Player p : opponentTeam.getPlayers()) {
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

        return slot;
    }

    private Node createPlayerCard(Player p) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/PlayerCard.fxml"));
            Node card = loader.load();

            PlayerCardController controller = loader.getController();

            String countryCode = opponentTeam.getCode();

            controller.setPlayer(p, countryCode, p.getPhotoPath());

            return card;
        } catch (Exception e) {
            e.printStackTrace();
            return new Label(p.getName());
        }
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

    @FXML
    private void onBackClicked() {
        SceneManager.loadScene("OpponentSelection.fxml", "Выберите соперника");
    }

    @FXML
    private void onStartMatchClicked() {
        SceneManager.loadScene("Match.fxml", "МАТЧ: " + SceneManager.getUserTeam().getName() + " vs " + opponentTeam.getName());
    }

    private static class Position {
        double x, y;
        Position(double x, double y) { this.x = x; this.y = y; }
    }
}