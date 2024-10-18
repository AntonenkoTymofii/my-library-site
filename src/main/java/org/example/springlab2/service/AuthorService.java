package org.example.springlab2.service;

import jakarta.annotation.PostConstruct;
import org.example.springlab2.models.Author;
import org.example.springlab2.repositories.AuthorRepository;
import org.example.springlab2.repositories.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public AuthorService(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    public List<Author> findAllAuthors() {
        return (List<Author>) authorRepository.findAll();
    }

    public List<Author> searchAuthors(String name) {
        return authorRepository.findByNameContaining(name);
    }

    public Author findById(Long id) {
        return authorRepository.findById(id).orElse(null);
    }

    public void updateAuthor(Author author) {
        authorRepository.save(author);
    }

}
