package com.fifa.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MatchResult extends Entity {
    private int homeId;
    private int awayId;
    private int homeScore;
    private int awayScore;
    private LocalDateTime playedAt;
    private List<MatchEvent> events = new ArrayList<>();

    // Дополнительные поля для удобства отображения
    private String homeName;
    private String awayName;

    public MatchResult() {}

    public int getHomeId() { return homeId; }
    public void setHomeId(int homeId) { this.homeId = homeId; }

    public int getAwayId() { return awayId; }
    public void setAwayId(int awayId) { this.awayId = awayId; }

    public int getHomeScore() { return homeScore; }
    public void setHomeScore(int homeScore) { this.homeScore = homeScore; }

    public int getAwayScore() { return awayScore; }
    public void setAwayScore(int awayScore) { this.awayScore = awayScore; }

    public LocalDateTime getPlayedAt() { return playedAt; }
    public void setPlayedAt(LocalDateTime playedAt) { this.playedAt = playedAt; }

    public List<MatchEvent> getEvents() { return events; }
    public void setEvents(List<MatchEvent> events) { this.events = events; }

    public String getHomeName() { return homeName; }
    public void setHomeName(String homeName) { this.homeName = homeName; }

    public String getAwayName() { return awayName; }
    public void setAwayName(String awayName) { this.awayName = awayName; }
}
