//package View.Regexes;
//
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//public class RegisterRegexes {
//    public static boolean checkUsernameRegex(String name){
//        boolean result = false;
//        Pattern namePattern = Pattern.compile("(^[a-zA-Z0-9]*$)");
//        Matcher matcher = namePattern.matcher(name);
//        if (matcher.matches())
//            result=true;
//        return result;
//    }
//    public static boolean checkPasswordRegex(String password){
//        boolean result = false;
//        Pattern passwordPattern = Pattern.compile("(^(?=^.{6,}$)((?=.*[A-Za-z0-9])(?=.*[A-Z])(?=.*[a-z]))^.*$)");
//        Matcher matcher = passwordPattern.matcher(password);
//        if (matcher.matches())
//            result=true;
//        return result;
//    }
//    public static boolean checkPhoneNumRegex(String phoneNum){
//        boolean result = false;
//        Pattern phonePattern = Pattern.compile("(^((([0-9]{1})*[- .(]*([0-9]{3})[- .)]*[0-9]{3}[- .]*[0-9]{4})+)*$)");
//        Matcher matcher = phonePattern.matcher(phoneNum);
//        if (matcher.matches())
//            result = true;
//        return result;
//    }
//    public static boolean checkEmailRegex(String email){
//        boolean result = false;
//        Pattern emailPattern = Pattern.compile("(^([a-zA-Z0-9._%-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4})*$)");
//        Matcher matcher = emailPattern.matcher(email);
//        if (matcher.matches())
//            result = true;
//        return result;
//    }
//    public static boolean checkNameRegex(String name){
//        boolean result = false;
//        Pattern namePattern = Pattern.compile("(^[a-zA-Z]*$)");
//        Matcher matcher = namePattern.matcher(name);
//        if (matcher.matches())
//            result = true;
//        return result;
//    }
//    public static boolean checkAgeRegex(String age){
//        boolean result = false;
//        Pattern agePattern = Pattern.compile("(^[0-9][0-9]$)");
//        Matcher matcher = agePattern.matcher(age);
//        if (matcher.matches())
//            result = true;
//        return result;
//    }
//}
