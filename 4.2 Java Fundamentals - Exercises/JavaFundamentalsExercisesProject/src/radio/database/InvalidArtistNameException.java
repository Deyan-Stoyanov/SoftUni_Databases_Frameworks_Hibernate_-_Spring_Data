package radio.database;

public class InvalidArtistNameException extends InvalidSongExeption {
    public InvalidArtistNameException(String message){
        super(message);
    }
}
