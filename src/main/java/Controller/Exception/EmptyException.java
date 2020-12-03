package Controller.Exception;

public class EmptyException extends Exception{
    String name ;
    public EmptyException(String name) {
        super(" can not be empty");
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
