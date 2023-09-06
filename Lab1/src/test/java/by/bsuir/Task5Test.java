package by.bsuir;

import by.bsuir.task5.Ascending;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class Task5Test {
    private int[] arr1;
    private int[] arr2;

    @Before
    public void init() {
        arr1 = new int[]{1, 2, 3, -7, 8};
        arr2 = new int[]{1, 3, 4, 3, 3, 5, 6};
    }

    @Test
    public void testMinPops1() {
        Assert.assertEquals(1, Ascending.findMinPops(arr1));
    }

    @Test
    public void testMinPops2() {
        Assert.assertEquals(2, Ascending.findMinPops(arr2));
    }
}
