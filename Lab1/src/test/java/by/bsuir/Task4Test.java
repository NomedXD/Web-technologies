package by.bsuir;

import by.bsuir.task4.Prime;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class Task4Test {
    private Integer[] arr1;
    private Integer[] arr2;

    @Before
    public void init() {
        arr1 = new Integer[]{0, 13, 17, 55};
        arr2 = new Integer[]{49, -26, -51, 12, 5, 97};
    }

    @Test
    public void testPrimesSearch1() {
        Assert.assertEquals(List.of(1, 2), Prime.findIndexes(arr1));
    }

    @Test
    public void testPrimesSearch2() {
        Assert.assertEquals(List.of(1, 4, 5), Prime.findIndexes(arr2));
    }
}
