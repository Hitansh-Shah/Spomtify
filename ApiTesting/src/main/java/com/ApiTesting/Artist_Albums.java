package com.ApiTesting;

import org.jmusixmatch.entity.track.Track;
import org.jmusixmatch.entity.track.TrackData;

import java.util.List;

public class Artist_Albums {
    static List<Track> tracks;
    static StringBuilder tracks2 = new StringBuilder("");
    static void getArtistAlbums(List<Track> trackss) {
        tracks = trackss;
        for (Track trk : tracks) {
            TrackData trkData = trk.getTrack();

            tracks2.append("Album Name : ").append(trkData.getAlbumName()).append("\n");
            tracks2.append("Artist Name : ").append(trkData.getArtistName()).append("\n");
            tracks2.append("Track Name : ").append(trkData.getTrackName()).append("\n\n");
        }
    }

    static void printArtistAlbums() {
        System.out.println(tracks2.toString());
    }
}
