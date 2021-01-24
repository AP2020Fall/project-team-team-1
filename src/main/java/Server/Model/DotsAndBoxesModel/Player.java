package Server.Model.DotsAndBoxesModel;

public enum Player {
    RED("R"),
    BLUE("B"),
    NONE("N");
    private String label;

    Player(String label){
        this.label=label;
    }

    public String getLabel() {
        return label;
    }
}
