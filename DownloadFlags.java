import java.io.*;
import java.net.*;
import java.net.http.*;
import java.nio.file.*;
import java.util.*;

public class DownloadFlags {
    private static final String FLAGS_DIR = "src/main/resources/images/flags/";
    private static final Map<String, String> ISO_CODES = new HashMap<>() {{
        put("kazakhstan", "kz"); put("argentina", "ar"); put("brazil", "br");
        put("france", "fr"); put("germany", "de"); put("spain", "es");
        put("england", "gb-eng"); put("portugal", "pt"); put("netherlands", "nl");
        put("senegal", "sn"); put("mexico", "mx"); put("usa", "us");
        put("morocco", "ma"); put("australia", "au");
    }};

    public static void main(String[] args) throws Exception {
        System.out.println("=== Flag Downloader (FlagCDN) ===\n");
        Files.createDirectories(Paths.get(FLAGS_DIR));
        HttpClient client = HttpClient.newHttpClient();

        for (Map.Entry<String, String> entry : ISO_CODES.entrySet()) {
            String team = entry.getKey();
            String iso = entry.getValue();
            String url = "https://flagcdn.com/w320/" + iso + ".png";
            String path = FLAGS_DIR + team + ".png";

            System.out.print("📥 Downloading flag for " + team + "... ");
            try {
                HttpRequest req = HttpRequest.newBuilder().uri(URI.create(url)).GET().build();
                HttpResponse<byte[]> resp = client.send(req, HttpResponse.BodyHandlers.ofByteArray());
                if (resp.statusCode() == 200) {
                    Files.write(Paths.get(path), resp.body());
                    System.out.println("✅ DONE");
                } else {
                    System.out.println("❌ HTTP " + resp.statusCode());
                }
            } catch (Exception e) {
                System.out.println("⚠️ Error: " + e.getMessage());
            }
        }
        System.out.println("\n=== FLAGS DOWNLOADED ===");
    }
}
