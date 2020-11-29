package View;

import java.util.HashMap;

public class ViewAdminSuggestion extends Menu {
    private String username;
    public ViewAdminSuggestion(String username, Menu parentMenu) {
        super("Admin Suggestion", parentMenu);
        this.username=username;
        HashMap<Integer,Menu> submenus = new HashMap<>();
        submenus.put(1,chooseSuggestion());
    }
    private Menu chooseSuggestion(){
        return new Menu("Choose Suggestion",this) {
            @Override
            public void execute() {
                playerGeneralController.showSuggestion(username);
                String chosenSuggestion = scanner.nextLine();
                playerGeneralController.playSuggestedGame(username,chosenSuggestion);
            }
        };
    }
}
