package View;

public class RunDotsAndBoxes extends Menu {
    private String Username1;
    private String Username2;
    public RunDotsAndBoxes(String username1,String username2, Menu parentMenu) {
        super("DotsAndBoxes", parentMenu);
        this.Username1=username1;
        this.Username2=username2;
    }
}
