package by.bsuir;

import by.bsuir.task9.Ball;
import by.bsuir.task9.Basket;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class Task9Test {
    private Basket basket1;
    private Basket basket2;
    private Basket basket3;

    @Before
    public void init() {
        basket1 = new Basket(List.of(new Ball(13, Ball.Color.RED), new Ball(11, Ball.Color.GREEN)));
        basket2 = new Basket(List.of(new Ball(8, Ball.Color.GREEN), new Ball(5, Ball.Color.GREEN),
                new Ball(3, Ball.Color.RED)));
        basket3 = new Basket(List.of());
    }

    @Test
    public void testBallsWeight1() {
        Assert.assertEquals(24, basket1.getBallsWeight());
    }

    @Test
    public void testAmountBallsOfColor1() {
        Assert.assertEquals(1, basket1.getAmountBallsOfColor(Ball.Color.GREEN));
    }

    @Test
    public void testBallsWeight2() {
        Assert.assertEquals(16, basket2.getBallsWeight());
    }

    @Test
    public void testAmountBallsOfColor2() {
        Assert.assertEquals(1, basket2.getAmountBallsOfColor(Ball.Color.RED));
    }

    @Test
    public void testBallsWeight3() {
        Assert.assertEquals(0, basket3.getBallsWeight());
    }

    @Test
    public void testAmountBallsOfColor3() {
        Assert.assertEquals(0, basket3.getAmountBallsOfColor(Ball.Color.YELLOW));
    }
}
