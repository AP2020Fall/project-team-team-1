package Controller.CompetencyController;

import junit.framework.TestCase;

public class ValidationTest extends TestCase {

    public void testEmailIsValid() {
        String emailForTrue  = "hess_am-as12.13na@yahoo.com";
        assertTrue(Validation.EmailIsValid(emailForTrue));
        String emailForFlase  = "hess_am-as12.13na.com";
        assertFalse(Validation.EmailIsValid(emailForFlase));
    }

    public void testPhoneNumberIsValid() {
        String phoneNumber = "09125552730";
        assertTrue(Validation.PhoneNumberIsValid(phoneNumber));
    }

    public void testNameOrLastNameIsValid() {
        String nameOrLastnameForFalse = "hes123sam";
        assertFalse(Validation.NameOrLastNameIsValid(nameOrLastnameForFalse));
        String nameOrLastnameForTrue = "APteam";
        assertTrue(Validation.NameOrLastNameIsValid(nameOrLastnameForTrue));
    }

    public void testPasswordIsValid() {
        String passwordForFalse = "aminisolaq";
        assertFalse(Validation.PasswordIsValid(passwordForFalse));
        String passwordForTrue = "Apteam@1235";
        assertTrue(Validation.PasswordIsValid(passwordForTrue));

    }

    public void testAgeIsValid() {
        String age = "50";
        assertTrue(Validation.AgeIsValid(age));
    }

    public void testUsernameIsValid() {
        String name = "Amirzgh11";
        assertTrue(Validation.UsernameIsValid(name));
    }
}