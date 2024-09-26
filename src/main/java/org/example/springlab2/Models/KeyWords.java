package org.example.springlab2.Models;


import java.util.List;

public class KeyWords {

    private Long id;

    private String word;

    private List<Book> books;

    public KeyWords(long id, String word, List<Book> books) {
        this.id = id;
        this.word = word;
        this.books = books;
    }

    public KeyWords() {
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }
}
