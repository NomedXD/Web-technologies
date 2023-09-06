package by.bsuir.task6;

public class MatrixConversion {
    public static Double[][] convert(Double... numbers) {
        Double[][] result = new Double[numbers.length][numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            System.arraycopy(numbers, i, result[i], 0, numbers.length - i);
            System.arraycopy(numbers, 0, result[i], numbers.length - i, i);
        }
        return result;
    }
}
