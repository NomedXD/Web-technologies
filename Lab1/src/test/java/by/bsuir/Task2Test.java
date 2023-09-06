package by.bsuir;

import by.bsuir.task2.Calculation;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class Task2Test {
    private int x, y;
    private boolean result;
    @Before
    public void init(){
        x = 2;
        y = 5;
        result = true;
    }
    @Test
    public void test() {
        Assert.assertEquals(result, Calculation.isInArea(x,y));
    }
}
