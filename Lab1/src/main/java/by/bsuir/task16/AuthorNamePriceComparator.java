package by.bsuir.task16;

import java.util.Comparator;

public class AuthorNamePriceComparator implements Comparator<Book> {
    @Override
    public int compare(Book o1, Book o2) {
        Comparator<Book> comparator1 = Comparator.comparing(Book::getAuthor);
        Comparator<Book> comparator2 = Comparator.comparing(Book::getTitle);
        Comparator<Book> comparator3 = Comparator.comparing(Book::getPrice);
        return comparator1.thenComparing(comparator2).thenComparing(comparator3).compare(o1, o2);
    }
}
