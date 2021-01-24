package Server.Controller.Exception.Plato;

public class InvalidUserNameException extends Exception {
    String userName;
    public InvalidUserNameException(String userName) {
        super(" is invalid username");
        this.userName=userName;
    }

    public String getUserName() {
        return userName;
    }
}
