package org.example.MyLibrarySite.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "keywords")
public class KeyWords {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "word")
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

}
