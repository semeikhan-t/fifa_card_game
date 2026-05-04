package com.fifa.model;

public class MatchEvent {
    private int minute;
    private Player player;
    private Team team;
    private String type; // e.g. "GOAL"

    public MatchEvent(int minute, Player player, Team team, String type) {
        this.minute = minute;
        this.player = player;
        this.team = team;
        this.type = type;
    }

    public int getMinute() {
        return minute;
    }

    public Player getPlayer() {
        return player;
    }

    public Team getTeam() {
        return team;
    }

    public String getType() {
        return type;
    }
}
