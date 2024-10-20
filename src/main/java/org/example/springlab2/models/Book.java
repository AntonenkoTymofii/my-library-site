package org.example.springlab2.models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "year")
    private int year;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author; // Книга має одного автора

    @ManyToMany
    @JoinTable(
            name = "bookkeywords",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "keyword_id")
    )
    private List<KeyWords> keyWords;

    public Book() {
    }

    public Book(Long id, String title, int year, Author author, List<KeyWords> keyWords) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.author = author;
        this.keyWords = keyWords;
    }

}
