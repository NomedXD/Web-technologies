package by.bsuir;

import by.bsuir.task1.Calculation;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class Task1Test {
    private double x, y, result;
    @Before
    public void init(){
        x = 3.67;
        y = 2.34;
        result = 3.86254;
    }
    @Test
    public void test() {
        Assert.assertEquals(result, Calculation.calculate(3.67, 2.34), 0.2);
    }
}
