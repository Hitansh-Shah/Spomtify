package com.ApiTesting;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;

public class Lyrics {
    static String lyrics;
    static void getLyrics(String url) throws IOException, InterruptedException, ParseException {
        Object obj = new JSONParser().parse(RouteHandler.req_res(url));
        JSONObject jo = (JSONObject) obj;

        lyrics = (String) jo.get("lyrics");
    }
    static void printLyrics() {
        System.out.println(lyrics);
        RouteHandler.gap();
    }
}
