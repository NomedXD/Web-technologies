package by.bsuir.task14;

import java.util.Objects;

public class Book implements Cloneable{
    private String title;
    private String author;
    private int price;
    private static int edition;

    public Book(String title, String author, int price) {
        this.title = title;
        this.author = author;
        this.price = price;
    }

    @Override
    public int hashCode() {
        int prime = 31;
        return prime * Objects.hash(title, author, price, edition);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Book book = (Book) obj;
        return title.equals(book.title) &&
                author.equals(book.author) && price == book.price;
    }

    @Override
    public String toString() {
        return String.format("Title: %s, author: %s, price: %d, edition: %d", title, author, price, edition);
    }

    @Override
    public Object clone() {
        Book book;
        try
        {
            book = (Book) super.clone();
        }
        catch (CloneNotSupportedException e)
        {
            throw new Error();
        }
        return book;
    }
}
