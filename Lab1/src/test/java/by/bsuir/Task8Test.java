package by.bsuir;

import by.bsuir.task8.CalculationSequence;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class Task8Test {
    private List<Integer> a1, b1, a2, b2, result1, result2;
    @Before
    public void init(){
        a1 = new ArrayList<>(List.of(5, 5, 6, 7, 7));
        b1 = new ArrayList<>(List.of(5,6));
        a2 = new ArrayList<>(List.of(5, 5, 6, 7, 7));
        b2 = new ArrayList<>(List.of(5,6,8));
        result1 = new ArrayList<>(List.of(2,4));
        result2 = new ArrayList<>(List.of(2,4,7));
    }
    @Test
    public void test1() {
        Assert.assertEquals(result1, CalculationSequence.calculateSequence(a1, b1));
    }
    @Test
    public void test2() {
        Assert.assertEquals(result2, CalculationSequence.calculateSequence(a2, b2));
    }
}
