package com.ApiTesting;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RouteHandler {
   static void gap() {
      System.out.println();
      System.out.println();
      System.out.println();
   }

   static String req_res(String url) throws IOException, InterruptedException {
       HttpClient client = HttpClient.newHttpClient();
       HttpRequest req_album_tracks = HttpRequest.newBuilder()
               .GET()
               .header("accept", "application/json")
               .uri(URI.create(url))
               .build();
       HttpResponse<String> response = client.send(req_album_tracks, HttpResponse.BodyHandlers.ofString());
       return response.body();

   }

}
