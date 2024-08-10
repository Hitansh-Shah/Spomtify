package com.ApiTesting;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class Lyrics {
    static String lyrics;

    static void setLyrics(String llyrics) {
//        lyrics = URLEncoder.encode(llyrics, StandardCharsets.UTF_8);
        String rawString = llyrics.toString();
        ByteBuffer buffer = StandardCharsets.UTF_8.encode(rawString);

        String utf8EncodedString = StandardCharsets.UTF_8.decode(buffer).toString();
        lyrics = utf8EncodedString;
//        System.out.println(lyrics);
    }
    static void getLyrics(String url) throws IOException, InterruptedException, ParseException {
        Object obj = new JSONParser().parse(RouteHandler.req_res(url));
        JSONObject jo = (JSONObject) obj;

        lyrics = (String) jo.get("lyrics");
        System.out.println(lyrics);
    }
    static void printLyrics() {
        System.out.println(lyrics);
        RouteHandler.gap();
    }
}
