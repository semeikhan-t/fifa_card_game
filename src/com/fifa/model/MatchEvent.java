package com.fifa.model;

public class MatchEvent {
    private int minute;
    private String playerName;
    private String type; 
    private boolean isHomeTeam;

    public MatchEvent(int minute, String playerName, String type, boolean isHomeTeam) {
        this.minute = minute;
        this.playerName = playerName;
        this.type = type;
        this.isHomeTeam = isHomeTeam;
    }

    public int getMinute() { return minute; }
    public String getPlayerName() { return playerName; }
    public String getType() { return type; }
    public boolean isHomeTeam() { return isHomeTeam; }

    @Override
    public String toString() {
        return minute + "'' - " + type + ": " + playerName;
    }
}