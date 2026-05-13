package com.fifa.model;

import java.util.ArrayList;
import java.util.List;

public class Team extends Entity {
    private String name;
    private int ovrAttack;
    private int ovrDefense;
    private String code;
    private List<Player> players = new ArrayList<>();

    public Team() {}

    public Team(String name, int ovrAttack, int ovrDefense) {
        this.name = name;
        this.ovrAttack = ovrAttack;
        this.ovrDefense = ovrDefense;
    }

    // Getters and Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public int getOvrAttack() { return ovrAttack; }
    public void setOvrAttack(int ovrAttack) { this.ovrAttack = ovrAttack; }

    public int getOvrDefense() { return ovrDefense; }
    public void setOvrDefense(int ovrDefense) { this.ovrDefense = ovrDefense; }

    public List<Player> getPlayers() { return players; }
    public void setPlayers(List<Player> players) { this.players = players; }

    public int calculateAverageOverall() {
        if (players.isEmpty()) return 0;
        int sum = 0;
        for (Player p : players) {
            sum += p.getOverall();
        }
        return sum / players.size();
    }
}
