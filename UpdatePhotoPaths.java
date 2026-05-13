import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;
import com.fifa.util.DatabaseManager;

public class UpdatePhotoPaths {
    
    static class Match {
        int playerId;
        int score;
        String playerName;
        Match(int id, int s, String name) { this.playerId = id; this.score = s; this.playerName = name; }
    }

    public static void main(String[] args) {
        try {
            Connection conn = DatabaseManager.getConnection();
            
            Map<Integer, String> countryMap = new HashMap<>();
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery("SELECT id, code FROM countries")) {
                while (rs.next()) {
                    countryMap.put(rs.getInt("id"), rs.getString("code"));
                }
            }
            
            List<PlayerInfo> players = new ArrayList<>();
            try (Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery("SELECT id, name, country_id FROM players")) {
                while (rs.next()) {
                    players.add(new PlayerInfo(rs.getInt("id"), rs.getString("name"), rs.getInt("country_id")));
                }
            }
            
            File playersDir = new File("src/main/resources/images/players/");
            File[] files = playersDir.listFiles();
            if (files == null) return;
            
            Map<String, Match> fileToPlayer = new HashMap<>();
            
            for (PlayerInfo p : players) {
                String countryCode = countryMap.get(p.countryId);
                if (countryCode == null) continue;
                
                String dbName = p.name.toLowerCase();
                String cleanDbName = clean(dbName);
                String superCleanDbName = cleanDbName.replace(" ", "");
                
                for (File file : files) {
                    String fileName = file.getName().toLowerCase();
                    if (!fileName.startsWith(countryCode + "_")) continue;
                    
                    String namePart = fileName.substring(countryCode.length() + 1, fileName.lastIndexOf("_"));
                    String cleanFile = clean(namePart);
                    String superCleanFile = cleanFile.replace(" ", "");
                    
                    int score = 0;
                    
                    // Rule 1: Super strict match
                    if (superCleanDbName.equals(superCleanFile)) {
                        score = 300;
                    } else {
                        // Rule 2: Word-based matching
                        String[] dbWords = cleanDbName.split(" ");
                        String[] fileWords = cleanFile.split(" ");
                        String dbLast = dbWords[dbWords.length - 1];
                        
                        boolean lastMatch = false;
                        for (String fw : fileWords) {
                            if (fw.equals(dbLast) || (dbLast.length() > 3 && fw.contains(dbLast)) || (fw.length() > 3 && dbLast.contains(fw))) {
                                lastMatch = true;
                                break;
                            }
                        }
                        
                        if (lastMatch) {
                            score += 100;
                            // Add points for first name / initial
                            if (dbWords[0].charAt(0) == fileWords[0].charAt(0)) {
                                score += 50;
                            }
                            if (dbWords[0].equals(fileWords[0])) {
                                score += 50;
                            }
                        }
                    }
                    
                    if (score >= 100) {
                        Match currentMatch = fileToPlayer.get(fileName);
                        if (currentMatch == null || score > currentMatch.score) {
                            fileToPlayer.put(fileName, new Match(p.id, score, p.name));
                        }
                    }
                }
            }
            
            Map<Integer, String> playerToBestFile = new HashMap<>();
            Map<Integer, Integer> playerBestScore = new HashMap<>();
            for (Map.Entry<String, Match> entry : fileToPlayer.entrySet()) {
                String fileName = entry.getKey();
                Match m = entry.getValue();
                if (!playerBestScore.containsKey(m.playerId) || m.score > playerBestScore.get(m.playerId)) {
                    playerToBestFile.put(m.playerId, fileName);
                    playerBestScore.put(m.playerId, m.score);
                }
            }

            try (Statement stmt = conn.createStatement()) {
                stmt.executeUpdate("UPDATE players SET photo_path = NULL");
            }
            
            PreparedStatement updateStmt = conn.prepareStatement("UPDATE players SET photo_path = ? WHERE id = ?");
            int total = 0;
            for (Map.Entry<Integer, String> entry : playerToBestFile.entrySet()) {
                updateStmt.setString(1, entry.getValue());
                updateStmt.setInt(2, entry.getKey());
                updateStmt.executeUpdate();
                total++;
            }
            
            System.out.println("🚀 Success! Perfectly matched " + total + " players.");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private static String clean(String name) {
        if (name == null) return "";
        return name.toLowerCase()
            .replace("á", "a").replace("é", "e").replace("í", "i").replace("ó", "o").replace("ú", "u")
            .replace("ñ", "n").replace("ć", "c").replace("š", "s").replace("ž", "z")
            .replace("ü", "u").replace("ö", "o").replace("ä", "a")
            .replace("y", "i").replace("j", "i")
            .replaceAll("[^a-z0-9]", " ")
            .replaceAll("\\s+", " ")
            .trim();
    }

    static class PlayerInfo {
        int id; String name; int countryId;
        PlayerInfo(int id, String name, int c) { this.id = id; this.name = name; this.countryId = c; }
    }
}
