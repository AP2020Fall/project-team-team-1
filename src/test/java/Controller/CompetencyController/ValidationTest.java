package Controller.CompetencyController;

import Controller.Exception.*;
import junit.framework.TestCase;

public class ValidationTest extends TestCase {

    public void testEmailIsValid() throws InvalidEmailException {
        String emailForTrue  = "amirZgh@gmail.com";
        assertTrue(Validation.emailIsValid(emailForTrue));
        String emailForFlase  = "hess_am-as12.13na.com";
        assertFalse(Validation.emailIsValid(emailForFlase));
    }

    public void testPhoneNumberIsValid() throws InvalidPhoneNumberException {
        String phoneNumber = "09125552730";
        assertTrue(Validation.phoneNumberIsValid(phoneNumber));
    }

    public void testNameOrLastNameIsValid() throws InvalidNameException {
        String nameOrLastnameForFalse = "hes123sam";
        assertFalse(Validation.nameOrLastNameIsValid(nameOrLastnameForFalse));
        String nameOrLastnameForTrue = "APteam";
        assertTrue(Validation.nameOrLastNameIsValid(nameOrLastnameForTrue));
    }

    public void testPasswordIsValid() throws InvalidPasswordException, StrongerPasswordException {
        String passwordForFalse = "aminisolaq";
        assertFalse(Validation.passwordIsValid(passwordForFalse));
        String passwordForTrue = "Apteam@1235";
        assertTrue(Validation.passwordIsValid(passwordForTrue));

    }

    public void testAgeIsValid() throws InvalidAgeException {
        String ageForFalse = "999";
        assertFalse(Validation.ageIsValid(ageForFalse));
        String ageForTrue = "50";
        assertTrue(Validation.ageIsValid(ageForTrue));
    }

    public void testUsernameIsValid() throws InvalidUserNameException {
        String name = "Amirzgh11";
        assertTrue(Validation.usernameIsValid(name));
    }
}