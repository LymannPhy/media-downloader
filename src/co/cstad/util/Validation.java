package co.cstad.util;

public class Validation {
    public static boolean isInteger(String number) {
        try {
            Integer.parseInt(number);
            return true;
        } catch (NumberFormatException e) {
            System.out.println("Invalid Input");
            return false;
        }
    }
}
