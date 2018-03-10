package radio.database;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        List<Song> playlist = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String[] input = reader.readLine().split("[;:]");
            String artist = input[0];
            String song = input[1];
            int minutes = Integer.parseInt(input[2]);
            int seconds = Integer.parseInt(input[3]);
            try {
                if (artist.length() < 3 || artist.length() > 20) {
                    throw new InvalidArtistNameException("Artist name should be between 3 and 20 symbols.");
                } else if (song.length() < 3 || song.length() > 30) {
                    throw new InvalidSongNameException("Song name should be between 3 and 30 symbols.");
                } else if (minutes < 0 || minutes > 14) {
                    throw new InvalidSongMinutesException("Song minutes should be between 0 and 14.");
                } else if (seconds < 0 || seconds > 59) {
                    throw new InvalidSongSecondsException("Song seconds should be between 0 and 59.");
                } else {
                    Song song1 = new Song(artist, song, minutes, seconds);
                    playlist.add(song1);
                    System.out.println("Song added.");
                }
            } catch (Exception e){
                System.out.println(e.getMessage());
            }

        }
        System.out.printf("Songs added: %d%n", playlist.size());
        int totalMinutes = 0;
        int totalSeconds = 0;
        int totalHours = 0;
        for (Song s : playlist) {
            totalMinutes += s.getMinutes();
            totalSeconds += s.getSeconds();
        }
        while (totalSeconds >= 60) {
            totalSeconds -= 60;
            totalMinutes += 1;
        }
        while (totalMinutes >= 60){
            totalMinutes -= 60;
            totalHours += 1;
        }
        System.out.printf("Playlist length: %dh %dm %ds%n", totalHours, totalMinutes, totalSeconds);
    }
}
