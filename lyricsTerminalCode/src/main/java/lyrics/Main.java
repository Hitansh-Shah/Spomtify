package lyrics;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException, ParseException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Name of artist");
        String artist = sc.nextLine();
        System.out.println("Name of song");
        String song = sc.nextLine();

        String MY_URI = "https://api.lyrics.ovh/v1/" + URLEncoder.encode(artist, StandardCharsets.UTF_8) + "/" + URLEncoder.encode(song, StandardCharsets.UTF_8);

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .header("accept", "application/json")
                .uri(URI.create(MY_URI))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        Object obj = new JSONParser().parse(response.body());

        JSONObject jo = (JSONObject) obj;

        String lyrics = (String) jo.get("lyrics");



        System.out.println(lyrics);

    }
}
