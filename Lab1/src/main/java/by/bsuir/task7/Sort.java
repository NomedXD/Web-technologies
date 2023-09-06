package by.bsuir.task7;

public class Sort {
    public static void sort(double[] array) {
        int range = 1;
        while (range * 3 < array.length)
            range = range * 3 + 1;

        while (range >= 1) {
            sorting(array, range);
            range = range / 3;
        }
    }

    private static void swap(double[] array, int j, int i) {
        double temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    private static void sorting(double[] array, int range) {
        int length = array.length;
        for (int i = range; i < length; i++) {
            for (int j = i; j >= range; j = j - range) {
                if (array[j] < array[j - range])
                    swap(array, j, j - range);
                else
                    break;
            }
        }
    }
}
