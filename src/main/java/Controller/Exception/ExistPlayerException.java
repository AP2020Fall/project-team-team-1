package Controller.Exception;

public class ExistPlayerException extends Exception{
    String playerName;

    public ExistPlayerException(String message, String playerName) {
        super(message);
        this.playerName = playerName;
    }

    public ExistPlayerException(String message) {
        super(message);
    }

    public String getPlayerName() {
        return playerName;
    }
}
