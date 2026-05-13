import java.io.*;
import java.nio.file.*;
import java.util.*;

public class GenerateSeedAndCleanup {

    private static final String CSV_INPUT = "downloaded_squads.csv";
    private static final String SEED_SQL = "src/main/resources/db/seed.sql";

    public static void main(String[] args) throws Exception {
        System.out.println("=== Database Seeder (From Downloaded Assets) ===\n");

        if (!Files.exists(Paths.get(CSV_INPUT))) {
            System.err.println("❌ Ошибка: Файл " + CSV_INPUT + " не найден. Сначала запустите DownloadAssets.");
            return;
        }

        Map<String, List<Player>> teams = new LinkedHashMap<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(CSV_INPUT))) {
            String line = br.readLine(); // Header
            while ((line = br.readLine()) != null) {
                String[] p = line.split(",");
                if (p.length < 5) continue;
                
                String country = p[0];
                Player player = new Player(p[1], p[2], Integer.parseInt(p[3]), p[4]);
                
                teams.computeIfAbsent(country, k -> new ArrayList<>()).add(player);
            }
        }

        StringBuilder sql = new StringBuilder();
        sql.append("-- Auto-generated seed.sql from Downloaded Assets\n");
        sql.append("DELETE FROM matches;\nDELETE FROM players;\nDELETE FROM countries;\n\n");

        // 1. Countries
        sql.append("-- 1. COUNTRIES\n");
        sql.append("INSERT INTO countries (id, name, code, flag_path, ovr_attack, ovr_defense) VALUES \n");
        
        List<String> countryNames = new ArrayList<>(teams.keySet());
        for (int i = 0; i < countryNames.size(); i++) {
            String name = countryNames.get(i);
            String display = name.substring(0,1).toUpperCase() + name.substring(1);
            sql.append(String.format("(%d, '%s', '%s', '%s', %d, %d)%s\n", 
                i + 1, display, name, name + ".png", 80, 80, (i == countryNames.size() - 1 ? ";" : ",")));
        }
        sql.append("\n");

        // 2. Players
        sql.append("-- 2. PLAYERS\n");
        sql.append("INSERT INTO players (name, country_id, position, overall, is_starter, photo_path) VALUES\n");

        for (int i = 0; i < countryNames.size(); i++) {
            String country = countryNames.get(i);
            List<Player> squad = teams.get(country);
            
            // Сортируем по рейтингу и берем лучших (или всех)
            squad.sort((a, b) -> b.overall - a.overall);
            
            for (int j = 0; j < squad.size(); j++) {
                Player p = squad.get(j);
                boolean isStarter = (j < 11); // Первые 11 - стартовый состав
                
                sql.append(String.format("('%s', %d, '%s', %d, %s, '%s')%s\n",
                    p.name.replace("'", "''"), i + 1, p.position, p.overall, isStarter, p.photo,
                    (i == countryNames.size() - 1 && j == squad.size() - 1 ? ";" : ",")));
            }
        }

        Files.writeString(Paths.get(SEED_SQL), sql.toString());
        Files.copy(Paths.get(SEED_SQL), Paths.get("resources/db/seed.sql"), StandardCopyOption.REPLACE_EXISTING);
        
        System.out.println("✅ Готово! seed.sql создан.");
        System.out.println("Всего стран: " + countryNames.size());
        System.out.println("Всего игроков: " + teams.values().stream().mapToInt(List::size).sum());
    }

    static class Player {
        String name, position, photo;
        int overall;
        Player(String n, String p, int o, String f) {
            this.name = n; this.position = p; this.overall = o; this.photo = f;
        }
    }
}
