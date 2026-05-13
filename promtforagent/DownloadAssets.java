import java.io.*;
import java.net.*;
import java.net.http.*;
import java.nio.file.*;
import java.util.*;
import org.json.*;

/**
 * FIFA Card Game 2026 — Asset Downloader
 * Запусти ОДИН РАЗ. Скачает фото игроков + флаги в resources/images/
 */
public class DownloadAssets {

    // ✅ ВСТАВЬ СВОЙ КЛЮЧ СЮДА
    private static final String API_KEY = "YOUR_API_KEY_HERE";
    private static final String BASE_URL = "https://v3.football.api-sports.io";

    private static final String PLAYERS_DIR = "src/main/resources/images/players/";
    private static final String FLAGS_DIR   = "src/main/resources/images/flags/";

    private static final Map<String, Integer> TEAMS = new LinkedHashMap<>() {{
        put("kazakhstan",   1095);
        put("argentina",    26);
        put("brazil",       6);
        put("france",       2);
        put("germany",      25);
        put("spain",        9);
        put("england",      10);
        put("portugal",     27);
        put("usa",          2384);
        put("morocco",      31);
        put("netherlands",  1118);
        put("senegal",      13);
        put("mexico",       16);
        put("australia",    20);
        put("south_korea",  1728);
    }};

    private static final int SEASON = 2024;

    public static void main(String[] args) throws Exception {
        System.out.println("=== FIFA Card Game 2026 — Asset Downloader ===\n");

        Files.createDirectories(Paths.get(PLAYERS_DIR));
        Files.createDirectories(Paths.get(FLAGS_DIR));

        HttpClient client = HttpClient.newHttpClient();

        int totalPlayers = 0;
        int totalFlags   = 0;

        for (Map.Entry<String, Integer> entry : TEAMS.entrySet()) {
            String teamName = entry.getKey();
            int    teamId   = entry.getValue();

            System.out.println("📥 Загружаю: " + teamName + " (id=" + teamId + ")");

            boolean flagOk = downloadFlag(client, teamId, teamName);
            if (flagOk) totalFlags++;

            Thread.sleep(6500);

            int downloaded = downloadPlayers(client, teamId, teamName);
            totalPlayers += downloaded;

            System.out.println("   ✅ Флаг: " + (flagOk ? "OK" : "FAIL")
                             + " | Игроков: " + downloaded + "\n");

            Thread.sleep(6500);
        }

        System.out.println("=== ГОТОВО ===");
        System.out.println("Флагов:   " + totalFlags);
        System.out.println("Игроков:  " + totalPlayers);
    }

    private static boolean downloadFlag(HttpClient client, int teamId, String teamName) {
        try {
            String json = apiGet(client, BASE_URL + "/teams?id=" + teamId);
            JSONObject root = new JSONObject(json);
            JSONArray response = root.getJSONArray("response");
            if (response.isEmpty()) return false;

            String flagUrl = response
                .getJSONObject(0)
                .getJSONObject("team")
                .getString("logo");

            downloadFile(flagUrl, FLAGS_DIR + teamName + ".png");
            return true;
        } catch (Exception e) {
            System.out.println("   ⚠️  Флаг ошибка: " + e.getMessage());
            return false;
        }
    }

    private static int downloadPlayers(HttpClient client, int teamId, String teamName) {
        int count = 0;
        try {
            String json = apiGet(client, BASE_URL + "/players?team=" + teamId + "&season=" + SEASON);
            JSONObject root = new JSONObject(json);
            JSONArray response = root.getJSONArray("response");

            int limit = Math.min(18, response.length());

            for (int i = 0; i < limit; i++) {
                JSONObject playerObj = response.getJSONObject(i);
                JSONObject player    = playerObj.getJSONObject("player");

                int    playerId   = player.getInt("id");
                String playerName = player.getString("name")
                                         .replaceAll("[^a-zA-Z0-9_]", "_")
                                         .toLowerCase();
                String photoUrl   = player.getString("photo");

                String filePath = PLAYERS_DIR + teamName + "_" + playerName + "_" + playerId + ".png";

                if (Files.exists(Paths.get(filePath))) {
                    count++;
                    continue;
                }

                downloadFile(photoUrl, filePath);
                count++;
                Thread.sleep(300);
            }
        } catch (Exception e) {
            System.out.println("   ⚠️  Игроки ошибка: " + e.getMessage());
        }
        return count;
    }

    private static String apiGet(HttpClient client, String url) throws Exception {
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(url))
            .header("x-apisports-key", API_KEY)
            .header("Accept", "application/json")
            .GET()
            .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("   [API] Status: " + response.statusCode());
        String remaining = response.headers().firstValue("x-ratelimit-requests-remaining").orElse("?");
        System.out.println("   [API] Запросов осталось: " + remaining);

        if (response.statusCode() != 200) {
            throw new RuntimeException("HTTP " + response.statusCode() + " для " + url);
        }
        return response.body();
    }

    private static void downloadFile(String fileUrl, String savePath) throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(fileUrl))
            .GET()
            .build();

        HttpResponse<byte[]> response = client.send(request, HttpResponse.BodyHandlers.ofByteArray());

        if (response.statusCode() == 200) {
            Files.write(Paths.get(savePath), response.body());
        } else {
            throw new RuntimeException("Не удалось скачать: " + fileUrl);
        }
    }
}
