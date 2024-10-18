package org.example.springlab2.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "keywords")
public class KeyWords {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String word;

    @ManyToMany(mappedBy = "keyWords")
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
