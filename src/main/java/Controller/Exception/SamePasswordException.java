package Controller.Exception;

public class SamePasswordException extends Exception{
    public SamePasswordException(String message) {
        super(message);
    }
}
