package by.bsuir;

import by.bsuir.task14.Book;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class Task14Test {
    private Book book;
    @Before
    public void init(){
            book = new Book("Title", "Author", 12);
    }
    @Test
    public void test1() {
        Book book1 = (Book) book.clone();
        Assert.assertEquals(book, book1);
        System.out.printf("%d, %d", book.hashCode(), book1.hashCode());
        Assert.assertEquals(book.hashCode(), book1.hashCode());
    }
}
