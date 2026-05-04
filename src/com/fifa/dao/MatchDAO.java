package com.fifa.dao;

import com.fifa.model.MatchResult;
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
        List<MatchResult> results = new ArrayList<>();
        String sql = "SELECT m.*, c1.name as home_name, c2.name as away_name " +
                     "FROM matches m " +
                     "JOIN countries c1 ON m.home_id = c1.id " +
                     "JOIN countries c2 ON m.away_id = c2.id " +
                     "ORDER BY m.played_at DESC";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                MatchResult r = new MatchResult();
                r.setId(rs.getInt("id"));
                r.setHomeId(rs.getInt("home_id"));
                r.setAwayId(rs.getInt("away_id"));
                r.setHomeScore(rs.getInt("home_score"));
                r.setAwayScore(rs.getInt("away_score"));
                r.setPlayedAt(rs.getTimestamp("played_at").toLocalDateTime());
                r.setHomeName(rs.getString("home_name"));
                r.setAwayName(rs.getString("away_name"));
                results.add(r);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }

    @Override
    public void save(MatchResult entity) {
        String sql = "INSERT INTO matches (home_id, away_id, home_score, away_score) VALUES (?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, entity.getHomeId());
            stmt.setInt(2, entity.getAwayId());
            stmt.setInt(3, entity.getHomeScore());
            stmt.setInt(4, entity.getAwayScore());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {}
}
