package Controller.CompetencyController;

import Controller.AdminController.AdminGeneralController;
import Controller.AdminController.Game;
import Controller.Exception.Plato.*;
import Model.PlatoModel.Games;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {
    public static boolean emailIsValid(String email) throws InvalidEmailException {
        Pattern emailPattern = Pattern.compile("(^([a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4})*$)");
        Matcher matcher = emailPattern.matcher(email);
        if (matcher.matches())
            return true;
        else
            throw new InvalidEmailException(" Email is invalid");
    }
    public static boolean phoneNumberIsValid(String phoneNumber) throws InvalidPhoneNumberException {
        Pattern phonePattern = Pattern.compile("^(0)?9\\d{9}$");
        Matcher matcher = phonePattern.matcher(phoneNumber);
        if (matcher.matches())
            return true;
        else
            throw new InvalidPhoneNumberException(" Phone number is invalid");
    }
    public static boolean nameOrLastNameIsValid(String nameOrLastName) throws InvalidNameException {
        Pattern namePattern = Pattern.compile("(^[a-zA-Z]*$)");
        Matcher matcher = namePattern.matcher(nameOrLastName);
        if (matcher.matches())
           return true;
        else
            throw new InvalidNameException("Name Or Lastname is Invalid");
    }
    public static boolean passwordIsValid(String password) throws StrongerPasswordException {
        Pattern passwordPattern = Pattern.compile("(^(?=^.{6,}$)((?=.*[A-Za-z0-9])(?=.*[A-Z])(?=.*[a-z]))^.*$)");
        Matcher matcher = passwordPattern.matcher(password);
        if (matcher.matches())
            return true;
        else
            throw new StrongerPasswordException ("Please try stronger password !");
    }
    public static boolean ageIsValid(String age) throws InvalidAgeException {
        Pattern agePattern = Pattern.compile("^(1[90]|[2-6][0-9])$");
        Matcher matcher = agePattern.matcher(age);
        if (matcher.matches())
            return true;
        else
            throw new InvalidAgeException("Age format is invalid");
    }
    public static boolean usernameIsValid(String username) throws InvalidUserNameException {
        Pattern namePattern = Pattern.compile("(^[a-zA-Z0-9]*$)");
        Matcher matcher = namePattern.matcher(username);
        if (matcher.matches())
           return true;
        else
            throw new InvalidUserNameException(username);
    }
    public static boolean dateIsValid(String date) throws InvalidDateException {

        Pattern namePattern = Pattern.compile("^((19|2[0-9])[0-9]{2})-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$");
        Matcher matcher = namePattern.matcher(date);
        if (matcher.matches())
            return true;
        else
            throw new InvalidDateException("Date format is invalid");

    }
    public static boolean gameNameIsValid(String game) throws InvalidGameNameException {

        if (game.equalsIgnoreCase(Games.getGames().get(0).getGameName()) || game.equalsIgnoreCase(Games.getGames().get(1).getGameName()))
            return true;
        else
            throw new InvalidGameNameException(game);

    }
    public static boolean rememberInputValidation(String input) throws RememberMeException {
        if (input.equalsIgnoreCase("yes")|| input.equalsIgnoreCase("no")){
            return true;
        }
        else {
            throw new RememberMeException(input);
        }
    }


}

