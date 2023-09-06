package by.bsuir;

import by.bsuir.task6.MatrixConversion;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class Task6Test {
    private Double[] arr1;
    private Double[] arr2;

    @Before
    public void init() {
        arr1 = new Double[]{5.0, 7.0};
        arr2 = new Double[]{1.0, 2.0, 3.0};
    }

    @Test
    public void test1() {
        Assert.assertArrayEquals(new double[][]{ {5.0, 7.0}, {5.0, 7.0} }, MatrixConversion.convert(arr1));
    }

    @Test
    public void test2() {
        Assert.assertArrayEquals(new double[][]{ {1.0, 2.0, 3.0}, {2.0, 3.0, 1.0}, {3.0, 1.0, 2.0} }, MatrixConversion.convert(arr2));
    }
}
