package Server.Controller.Exception.Plato;

public class AcceptAndDeclineFriendException extends Exception {
    String playerHowSentRequests;

    public AcceptAndDeclineFriendException(String message, String playerHowSentRequests) {
        super(message);
        this.playerHowSentRequests = playerHowSentRequests;
    }


    public String getPlayerHowSentRequests() {
        return playerHowSentRequests;
    }
}
