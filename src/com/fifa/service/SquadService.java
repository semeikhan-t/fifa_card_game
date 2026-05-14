package com.fifa.service;

import com.fifa.dao.PlayerDAO;
import com.fifa.model.Player;
import com.fifa.model.Team;
import java.util.List;

public class SquadService {
    private PlayerDAO playerDAO = new PlayerDAO();

    public void loadSquad(Team team) {
        List<Player> players = playerDAO.findByCountry(team.getId());
        team.setPlayers(players);
    }

    public boolean validateSquad(Team team) {
        long starters = team.getPlayers().stream().filter(Player::isStarter).count();
        return starters == 11;
    }
}