package by.bsuir;

import by.bsuir.task16.AuthorNameComparator;
import by.bsuir.task16.AuthorNamePriceComparator;
import by.bsuir.task16.Book;
import by.bsuir.task16.NameAuthorComparator;
import by.bsuir.task16.NameComparator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class Task16Test {
    private List<Book> bookList;
    private List<Book> resultList1;
    private List<Book> resultList2;
    private List<Book> resultList3;
    private List<Book> resultList4;

    @Before
    public void init() {
        bookList = new ArrayList<>(List.of(
                new Book("A", "J", 44),
                new Book("B", "G", 12),
                new Book("L", "L", 115),
                new Book("L", "L", 77),
                new Book("B", "A", 1)));
        resultList1 = new ArrayList<>(List.of(
                new Book("A", "J", 44),
                new Book("B", "G", 12),
                new Book("B", "A", 1),
                new Book("L", "L", 115),
                new Book("L", "L", 77)));
        resultList2 = new ArrayList<>(List.of(
                new Book("A", "J", 44),
                new Book("B", "A", 1),
                new Book("B", "G", 12),
                new Book("L", "L", 115),
                new Book("L", "L", 77)));
        resultList3 = new ArrayList<>(List.of(
                new Book("B", "A", 1),
                new Book("B", "G", 12),
                new Book("A", "J", 44),
                new Book("L", "L", 115),
                new Book("L", "L", 77)));
        resultList4 = new ArrayList<>(List.of(
                new Book("B", "A", 1),
                new Book("B", "G", 12),
                new Book("A", "J", 44),
                new Book("L", "L", 77),
                new Book("L", "L", 115)));
    }

    @Test
    public void test1() {
        bookList.sort(new NameComparator());
        System.out.println(bookList);
        Assert.assertEquals(resultList1, bookList);
    }

    @Test
    public void test2() {
        bookList.sort(new NameAuthorComparator());
        System.out.println(bookList);
        Assert.assertEquals(resultList2, bookList);
    }

    @Test
    public void test3() {
        bookList.sort(new AuthorNameComparator());
        System.out.println(bookList);
        Assert.assertEquals(resultList3, bookList);
    }

    @Test
    public void test4() {
        bookList.sort(new AuthorNamePriceComparator());
        System.out.println(bookList);
        Assert.assertEquals(resultList4, bookList);
    }
}
