package Controller.RegisterController;


        import Controller.CompetencyController.Existence;
        import Controller.Exception.InvalidUserNameException;
        import Controller.Exception.WrongPasswordException;
        import Controller.PlayerController.FindPlayerByInfo;
        import Model.PlatoModel.Player;
        import Model.PlatoModel.User;

public class Delete {
    public void deleteUser(String input) throws InvalidUserNameException, WrongPasswordException {
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
//        User.saveInJsonFile();
//        Player.saveInJsonFile();
    }
}
