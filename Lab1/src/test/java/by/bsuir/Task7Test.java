package by.bsuir;

import by.bsuir.task7.Sort;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class Task7Test {
    private double[] arr1;
    private double[] arr2;

    @Before
    public void init() {
        arr1 = new double[]{1.0, 4.12, 6.99, -1.0};
        arr2 = new double[]{4.6, 3.1, 2.99, 1.0};
    }

    @Test
    public void test1() {
        Sort.sort(arr1);
        Assert.assertArrayEquals(new double[]{-1.0, 1.0, 4.12, 6.99}, arr1, 0.01);
    }

    @Test
    public void test2() {
        Sort.sort(arr2);
        Assert.assertArrayEquals(new double[]{1.0, 2.99, 3.1, 4.6}, arr2, 0.01);
    }
}
