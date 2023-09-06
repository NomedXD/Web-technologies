package by.bsuir.task15;

import java.util.Objects;

public class Book implements Comparable<Book> {
    private String title;
    private String author;
    private int price;
    private static int edition;
    private int isbn;

    public Book(String title, String author, int price, int isbn) {
        this.title = title;
        this.author = author;
        this.price = price;
        this.isbn = isbn;
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
    public int compareTo(Book o) {
        return isbn - o.isbn;
    }
}
