package org.example.springlab2.Service;

import jakarta.annotation.PostConstruct;
import org.example.springlab2.Models.Author;
import org.example.springlab2.Repositories.AuthorRepository;
import org.example.springlab2.Repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    // Заповнення репозиторію авторами
    @PostConstruct
    public void setUpAuthors() {
        Author author1 = new Author(1L, "Albert White", null);
        Author author2 = new Author(2L, "David Rich", null);
        Author author3 = new Author(3L, "Sam Anderson", null);

        author1.setBooks(bookRepository.findByAuthor(author1));
        author2.setBooks(bookRepository.findByAuthor(author2));
        author3.setBooks(bookRepository.findByAuthor(author3));

        authorRepository.save(author1);
        authorRepository.save(author2);
        authorRepository.save(author3);
    }
}
