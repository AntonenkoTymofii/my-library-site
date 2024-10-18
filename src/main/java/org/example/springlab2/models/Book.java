package org.example.springlab2.models;


import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author; // Книга має одного автора

    @ManyToMany
    @JoinTable(
            name = "book_keywords",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "keyword_id")
    )
    private List<KeyWords> keyWords;

    public Book() {
    }

    public List<KeyWords> getKeywords() {
        return keyWords;
    }

    public void setKeywords(List<KeyWords> keywords) {
        this.keyWords = keywords;
    }

    public Book(Long id, String title, Author author, List<KeyWords> keyWords) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.keyWords = keyWords;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}
