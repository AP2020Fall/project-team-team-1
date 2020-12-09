package Controller.Exception.Plato;

public class ExistFriendException extends Exception {
    String name;

    public ExistFriendException(String message, String name) {
        super(message);
        this.name = name;
    }

    public ExistFriendException(String message) {
        super(message);
    }

    public String getName() {
        return name;
    }
}
