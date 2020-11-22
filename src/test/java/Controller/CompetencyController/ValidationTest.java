package Controller.CompetencyController;

import junit.framework.TestCase;

public class ValidationTest extends TestCase {

    public void testEmailIsValid() {
        String email  = "hess_am-as12.13na@yahoo.com";
        assertTrue(Validation.EmailIsValid(email));
    }

    public void testPhoneNumberIsValid() {
        String email  = "09125552730";
        assertTrue(Validation.EmailIsValid(email));
    }

    public void testNameOrLastNameIsValid() {
    }

    public void testPasswordIsValid() {
    }

    public void testAgeIsValid() {
    }

    public void testUsernameIsValid() {
    }
}