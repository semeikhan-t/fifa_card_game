package com.fifa.dao;

import com.fifa.model.Player;
import com.fifa.util.DatabaseManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlayerDAO extends AbstractDAO<Player> {

    @Override
    public Player findById(int id) {
        // Implementation if needed
        return null;
    }

    @Override
    public List<Player> findAll() {
        return null;
    }

    public List<Player> findByCountryId(int countryId) {
        List<Player> players = new ArrayList<>();
        String sql = "SELECT * FROM players WHERE country_id = ?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, countryId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                players.add(mapRow(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return players;
    }

    @Override
    public void save(Player entity) {
    }

    private Player mapRow(ResultSet rs) throws SQLException {
        Player player = new Player();
        player.setId(rs.getInt("id"));
        player.setName(rs.getString("name"));
        player.setCountryId(rs.getInt("country_id"));
        player.setPosition(rs.getString("position"));
        player.setOverall(rs.getInt("overall"));
        player.setStarter(rs.getBoolean("is_starter"));
        return player;
    }
}
