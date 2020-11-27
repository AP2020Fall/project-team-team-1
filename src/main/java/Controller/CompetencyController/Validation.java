package Controller.CompetencyController;

import Controller.Exception.InvalidEmailException;
import Controller.Exception.InvalidNameException;
import Controller.Exception.InvalidPhoneNumberException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {
    public static boolean emailIsValid(String email) throws InvalidEmailException {
        boolean result = false;
        Pattern emailPattern = Pattern.compile("(^([a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4})*$)");
        Matcher matcher = emailPattern.matcher(email);
        if (matcher.matches())
            result = true;
        else
            throw new InvalidEmailException(" Email is invalid");
        return result;
    }
    public static boolean phoneNumberIsValid(String phoneNumber) throws InvalidPhoneNumberException {
        boolean result = false;
        Pattern phonePattern = Pattern.compile("^(0)?9\\d{9}$");
        Matcher matcher = phonePattern.matcher(phoneNumber);
        if (matcher.matches())
            result = true;
        else
            throw new InvalidPhoneNumberException(" Phone number is invalid");
        return result;
    }
    public static boolean nameOrLastNameIsValid(String nameOrLastName) throws InvalidNameException {
        boolean result = false;
        Pattern namePattern = Pattern.compile("(^[a-zA-Z]*$)");
        Matcher matcher = namePattern.matcher(nameOrLastName);
        if (matcher.matches())
            result = true;
        else
            throw new InvalidNameException("Name Or Lastname is Invalid");
        return result;
    }
    public static boolean passwordIsValid(String password){
        boolean result = false;
        Pattern passwordPattern = Pattern.compile("(^(?=^.{6,}$)((?=.*[A-Za-z0-9])(?=.*[A-Z])(?=.*[a-z]))^.*$)");
        Matcher matcher = passwordPattern.matcher(password);
        if (matcher.matches())
            result=true;
        return result;
    }
    public static boolean ageIsValid(String age){
        boolean result = false;
        Pattern agePattern = Pattern.compile("^(1[90]|[2-6][0-9])$");
        Matcher matcher = agePattern.matcher(age);
        if (matcher.matches())
            result = true;
        return result;
    }
    public static boolean usernameIsValid(String username){
        boolean result = false;
        Pattern namePattern = Pattern.compile("(^[a-zA-Z0-9]*$)");
        Matcher matcher = namePattern.matcher(username);
        if (matcher.matches())
            result=true;
        return result;
    }
    public static boolean dateIsValid(String date){
        boolean result = false;
        Pattern namePattern = Pattern.compile("^((19|2[0-9])[0-9]{2})-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$");
        Matcher matcher = namePattern.matcher(date);
        if (matcher.matches())
            result=true;
        return result;
    }

}

