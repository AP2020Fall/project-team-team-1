package Client.OldView;

import Server.Controller.Exception.Plato.ExistSuggestionException;

import java.util.HashMap;

public class ViewAdminSuggestion extends Menu {
    private String username;
    public ViewAdminSuggestion(String username, Menu parentMenu) {
        super("Admin Suggestion", parentMenu);
        this.username=username;
        HashMap<Integer,Menu> submenus = new HashMap<>();
        submenus.put(1,chooseSuggestion());
        this.setSubmenus(submenus);
    }



    private Menu chooseSuggestion(){
        return new Menu("Choose Suggestion",this) {

            @Override
            public void execute() {
                try {
                    String[] showEvent =  playerGeneralController.showSuggestion(username).split("\\$");
                    for (String out : showEvent) {
                        System.out.println(out);
                    }
                    System.out.println("Enter To continue Or Enter Back! ");
                    String nextCommand = scanner.nextLine();
                    if (!nextCommand.equalsIgnoreCase("back")){
                        String suggestionID = scanner.nextLine();
                        if (playerGeneralController.suggestionGameName(suggestionID).startsWith("D")||playerGeneralController.suggestionGameName(suggestionID).startsWith("d")){
                            new DotsAndBoxesMenu(username,this.parentMenu.parentMenu).run();
                        }else if (playerGeneralController.suggestionGameName(suggestionID).startsWith("b")||playerGeneralController.suggestionGameName(suggestionID).startsWith("B")){
                            new BattleShipMenu(username,this.parentMenu.parentMenu).run();
                        }
                    }else this.parentMenu.run();

//                    playerGeneralController.playSuggestedGame(username,chosenSuggestion);
                } catch (ExistSuggestionException e) {
                    System.out.println(e.getMessage());
                    this.parentMenu.run();
                }

            }
        };
    }
}
