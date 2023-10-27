package by.bsuir.repositories.impl;

import by.bsuir.exception.SQLExecutionException;
import by.bsuir.repositories.CategoryRepository;
import by.bsuir.repositories.impl.CategoryRepositoryImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CategoryRepTest {
    CategoryRepository categoryRepository = new CategoryRepositoryImpl();
    private Integer t1, t2;
    private String t3, t4;
    @Before
    public void init() {
        t1 = 1;
        t2 = 15;
        t3 = "Vans";
        t4 = "SomeText";
    }

    @Test
    public void test1() throws SQLExecutionException {
        Assert.assertTrue(categoryRepository.findById(t1).isPresent());
    }

    @Test
    public void test2() throws SQLExecutionException {
        Assert.assertFalse(categoryRepository.findById(t2).isPresent());
    }

    @Test
    public void test3() throws SQLExecutionException {
        Assert.assertTrue(categoryRepository.findByName(t3).isPresent());
    }

    @Test
    public void test4() throws SQLExecutionException {
        Assert.assertFalse(categoryRepository.findByName(t4).isPresent());
    }
}
