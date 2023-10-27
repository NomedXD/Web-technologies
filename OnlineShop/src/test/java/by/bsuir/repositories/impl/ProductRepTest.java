package by.bsuir.repositories.impl;

import by.bsuir.exception.SQLExecutionException;
import by.bsuir.repositories.DiscountCodeRepository;
import by.bsuir.repositories.ProductRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ProductRepTest {
    ProductRepository productRepository = new ProductRepositoryImpl();
    private Integer t1;
    @Before
    public void init() {
        t1 = 1;
    }

    @Test
    public void test1() throws SQLExecutionException {
        Assert.assertFalse(productRepository.getProductsByCategoryId(t1).isEmpty());
    }
}