package Controller.Exception.Plato;

public class InvalidGameNameException extends Exception{
    String gameName;

    public InvalidGameNameException(String gameName) {
        super(" Is Invalid Game Name");
        this.gameName = gameName;
    }

    public String getGameName() {
        return gameName;
    }
}
