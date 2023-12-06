package by.bsuir.project.validator;

public class ValidatorUtils {
    public static boolean validatePasswordMatching(String password, String repeatPassword) {
        return password.equals(repeatPassword);
    }

    public static boolean validateNewCategoryData(String name, String someText, String imagePath) {
        return !name.isEmpty() && name.length() <= 45 && !someText.isEmpty() && someText.length() <= 45 && !imagePath.isEmpty() && imagePath.length() <= 45;
    }

    public static boolean validateNewProduct(String name, Float price, String description, String imagePath) {
        return !name.isEmpty() && name.length() <= 45 && !description.isEmpty() && !imagePath.isEmpty() && imagePath.length() <= 70 && price != null && price > 0;
    }
}
