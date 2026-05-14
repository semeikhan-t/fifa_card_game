package com.fifa.dao;

import com.fifa.model.Team;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TeamDAO extends AbstractDAO<Team> {
    @Override
    public Team findById(int id) {
        String sql = "SELECT * FROM countries WHERE id = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Team team = new Team(rs.getString("name"), rs.getInt("ovr_attack"), rs.getInt("ovr_defense"));
                team.setId(rs.getInt("id"));
                team.setCode(rs.getString("code"));
                return team;
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
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Team team = new Team(rs.getString("name"), rs.getInt("ovr_attack"), rs.getInt("ovr_defense"));
                team.setId(rs.getInt("id"));
                team.setCode(rs.getString("code"));
                teams.add(team);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return teams;
    }

    @Override
    public void save(Team entity) {
        
    }

    @Override
    public void delete(int id) {
        
    }
}