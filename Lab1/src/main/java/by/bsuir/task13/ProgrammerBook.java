package by.bsuir.task13;

import by.bsuir.task12.Book;

import java.util.Objects;

public class ProgrammerBook extends Book {
    private String language;
    private int level;

    @Override
    public int hashCode() {
        int prime = 20011;
        int hash = super.hashCode();
        hash = prime * hash + Objects.hash(language);
        hash = prime * hash + Objects.hash(level);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        if (! super.equals(obj)) return false;
        else {
            ProgrammerBook programmerBook = (ProgrammerBook) obj;
            return language.equals(programmerBook.language) && level == programmerBook.level;
        }
    }

    @Override
    public String toString() {
        return String.format("%s. Language: %s, level: %d", super.toString(), language, level);
    }
}
