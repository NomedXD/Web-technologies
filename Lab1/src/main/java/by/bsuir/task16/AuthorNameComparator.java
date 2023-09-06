package by.bsuir.task16;

import java.util.Comparator;

public class AuthorNameComparator implements Comparator<Book> {
    @Override
    public int compare(Book o1, Book o2) {
        Comparator<Book> comparator1 = Comparator.comparing(Book::getAuthor);
        Comparator<Book> comparator2 = Comparator.comparing(Book::getTitle);
        return comparator1.thenComparing(comparator2).compare(o1, o2);
    }
}
