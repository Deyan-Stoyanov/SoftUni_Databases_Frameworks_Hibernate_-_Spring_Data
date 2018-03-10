package radio.database;

public class InvalidSongExeption extends RuntimeException{
    private String message;

    public InvalidSongExeption(String message) {
        super(message);
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
