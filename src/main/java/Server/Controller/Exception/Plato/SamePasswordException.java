package Server.Controller.Exception.Plato;

public class SamePasswordException extends Exception{
    public SamePasswordException(String message) {
        super(message);
    }
}