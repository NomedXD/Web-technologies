package by.bsuir.utils;

public class ValidatorUtils {
    public static boolean validatePasswordMatching(String password, String repeatPassword) {
        return password.equals(repeatPassword);
    }

    public static boolean validateNewCategoryData(String name, String someText, String imagePath) {
        return !name.isEmpty() && name.length() <= 45 && !someText.isEmpty() && someText.length() <= 45 && !imagePath.isEmpty() && imagePath.length() <= 45;
    }

    public static boolean validateNewProduct(String name, String price, String description, String imagePath) {
        float parsedPrice;
        try {
            parsedPrice = Float.parseFloat(price);
        } catch (NumberFormatException e) {
            return false;
        }
        return !name.isEmpty() && name.length() <= 45 && parsedPrice > 0 && !description.isEmpty() && !imagePath.isEmpty() && imagePath.length() <= 70;
    }
}
