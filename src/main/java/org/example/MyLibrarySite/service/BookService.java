package org.example.MyLibrarySite.service;

import jakarta.persistence.EntityNotFoundException;
import org.example.MyLibrarySite.models.Author;
import org.example.MyLibrarySite.models.Book;
import org.example.MyLibrarySite.models.KeyWords;
import org.example.MyLibrarySite.repositories.BookRepository;
import org.example.MyLibrarySite.repositories.KeyWordsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class BookService {

    @Autowired
    private final BookRepository bookRepository;
    @Autowired
    private final KeyWordsRepository keyWordsRepository;

    @Autowired
    public BookService(BookRepository bookRepository, KeyWordsRepository keyWordsRepository) {
        this.bookRepository = bookRepository;
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

    public List<Book> findBooksByYear(int year) {
        return bookRepository.findByYear(year);
    }

    public void updateBook(Book book) {
        Book existingBook = bookRepository.findById(book.getId())
                .orElseThrow(() -> new EntityNotFoundException("Book not found"));

        existingBook.setTitle(book.getTitle());
        existingBook.setAuthor(book.getAuthor());
        existingBook.setYear(book.getYear());
        existingBook.setKeyWords(book.getKeyWords());

        bookRepository.save(existingBook);
    }

    public void addBook(Book book) {
        if (book == null) {
            throw new IllegalArgumentException("Book cannot be null");
        }

        if (book.getTitle() == null || book.getTitle().trim().isEmpty()) {
            throw new IllegalArgumentException("Book title cannot be null or empty");
        }

        if (book.getAuthor() == null || book.getAuthor().getId() == null) {
            throw new IllegalArgumentException("Book must have a valid author");
        }

        if (book.getKeyWords() == null || book.getKeyWords().isEmpty()) {
            throw new IllegalArgumentException("Book must have at least one keyword");
        }

        bookRepository.save(book);
    }

    public List<Book> findBooksByAuthor(Author author) {
        return bookRepository.findByAuthor(author);
    }

    public List<Book> searchBooksByKeyWords(String keyword) {
        KeyWords keyWords = keyWordsRepository.findByWord(keyword); // Знайти ключове слово за назвою
        if (keyWords != null) {
            return bookRepository.findByKeyWords(keyWords); // Виклик методу репозиторію
        }
        return Collections.emptyList(); // Повертаємо порожній список, якщо ключове слово не знайдено
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

}

