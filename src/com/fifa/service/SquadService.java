package com.fifa.service;

import com.fifa.dao.PlayerDAO;
import com.fifa.model.Player;
import com.fifa.model.Team;

import java.util.ArrayList;
import java.util.List;

public class SquadService {
    private PlayerDAO playerDAO = new PlayerDAO();
    private Team selectedTeam;
    private List<Player> squad;
    private Team opponentTeam;

    private static SquadService instance;

    public static SquadService getInstance() {
        if (instance == null) {
            instance = new SquadService();
        }
        return instance;
    }

    public void setSelectedTeam(Team team) {
        this.selectedTeam = team;
        this.squad = playerDAO.findByCountryId(team.getId());
    }

    public Team getSelectedTeam() {
        return selectedTeam;
    }

    public List<Player> getSquad() {
        return squad;
    }

    public List<Player> getStartingEleven() {
        List<Player> starters = new ArrayList<>();
        for (Player p : squad) {
            if (p.isStarter()) {
                starters.add(p);
            }
        }
        return starters;
    }

    public void setOpponentTeam(Team opponentTeam) {
        this.opponentTeam = opponentTeam;
    }

    public Team getOpponentTeam() {
        return opponentTeam;
    }
}
