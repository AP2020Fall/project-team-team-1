package Server.Controller.Exception.Plato;

public class ExistFavoriteException extends Exception{
    String gameName;

    public ExistFavoriteException(String message, String gameName) {
        super(message);
        this.gameName = gameName;
    }

    public ExistFavoriteException(String message) {
        super(message);
    }

    public String getGameName() {
        return gameName;
    }
}
