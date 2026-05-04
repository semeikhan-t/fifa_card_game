package com.fifa.dao;

import com.fifa.model.MatchResult;
import com.fifa.util.DatabaseManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MatchDAO extends AbstractDAO<MatchResult> {

    @Override
    public MatchResult findById(int id) {
        return null;
    }

    @Override
    public List<MatchResult> findAll() {
        List<MatchResult> matches = new ArrayList<>();
        String sql = "SELECT * FROM matches ORDER BY played_at DESC";
        try (Connection conn = DatabaseManager.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                MatchResult m = new MatchResult();
                m.setId(rs.getInt("id"));
                m.setHomeId(rs.getInt("home_id"));
                m.setAwayId(rs.getInt("away_id"));
                m.setHomeScore(rs.getInt("home_score"));
                m.setAwayScore(rs.getInt("away_score"));
                m.setPlayedAt(rs.getTimestamp("played_at").toLocalDateTime());
                matches.add(m);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return matches;
    }

    @Override
    public void save(MatchResult entity) {
        String sql = "INSERT INTO matches (home_id, away_id, home_score, away_score) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setInt(1, entity.getHomeId());
            stmt.setInt(2, entity.getAwayId());
            stmt.setInt(3, entity.getHomeScore());
            stmt.setInt(4, entity.getAwayScore());
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                entity.setId(rs.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
