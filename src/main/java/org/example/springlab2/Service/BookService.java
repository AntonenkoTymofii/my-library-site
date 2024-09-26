package org.example.springlab2.Service;

import jakarta.annotation.PostConstruct;
import org.example.springlab2.Models.Author;
import org.example.springlab2.Models.Book;
import org.example.springlab2.Models.KeyWords;
import org.example.springlab2.Repositories.AuthorRepository;
import org.example.springlab2.Repositories.BookRepository;
import org.example.springlab2.Repositories.KeyWordsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {

    @Autowired
    private final BookRepository bookRepository;
    @Autowired
    private final AuthorRepository authorRepository;
    @Autowired
    private final KeyWordsRepository keyWordsRepository;

    @Autowired
    public BookService(BookRepository bookRepository, AuthorRepository authorRepository, KeyWordsRepository keyWordsRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.keyWordsRepository = keyWordsRepository;
    }

    public List<Book> findAllBooks() {
        return (List<Book>) bookRepository.findAll();
    }

    public Book findById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    public Book findByTitle(String title) {
        return bookRepository.findByTitle(title);
    }

    public List<Book> searchBooks(String query) {
        return bookRepository.findByTitleContainingOrGenresNameContaining(query, query);
    }

    public void updateBook(Book book) {
        bookRepository.save(book);
    }

    // Заповнення репозиторію книгами
    @PostConstruct
    public void setUpBooks() {
        Author author1 = authorRepository.findById(1L).orElse(null);
        Author author2 = authorRepository.findById(2L).orElse(null);
        Author author3 = authorRepository.findById(3L).orElse(null);

        KeyWords keyWords1 = new KeyWords(1L, "Science Fiction", null);
        KeyWords keyWords2 = new KeyWords(2L, "Fantasy", null);
        KeyWords keyWords3 = new KeyWords(3L, "Mystery", null);

        keyWordsRepository.save(keyWords1);
        keyWordsRepository.save(keyWords2);
        keyWordsRepository.save(keyWords3);

        Book book1 = new Book(1L, "Book One", author1, List.of(keyWords1));
        Book book2 = new Book(2L, "Book Two", author2, List.of(keyWords2));
        Book book3 = new Book(3L, "Book Three", author3, List.of(keyWords3));

        bookRepository.save(book1);
        bookRepository.save(book2);
        bookRepository.save(book3);
    }
}

