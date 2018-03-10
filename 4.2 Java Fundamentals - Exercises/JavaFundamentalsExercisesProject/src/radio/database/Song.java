package radio.database;

public class Song {
    private String artistName;
    private String songName;
    private int minutes;
    private int seconds;

    Song(String artistName, String songName, int minutes, int seconds) {
        this.artistName = artistName;
        this.songName = songName;
        this.minutes = minutes;
        this.seconds = seconds;
    }

    public Song() {
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }
}
