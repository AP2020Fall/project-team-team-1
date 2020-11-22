package Controller.AdminController;

public class Edit {
    public static void editField(String field, String input) {
        if (field.trim().equalsIgnoreCase("name")) {
            editName(input);
        } else if (field.trim().equalsIgnoreCase("lastname")) {
            editLastName(input);
        } else if (field.trim().equalsIgnoreCase("email")) {
            editEmail(input);
        } else if (field.trim().equalsIgnoreCase("phonenumber")) {
            editPhoneNumber(input);
        } else if (field.trim().equalsIgnoreCase("username")) {
            editUsername(input);
        } else
            System.out.println("Field for edit is InValid !");
    }

    protected static void editName(String input) {

    }

    protected static void editLastName(String input) {

    }

    protected static void editEmail(String input) {

    }
    protected static void editUsername(String input) {

    }

    protected static void editPhoneNumber(String input) {

    }

    public static void editPassword(String oldPassword, String newPassword) {

    }
}
