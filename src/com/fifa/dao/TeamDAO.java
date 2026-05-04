package com.fifa.dao;

import com.fifa.model.Team;
import com.fifa.util.DatabaseManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TeamDAO extends AbstractDAO<Team> {

    @Override
    public Team findById(int id) {
        String sql = "SELECT * FROM countries WHERE id = ?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return mapRow(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Team> findAll() {
        List<Team> teams = new ArrayList<>();
        String sql = "SELECT * FROM countries";
        try (Connection conn = DatabaseManager.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                teams.add(mapRow(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return teams;
    }

    @Override
    public void save(Team entity) {
        // Not required for countries in this project (read-only mostly)
    }

    private Team mapRow(ResultSet rs) throws SQLException {
        Team team = new Team();
        team.setId(rs.getInt("id"));
        team.setName(rs.getString("name"));
        team.setOvrAttack(rs.getInt("ovr_attack"));
        team.setOvrDefense(rs.getInt("ovr_defense"));
        return team;
    }
}
