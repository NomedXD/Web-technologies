package by.bsuir;

import by.bsuir.task15.Book;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Task15Test {
    private List<Book> bookList;
    private List<Book> resultList;

    @Before
    public void init() {
        bookList = new ArrayList<>(List.of(
                new Book("Title1", "Author1", 12, 1),
                new Book("Title2", "Author2", 12, 5),
                new Book("Title3", "Author3", 12, 2),
                new Book("Title4", "Author4", 12, 19),
                new Book("Title5", "Author5", 12, 5)));
        resultList = new ArrayList<>(List.of(
                new Book("Title1", "Author1", 12, 1),
                new Book("Title3", "Author3", 12, 2),
                new Book("Title2", "Author2", 12, 5),
                new Book("Title5", "Author5", 12, 5),
                new Book("Title4", "Author4", 12, 19)));
    }

    @Test
    public void test1() {
        Collections.sort(bookList);
        Assert.assertEquals(resultList, bookList);
    }
}
