package com.ApiTesting;

import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
//import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class HistoryTracker {
    static String[] history;
    static StringBuilder history2;
    static void updateHistory(String artistName, String trackName, String date) throws IOException, ParseException {
        Object obj = new JSONParser().parse(new FileReader("history.json"));
        JSONArray ja = (JSONArray) obj;
        Map m = new LinkedHashMap(3);
        m.put("Artist Name", artistName);
        m.put("Track Name", trackName);
        m.put("Date", date);
        ja.add(m);
        history = new String[ja.size()];
        FileWriter myWriter = new FileWriter("history.json");
        myWriter.write(ja.toJSONString());
        myWriter.close();
    }
    static void readHistory() throws IOException, ParseException {
        for(String hist : history) {
            System.out.println(hist);
            System.out.println();
        }
    }

    static void getHistory() throws FileNotFoundException, IOException, ParseException {
        history2 = new StringBuilder("");
        Object obj = new JSONParser().parse(new FileReader("history.json"));
        JSONArray ja = (JSONArray) obj;
        history = new String[ja.size()];
        int count = 0;
        int count2 = 0;

        for (Object o : ja) {
            StringBuilder temp = new StringBuilder("");
            for (Map.Entry pair : (Iterable<Map.Entry>) ((Map) o).entrySet()) {
                count++;
                if(count == 3) {
                    temp.append(pair.getKey()).append(" : ").append(pair.getValue().toString());
                    count = 0;
                } else {
                    temp.append(pair.getKey()).append(" : ").append(pair.getValue().toString()).append("\n");
                }
            }

            history[history.length - count2 -1] = temp.toString();
            count2++;
        }
        for(String hist : history) {
            history2.append(hist).append("\n\n");
        }
    }
    
    static String giveInfo(int index, String type) throws IOException, ParseException {
        Object obj = new JSONParser().parse(new FileReader("history.json"));
        JSONArray ja = (JSONArray) obj;
        Object obj2 = ja.get(index);
        for (Map.Entry pair : (Iterable<Map.Entry>) ((Map) obj2).entrySet()) {
            if(pair.getKey().equals(type)) {
                return pair.getValue().toString();
            }
        }

        return null;

    }


    static void clearHistory() throws IOException {
        JSONArray ja = new JSONArray();
        FileWriter myWriter = new FileWriter("history.json");
        myWriter.write(ja.toJSONString());
        myWriter.close();
        history2 = new StringBuilder("");
    }
    
    static int giveSizeOfHistory() {
        return history.length;
    }

}
