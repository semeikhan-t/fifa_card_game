package com.fifa.service;

import com.fifa.dao.MatchDAO;
import com.fifa.model.MatchEvent;
import com.fifa.model.MatchResult;
import com.fifa.model.Player;
import com.fifa.model.Team;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MatchService {
    private MatchDAO matchDAO = new MatchDAO();
    private Random random = new Random();

    public MatchResult simulate(Team home, List<Player> homeSquad, Team away, List<Player> awaySquad) {
        int homeOvr = calculateOverall(homeSquad);
        int awayOvr = calculateOverall(awaySquad);

        // Add random factor +- 20%
        double homeFactor = 0.8 + (random.nextDouble() * 0.4);
        double awayFactor = 0.8 + (random.nextDouble() * 0.4);
        
        double homePower = homeOvr * homeFactor;
        double awayPower = awayOvr * awayFactor;

        int homeGoals = generateGoals(homePower, awayPower);
        int awayGoals = generateGoals(awayPower, homePower);

        MatchResult result = new MatchResult();
        result.setHomeId(home.getId());
        result.setAwayId(away.getId());
        result.setHomeScore(homeGoals);
        result.setAwayScore(awayGoals);

        // Save to DB
        matchDAO.save(result);
        return result;
    }

    public List<MatchEvent> generateEvents(MatchResult result, Team home, List<Player> homeSquad, Team away, List<Player> awaySquad) {
        List<MatchEvent> events = new ArrayList<>();
        
        for (int i = 0; i < result.getHomeScore(); i++) {
            events.add(new MatchEvent(random.nextInt(90) + 1, getRandomAttacker(homeSquad), home, "GOAL"));
        }
        for (int i = 0; i < result.getAwayScore(); i++) {
            events.add(new MatchEvent(random.nextInt(90) + 1, getRandomAttacker(awaySquad), away, "GOAL"));
        }
        
        // Sort events chronologically
        events.sort((e1, e2) -> Integer.compare(e1.getMinute(), e2.getMinute()));
        return events;
    }

    private int calculateOverall(List<Player> squad) {
        if (squad == null || squad.isEmpty()) return 50;
        int sum = 0;
        for (Player p : squad) {
            sum += p.getOverall();
        }
        return sum / squad.size();
    }

    private int generateGoals(double myPower, double opponentPower) {
        double diff = myPower - opponentPower;
        int baseGoals = random.nextInt(3); // 0 to 2
        if (diff > 10) baseGoals += 1;
        if (diff > 20) baseGoals += 1;
        if (diff < -10) baseGoals = Math.max(0, baseGoals - 1);
        if (diff < -20) baseGoals = Math.max(0, baseGoals - 2);
        
        // Ensure within 0-4 as per spec
        return Math.min(4, Math.max(0, baseGoals + random.nextInt(2)));
    }

    private Player getRandomAttacker(List<Player> squad) {
        List<Player> attackers = new ArrayList<>();
        for (Player p : squad) {
            if ("FWD".equals(p.getPosition()) || "MID".equals(p.getPosition())) {
                attackers.add(p);
            }
        }
        if (attackers.isEmpty() && !squad.isEmpty()) return squad.get(0);
        return attackers.get(random.nextInt(attackers.size()));
    }
}
