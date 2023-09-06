package by.bsuir.task3;

import java.util.ArrayList;
import java.util.List;

public class Calculation {
    public static List<Double> calculate(Double left, Double right, Double step) {
        List<Double> result = new ArrayList<>();
        while (left <= right) {
            result.add(Math.tan(left));
            left += step;
        }
        return result;
    }
}
