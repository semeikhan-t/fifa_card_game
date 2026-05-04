package com.fifa.dao;

import com.fifa.model.Player;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlayerDAO extends AbstractDAO<Player> {
    @Override
    public Player findById(int id) {
        String sql = "SELECT * FROM players WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Player p = new Player(rs.getString("name"), rs.getInt("country_id"), 
                                     rs.getString("position"), rs.getInt("overall"), 
                                     rs.getBoolean("is_starter"));
                p.setId(rs.getInt("id"));
                return p;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Player> findByCountry(int countryId) {
        List<Player> players = new ArrayList<>();
        String sql = "SELECT * FROM players WHERE country_id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, countryId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Player p = new Player(rs.getString("name"), rs.getInt("country_id"), 
                                     rs.getString("position"), rs.getInt("overall"), 
                                     rs.getBoolean("is_starter"));
                p.setId(rs.getInt("id"));
                players.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return players;
    }

    @Override
    public List<Player> findAll() {
        return null; // Not needed for now
    }

    @Override
    public void save(Player entity) {
    }

    @Override
    public void delete(int id) {
    }
}
