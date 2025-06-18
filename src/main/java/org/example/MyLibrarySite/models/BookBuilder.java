package org.example.MyLibrarySite.models;

import java.util.List;

public class BookBuilder {
    private Long id;
    private String title;
    private int year;
    private Author author;
    private List<KeyWords> keyWords;

    public BookBuilder id(Long id) {
        this.id = id;
        return this;
    }

    public BookBuilder title(String title) {
        this.title = title;
        return this;
    }

    public BookBuilder year(int year) {
        this.year = year;
        return this;
    }

    public BookBuilder author(Author author) {
        this.author = author;
        return this;
    }

    public BookBuilder keyWords(List<KeyWords> keyWords) {
        this.keyWords = keyWords;
        return this;
    }

    public Book build() {
        return new Book(id, title, year, author, keyWords);
    }
}
