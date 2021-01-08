package Controller.RegisterController;


        import Controller.CompetencyController.Existence;
        import Controller.Exception.Plato.ExistFriendException;
        import Controller.Exception.Plato.ExistPlayerException;
        import Controller.Exception.Plato.InvalidUserNameException;
        import Controller.Exception.Plato.WrongPasswordException;
        import Controller.PlayerController.FindPlayerByInfo;
        import Controller.PlayerController.PlayerGeneralController;
        import Model.DataBase.DataBase;
        import Model.PlatoModel.Player;
        import Model.PlatoModel.User;

        import java.io.File;
        import java.io.IOException;
        import java.util.ArrayList;

public class Delete {
    private static final File userFile = new File("src\\main\\java\\Model\\Database\\User.json");
    private static final File playerFile = new File("src\\main\\java\\Model\\Database\\Player.json");
    private static PlayerGeneralController playerGeneralController = new PlayerGeneralController();


    public void deleteUser(String input) throws InvalidUserNameException, WrongPasswordException, IOException {
        String[] inputSplit = input.split("\\s");

        if (!(Existence.checkUserNameExistence(inputSplit[0]))) {
            throw new InvalidUserNameException(inputSplit[0]);
        }

        if (!(Existence.checkPassword(inputSplit[0], inputSplit[1]))) {
            throw new WrongPasswordException();
        }

        Player player = FindPlayerByInfo.findByUserName(inputSplit[0]);

        ArrayList<String> forDelete = new ArrayList<>();
        for (String friend : player.getFriends()) {
            forDelete.add(friend);
        }
        for (String s : forDelete) {
            try {
                playerGeneralController.removeFriend(player.getUserName(),s);
            } catch (ExistFriendException e) {
                e.printStackTrace();
            } catch (ExistPlayerException e) {
                e.printStackTrace();
            }
        }
        Player.players.remove(player);
        User.users.remove(player);
        DataBase.save(Player.players,playerFile);
        DataBase.save(User.users,userFile);

//        User.saveInJsonFile();
//        Player.saveInJsonFile();
    }
}
