package com.ApiTesting;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
//import java.util.Arrays;
import java.util.Map;

public class Album_Tracks {
    static StringBuilder album_tracks;
    static void getAlbumTracks(String url) throws IOException, InterruptedException, ParseException {
        album_tracks = new StringBuilder("");
        String res_album_tracks = RouteHandler.req_res(url);

        Object obj = new JSONParser().parse(res_album_tracks);

        JSONObject jo = (JSONObject) obj;
        Map message = ((Map)jo.get("message"));
        Map body = null;
        for (Map.Entry pair : (Iterable<Map.Entry>) message.entrySet()) {
            if(pair.getKey().equals("body")) {
                body = (Map) pair.getValue();
                break;
            }
        }
        JSONArray ja = (JSONArray) body.get("track_list");

        for (Object o : ja) {
            for (Map.Entry pair : (Iterable<Map.Entry>) ((Map) o).entrySet()) {
                Map trk = (Map) pair.getValue();
                boolean track_printed = false;
                boolean artist_printed = false;
                for (Map.Entry trk_value : (Iterable<Map.Entry>) ((Map) trk).entrySet()) {

                    if(trk_value.getKey().equals("track_name")) {
                        album_tracks.append(trk_value.getKey()).append(" : ").append(trk_value.getValue().toString()).append("\n\n");

                        track_printed = true;

                    } else if (trk_value.getKey().equals("artist_name")) {
                        album_tracks.append(trk_value.getKey()).append(" : ").append(trk_value.getValue().toString()).append("\n");
                        artist_printed = true;
                    }
                    if(track_printed && artist_printed) {
                        break;
                    }
                }
                break;
            }
        }
    }
    static void printAlbumTracks() {
        System.out.println(album_tracks.toString());
        RouteHandler.gap();
    }
}
