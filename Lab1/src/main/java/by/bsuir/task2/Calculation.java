package by.bsuir.task2;

public class Calculation {
    public static Boolean isInArea(Integer x, Integer y) {
        if (y > 0 && y < 6) {
            return x > -5 && x < 5;
        } else if (y > -4 && y < 0) {
            return x > -7 && x < 7;
        }
        return false;
    }
}
