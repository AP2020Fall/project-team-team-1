package Controller.RegisterController;


        import Controller.CompetencyController.Existence;
        import Controller.Exception.InvalidUserNameException;
        import Controller.Exception.WrongPasswordException;
        import Controller.PlayerController.FindPlayerByInfo;
        import Model.DataBase.DataBase;
        import Model.PlatoModel.Player;
        import Model.PlatoModel.User;

        import java.io.File;
        import java.io.IOException;

public class Delete {
    private static final File userFile = new File("src\\main\\java\\Model\\Database\\User.json");
    private static final File playerFile = new File("src\\main\\java\\Model\\Database\\Player.json");

    public void deleteUser(String input) throws InvalidUserNameException, WrongPasswordException, IOException {
        String[] inputSplit = input.split("\\s");

        if (!(Existence.checkUserNameExistence(inputSplit[0]))) {
            throw new InvalidUserNameException(inputSplit[0]);
        }

        if (!(Existence.checkPassword(inputSplit[0], inputSplit[1]))) {
            throw new WrongPasswordException();
        }

        Player player = FindPlayerByInfo.findByUserName(inputSplit[0]);
        Player.players.remove(player);
        User.users.remove(player);
        DataBase.save(Player.players,playerFile);
        DataBase.save(User.users,userFile);

//        User.saveInJsonFile();
//        Player.saveInJsonFile();
    }
}
