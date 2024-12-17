package org.example.MyLibrarySite.controllers;

import org.example.MyLibrarySite.models.Book;
import org.example.MyLibrarySite.models.Author;
import org.example.MyLibrarySite.service.AuthorService;
import org.example.MyLibrarySite.service.BookService;
import org.example.MyLibrarySite.service.KeyWordsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class BookControllerTest {

    @InjectMocks
    private BookController bookController;

    @Mock
    private BookService bookService;

    @Mock
    private AuthorService authorService;

    @Mock
    private KeyWordsService keyWordsService;

    @Mock
    private Model model;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testViewBooks() {
        List<Book> books = new ArrayList<>();
        when(bookService.findAllBooks()).thenReturn(books);

        String viewName = bookController.viewBooks(model);

        verify(model).addAttribute("books", books);
        assertEquals("books", viewName);
    }

    @Test
    void testShowAddForm() {
        when(authorService.findAllAuthors()).thenReturn(new ArrayList<>());
        when(keyWordsService.findAllKeyWords()).thenReturn(new ArrayList<>());

        String viewName = bookController.showAddForm(model);

        verify(model).addAttribute(eq("book"), any(Book.class));
        verify(model).addAttribute(eq("authors"), anyList());
        verify(model).addAttribute(eq("allGenres"), anyList());
        assertEquals("add_book", viewName);
    }

    @Test
    void testAddBook() {
        Book book = new Book();
        book.setTitle("Test Book");

        String redirectView = bookController.addBook(book);

        verify(bookService).addBook(book);
        assertEquals("redirect:/book/all", redirectView);
    }

    @Test
    void testShowEditForm() {
        Long bookId = 1L;
        Book book = new Book();
        when(bookService.findById(bookId)).thenReturn(book);
        when(authorService.findAllAuthors()).thenReturn(new ArrayList<>());
        when(keyWordsService.findAllKeyWords()).thenReturn(new ArrayList<>());

        String viewName = bookController.showEditForm(bookId, model);

        verify(model).addAttribute(eq("book"), any(Book.class));
        verify(model).addAttribute(eq("authors"), anyList());
        verify(model).addAttribute(eq("allGenres"), anyList());
        assertEquals("edit_book", viewName);
    }

    @Test
    void testUpdateBook() {
        Book book = new Book();
        book.setTitle("Updated Book");

        String redirectView = bookController.updateBook(book);

        verify(bookService).updateBook(book);
        assertEquals("redirect:/book/all", redirectView);
    }

    @Test
    void testDeleteBook() {
        Long bookId = 1L;

        String redirectView = bookController.deleteBook(bookId);

        verify(bookService).deleteBook(bookId);
        assertEquals("redirect:/book/all", redirectView);
    }

    @Test
    void testSearchBooksByTitle() {
        String title = "Some Title";
        List<Book> books = new ArrayList<>();
        when(bookService.searchBooks(title)).thenReturn(books);

        String viewName = bookController.searchBooks(title, null, null, null, model);

        verify(model).addAttribute(eq("books"), anyList());
        assertEquals("search_results_books", viewName);
    }

    @Test
    void testSearchBooksByAuthor() {
        String authorName = "Some Author";
        List<Book> books = new ArrayList<>();
        when(authorService.findByFirstName(authorName)).thenReturn(new Author());
        when(bookService.findBooksByAuthor(any())).thenReturn(books);

        String viewName = bookController.searchBooks(null, authorName, null, null, model);

        verify(model).addAttribute(eq("books"), anyList());
        assertEquals("search_results_books", viewName);
    }

    @Test
    void testSearchBooksByYear() {
        int year = 2021;
        List<Book> books = new ArrayList<>();
        when(bookService.findBooksByYear(year)).thenReturn(books);

        String viewName = bookController.searchBooks(null, null, year, null, model);

        verify(model).addAttribute(eq("books"), anyList());
        assertEquals("search_results_books", viewName);
    }

    @Test
    void testSearchBooksByKeyWord() {
        String word = "example";
        List<Book> books = new ArrayList<>();
        when(bookService.searchBooksByKeyWords(word)).thenReturn(books);

        String viewName = bookController.searchBooks(null, null, null, word, model);

        verify(model).addAttribute(eq("books"), anyList());
        assertEquals("search_results_books", viewName);
    }
}
