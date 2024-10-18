package org.example.springlab2.service;

import jakarta.annotation.PostConstruct;
import org.example.springlab2.models.Author;
import org.example.springlab2.models.Book;
import org.example.springlab2.models.KeyWords;
import org.example.springlab2.repositories.AuthorRepository;
import org.example.springlab2.repositories.BookRepository;
import org.example.springlab2.repositories.KeyWordsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        return bookRepository.findByTitleContainingOrKeyWords_WordContaining(query, query);
    }

    public void updateBook(Book book) {
        bookRepository.save(book);
    }

}

