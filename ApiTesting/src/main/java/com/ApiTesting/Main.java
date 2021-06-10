package com.ApiTesting;

import org.jmusixmatch.MusixMatch;
import org.jmusixmatch.MusixMatchException;
import org.jmusixmatch.entity.track.Track;
import org.jmusixmatch.entity.track.TrackData;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main {
    private static final String apiKey = "1bee7e5dd531afab391df72725d2b8ca";
    static  String trackName;
    static String artistName;
    static String albumName;
    static String trackID;
    static String albumID;
    static String artistID;
    public static void main() throws MusixMatchException, IOException, InterruptedException, ParseException {
            
        String baseUrl = "https://api.musixmatch.com/ws/1.1/";
        MusixMatch musixMatch = new MusixMatch(apiKey);
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        HistoryTracker.updateHistory(artistName, trackName, dateFormat.format(date));
        HistoryTracker.getHistory();

        Track track = musixMatch.getMatchingTrack(trackName, artistName);
        TrackData data = track.getTrack();

        trackID = data.getTrackId().toString();
        albumID = data.getAlbumId().toString();
        albumName = data.getAlbumName();
        artistID = data.getArtistId().toString();

        String album_tracks_url = baseUrl + "album.tracks.get?album_id="+ URLEncoder.encode(albumID, StandardCharsets.UTF_8) + "&apikey=" + URLEncoder.encode(apiKey, StandardCharsets.UTF_8);
        String lyrics_url = "https://api.lyrics.ovh/v1/" + URLEncoder.encode(artistName, StandardCharsets.UTF_8) + "/" + URLEncoder.encode(trackName, StandardCharsets.UTF_8);

        Lyrics.getLyrics(lyrics_url);


        Album_Tracks.getAlbumTracks(album_tracks_url);

        List<Track> tracks = musixMatch.searchTracks("", artistName, "", 10, 10, true);
        Artist_Albums.getArtistAlbums(tracks);

    }
}
