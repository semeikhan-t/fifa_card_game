package com.fifa.service;

import com.fifa.model.MatchEvent;
import com.fifa.model.MatchResult;
import com.fifa.model.Player;
import com.fifa.model.Team;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MatchService {
    private Random random = new Random();

    public MatchResult simulateMatch(Team home, Team away) {
        MatchResult result = new MatchResult();
        result.setHomeId(home.getId());
        result.setAwayId(away.getId());
        result.setHomeName(home.getName());
        result.setAwayName(away.getName());

        int homeAvg = home.calculateAverageOverall();
        int awayAvg = away.calculateAverageOverall();

        // Базовая вероятность гола зависит от разницы рейтингов
        double homeProb = 0.5 + (homeAvg - awayAvg) / 100.0;
        
        // Симуляция голов
        int homeGoals = simulateGoals(homeProb);
        int awayGoals = simulateGoals(1.0 - homeProb);

        result.setHomeScore(homeGoals);
        result.setAwayScore(awayGoals);

        // Генерация событий (кто забил и на какой минуте)
        generateEvents(result, home, away, homeGoals, awayGoals);

        return result;
    }

    private int simulateGoals(double probability) {
        int goals = 0;
        // 4 попытки забить за матч
        for (int i = 0; i < 4; i++) {
            if (random.nextDouble() < (probability * 0.4)) {
                goals++;
            }
        }
        // Случайный фактор +/- 1 гол
        if (random.nextDouble() < 0.1) goals++;
        return goals;
    }

    private void generateEvents(MatchResult result, Team home, Team away, int hg, int ag) {
        List<MatchEvent> events = new ArrayList<>();
        
        for (int i = 0; i < hg; i++) {
            events.add(new MatchEvent(random.nextInt(90) + 1, getRandomScorer(home), "GOAL", true));
        }
        for (int i = 0; i < ag; i++) {
            events.add(new MatchEvent(random.nextInt(90) + 1, getRandomScorer(away), "GOAL", false));
        }
        
        // Сортировка по минутам
        events.sort((e1, e2) -> Integer.compare(e1.getMinute(), e2.getMinute()));
        result.setEvents(events);
    }

    private String getRandomScorer(Team team) {
        List<Player> scorers = new ArrayList<>();
        for (Player p : team.getPlayers()) {
            if (p.getPosition().equals("FWD")) scorers.add(p);
            if (p.getPosition().equals("MID")) scorers.add(p);
        }
        if (scorers.isEmpty()) return team.getPlayers().get(random.nextInt(team.getPlayers().size())).getName();
        return scorers.get(random.nextInt(scorers.size())).getName();
    }
}
