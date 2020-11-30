package Controller.RegisterController;


        import Controller.CompetencyController.Existence;
        import Controller.PlayerController.FindPlayerByInfo;
        import Model.PlatoModel.Player;
        import Model.PlatoModel.User;

public class Delete {
    public static boolean deleteUser(String input) {
        String[] inputSplit = input.split("\\s");

        if (!(Existence.checkUserNameExistence(inputSplit[0]))) {
            System.out.println("INVALID USERNAME");
            return false;
        }

        if (!(Existence.checkPassword(inputSplit[0], inputSplit[1]))) {
            System.out.println("WRONG PASSWORD");
            return false;
        }

        Player player = FindPlayerByInfo.findByUserName(inputSplit[0]);
        Player.players.remove(player);
        User.users.remove(player);
        User.saveInJsonFile();
        Player.saveInJsonFile();
        return true;
    }
}
