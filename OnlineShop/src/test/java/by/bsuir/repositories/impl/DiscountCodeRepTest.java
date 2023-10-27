package by.bsuir.repositories.impl;

import by.bsuir.exception.SQLExecutionException;
import by.bsuir.repositories.DiscountCodeRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DiscountCodeRepTest {
    DiscountCodeRepository discountCodeRepository = new DiscountCodeRepositoryImpl();
    private String t1, t2;
    @Before
    public void init() {
        t1 = "МИНСК";
        t2 = "SomeText";
    }

    @Test
    public void test1() throws SQLExecutionException {
        Assert.assertTrue(discountCodeRepository.findDiscountCodeByName(t1).isPresent());
    }

    @Test
    public void test2() throws SQLExecutionException {
        Assert.assertFalse(discountCodeRepository.findDiscountCodeByName(t2).isPresent());
    }
}