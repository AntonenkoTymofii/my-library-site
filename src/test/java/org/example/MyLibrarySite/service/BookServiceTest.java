package org.example.MyLibrarySite.service;

import jakarta.persistence.EntityNotFoundException;
import org.example.MyLibrarySite.models.Author;
import org.example.MyLibrarySite.models.Book;
import org.example.MyLibrarySite.repositories.BookRepository;
import org.example.MyLibrarySite.repositories.KeyWordsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class BookServiceTest {

    private BookRepository bookRepository;
    private KeyWordsRepository keyWordsRepository;
    private BookService bookService;

    @BeforeEach
    void setUp() {
        bookRepository = mock(BookRepository.class);
        keyWordsRepository = mock(KeyWordsRepository.class);
        bookService = new BookService(bookRepository, keyWordsRepository);
    }

    @Test
    void testFindAllBooks() {
        Author author = new Author(1L, "John", "Doe", new ArrayList<>());
        Book book1 = new Book(1L, "Book One", 2021, author, new ArrayList<>());
        Book book2 = new Book(2L, "Book Two", 2022, author, new ArrayList<>());

        when(bookRepository.findAll()).thenReturn(Arrays.asList(book1, book2));

        List<Book> books = bookService.findAllBooks();

        assertEquals(2, books.size());
        verify(bookRepository, times(1)).findAll();
    }

    @Test
    void testFindById_found() {
        Author author = new Author(1L, "John", "Doe", new ArrayList<>());
        Book book = new Book(1L, "Book One", 2021, author, new ArrayList<>());

        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));

        Book foundBook = bookService.findById(1L);

        assertNotNull(foundBook);
        assertEquals("Book One", foundBook.getTitle());
        verify(bookRepository, times(1)).findById(1L);
    }

    @Test
    void testFindById_notFound() {
        when(bookRepository.findById(1L)).thenReturn(Optional.empty());

        Book foundBook = bookService.findById(1L);

        assertNull(foundBook);
        verify(bookRepository, times(1)).findById(1L);
    }

    @Test
    void testFindByTitle() {
        Author author = new Author(1L, "John", "Doe", new ArrayList<>());
        Book book = new Book(1L, "Book One", 2021, author, new ArrayList<>());

        when(bookRepository.findByTitle("Book One")).thenReturn(book);

        Book foundBook = bookService.findByTitle("Book One");

        assertNotNull(foundBook);
        assertEquals("Book One", foundBook.getTitle());
        verify(bookRepository, times(1)).findByTitle("Book One");
    }

    @Test
    void testSearchBooks() {
        Author author = new Author(1L, "John", "Doe", new ArrayList<>());
        Book book1 = new Book(1L, "Book One", 2021, author, new ArrayList<>());
        Book book2 = new Book(2L, "Amazing Book", 2022, author, new ArrayList<>());

        when(bookRepository.findByTitleContainingOrKeyWords_WordContaining("Book", "Book")).thenReturn(Arrays.asList(book1, book2));

        List<Book> books = bookService.searchBooks("Book");

        assertEquals(2, books.size());
        verify(bookRepository, times(1)).findByTitleContainingOrKeyWords_WordContaining("Book", "Book");
    }

    @Test
    void testFindBooksByYear() {
        Author author = new Author(1L, "John", "Doe", new ArrayList<>());
        Book book = new Book(1L, "Book One", 2021, author, new ArrayList<>());

        when(bookRepository.findByYear(2021)).thenReturn(Arrays.asList(book));

        List<Book> books = bookService.findBooksByYear(2021);

        assertEquals(1, books.size());
        assertEquals("Book One", books.get(0).getTitle());
        verify(bookRepository, times(1)).findByYear(2021);
    }

    @Test
    void testUpdateBook() {
        Author author = new Author(1L, "John", "Doe", new ArrayList<>());
        Book existingBook = new Book(1L, "Old Title", 2021, author, new ArrayList<>());
        Book updatedBook = new Book(1L, "New Title", 2022, author, new ArrayList<>());

        when(bookRepository.findById(1L)).thenReturn(Optional.of(existingBook));

        bookService.updateBook(updatedBook);

        assertEquals("New Title", existingBook.getTitle());
        verify(bookRepository, times(1)).save(existingBook);
    }

    @Test
    void testUpdateBook_notFound() {
        Book updatedBook = new Book(1L, "New Title", 2022, new Author(), new ArrayList<>());

        when(bookRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> bookService.updateBook(updatedBook));
    }

    @Test
    void testFindBooksByAuthor() {
        Author author = new Author(1L, "John", "Doe", new ArrayList<>());
        Book book = new Book(1L, "Book One", 2021, author, new ArrayList<>());

        when(bookRepository.findByAuthor(author)).thenReturn(Arrays.asList(book));

        List<Book> books = bookService.findBooksByAuthor(author);

        assertEquals(1, books.size());
        assertEquals("Book One", books.get(0).getTitle());
        verify(bookRepository, times(1)).findByAuthor(author);
    }

    @Test
    void testDeleteBook() {
        doNothing().when(bookRepository).deleteById(1L);

        bookService.deleteBook(1L);

        verify(bookRepository, times(1)).deleteById(1L);
    }
}
