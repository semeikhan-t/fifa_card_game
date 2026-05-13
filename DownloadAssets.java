import java.io.*;
import java.net.*;
import java.net.http.*;
import java.nio.file.*;
import java.util.*;
import org.json.*;

public class DownloadAssets {

    private static final String API_KEY  = "ufx4wGBr6sC6t48tBk9izdQQISrTQCO4M9vwR2KEkAhgKDt8KEhQIdSkb9iQ";
    private static final String BASE_URL = "https://api.sportmonks.com/v3/football";

    private static final String PLAYERS_DIR = "src/main/resources/images/players/";
    private static final String FLAGS_DIR   = "src/main/resources/images/flags/";

    private static final Map<String, Integer> TEAMS = new LinkedHashMap<>() {{
        put("kazakhstan",  18603);
        put("argentina",   18644);
        put("brazil",      18704);
        put("france",      18647);
        put("germany",     18660);
        put("spain",       18710);
        put("england",     18645);
        put("portugal",    18701);
        put("netherlands", 18694);
        put("senegal",     18558);
        put("mexico",      18576);
        put("usa",         18571);
        put("morocco",     18551);
        put("australia",   18730);
    }};

    private static HttpClient HTTP;
    private static PrintWriter CSV_WRITER;

    public static void main(String[] args) throws Exception {
        System.out.println("=== Sportmonks v3 Asset Downloader ===\n");
        Files.createDirectories(Paths.get(PLAYERS_DIR));
        Files.createDirectories(Paths.get(FLAGS_DIR));
        HTTP = HttpClient.newHttpClient();
        
        CSV_WRITER = new PrintWriter(new FileWriter("downloaded_squads.csv"));
        CSV_WRITER.println("country,name,position,overall,photo");

        for (Map.Entry<String, Integer> entry : TEAMS.entrySet()) {
            String teamCode = entry.getKey();
            int teamId = entry.getValue();

            System.out.println("📥 " + teamCode + " (id=" + teamId + ")");

            downloadFlag(teamId, teamCode);
            int downloaded = downloadSquad(teamId, teamCode);
            System.out.println("   ✅ Игроков скачано: " + downloaded + "\n");
        }

        CSV_WRITER.close();
        System.out.println("=== ГОТОВО. Данные сохранены в downloaded_squads.csv ===");
    }

    private static final Map<String, String> ISO_CODES = new HashMap<>() {{
        put("kazakhstan", "kz"); put("argentina", "ar"); put("brazil", "br");
        put("france", "fr"); put("germany", "de"); put("spain", "es");
        put("england", "gb-eng"); put("portugal", "pt"); put("netherlands", "nl");
        put("senegal", "sn"); put("mexico", "mx"); put("usa", "us");
        put("morocco", "ma"); put("australia", "au");
    }};

    private static void downloadFlag(int teamId, String teamCode) {
        try {
            // План А: Sportmonks
            String json = apiGet(BASE_URL + "/teams/" + teamId + "?api_token=" + API_KEY);
            JSONObject team = new JSONObject(json).getJSONObject("data");
            String imgUrl = team.optString("image_path", "");
            
            if (!imgUrl.isEmpty() && !imgUrl.contains("placeholder")) {
                downloadFile(imgUrl, FLAGS_DIR + teamCode + ".png");
                System.out.println("   🏳 Flag: From Sportmonks");
                return;
            }
            
            // План Б: FlagCDN
            String iso = ISO_CODES.get(teamCode);
            if (iso != null) {
                String flagUrl = "https://flagcdn.com/w320/" + iso + ".png";
                downloadFile(flagUrl, FLAGS_DIR + teamCode + ".png");
                System.out.println("   🏳 Flag: From FlagCDN (" + iso + ")");
            }
        } catch (Exception e) {
            System.err.println("   ⚠️ Flag error: " + e.getMessage());
        }
    }

    private static int downloadSquad(int teamId, String teamCode) {
        int count = 0;
        int page  = 1;
        try {
            while (true) {
                String url  = BASE_URL + "/squads/teams/" + teamId
                        + "?api_token=" + API_KEY
                        + "&include=player"
                        + "&page=" + page + "&per_page=50";
                String json = apiGet(url);
                JSONObject root = new JSONObject(json);
                JSONArray data  = root.optJSONArray("data");

                if (data == null || data.isEmpty()) break;

                for (int i = 0; i < data.length(); i++) {
                    JSONObject entry  = data.getJSONObject(i);
                    JSONObject player = entry.optJSONObject("player");
                    if (player == null) continue;

                    int    playerId   = player.getInt("id");
                    String fullName   = player.optString("display_name", player.optString("name", "unknown"));
                    String safeName   = fullName.replaceAll("[^a-zA-Z0-9_ ]", "").replace(" ", "_").toLowerCase();
                    String imgUrl     = player.optString("image_path", "");
                    
                    if (imgUrl.isEmpty() || imgUrl.contains("placeholder")) continue;

                    String fileName = teamCode + "_" + safeName + "_" + playerId + ".png";
                    String filePath = PLAYERS_DIR + fileName;
                    
                    if (!Files.exists(Paths.get(filePath))) {
                        downloadFile(imgUrl, filePath);
                        Thread.sleep(100);
                    }

                    String pos = mapPosition(player.optInt("position_id", 0));
                    int rating = 75 + (int)(Math.random() * 15); // Default rating
                    
                    CSV_WRITER.println(teamCode + "," + fullName + "," + pos + "," + rating + "," + fileName);
                    count++;
                }

                JSONObject meta = root.optJSONObject("pagination");
                if (meta == null || !meta.optBoolean("has_more", false)) break;
                page++;
                Thread.sleep(300);
            }
        } catch (Exception e) {
            System.err.println("   ⚠️ Squad error: " + e.getMessage());
        }
        return count;
    }

    private static String mapPosition(int id) {
        if (id == 24) return "GK";
        if (id == 25) return "DEF";
        if (id == 26) return "MID";
        if (id == 27) return "FWD";
        return "MID";
    }

    private static String apiGet(String url) throws Exception {
        HttpRequest req = HttpRequest.newBuilder().uri(URI.create(url)).header("Accept", "application/json").GET().build();
        HttpResponse<String> resp = HTTP.send(req, HttpResponse.BodyHandlers.ofString());
        if (resp.statusCode() == 429) {
            Thread.sleep(60000);
            return apiGet(url);
        }
        return resp.body();
    }

    private static void downloadFile(String fileUrl, String savePath) throws Exception {
        HttpRequest req = HttpRequest.newBuilder().uri(URI.create(fileUrl)).GET().build();
        HttpResponse<byte[]> resp = HTTP.send(req, HttpResponse.BodyHandlers.ofByteArray());
        if (resp.statusCode() == 200) Files.write(Paths.get(savePath), resp.body());
    }
}
