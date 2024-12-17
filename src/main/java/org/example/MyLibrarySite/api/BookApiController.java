package org.example.MyLibrarySite.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.example.MyLibrarySite.models.Book;
import org.example.MyLibrarySite.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@Tag(name = "Books", description = "API для роботи з книгами")
public class BookApiController {

    @Autowired
    private BookService bookService;

    @Operation(summary = "Отримати всі книги", description = "Повертає список усіх книг у базі даних.")
    @ApiResponse(responseCode = "200", description = "Список книг успішно отримано")
    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.findAllBooks();
    }

    @Operation(summary = "Отримати книгу за ID", description = "Повертає книгу за вказаним ID.")
    @ApiResponse(responseCode = "200", description = "Книга знайдена")
    @ApiResponse(responseCode = "404", description = "Книга не знайдена")
    @GetMapping("/{id}")
    public Book getBookById(@Parameter(description = "ID книги", required = true) @PathVariable Long id) {
        return bookService.findById(id);
    }

    @Operation(summary = "Додати нову книгу", description = "Створює нову книгу в базі даних.")
    @ApiResponse(responseCode = "201", description = "Книга успішно створена")
    @PostMapping
    public Book createBook(@RequestBody Book book) {
        bookService.addBook(book);
        return book;
    }

    @Operation(summary = "Оновити книгу", description = "Оновлює існуючу книгу за її ID.")
    @ApiResponse(responseCode = "200", description = "Книга успішно оновлена")
    @PutMapping("/{id}")
    public Book updateBook(
            @Parameter(description = "ID книги", required = true) @PathVariable Long id,
            @RequestBody Book book) {
        book.setId(id);
        bookService.updateBook(book);
        return book;
    }

    @Operation(summary = "Видалити книгу", description = "Видаляє книгу за її ID.")
    @ApiResponse(responseCode = "200", description = "Книга успішно видалена")
    @ApiResponse(responseCode = "404", description = "Книга не знайдена")
    @DeleteMapping("/{id}")
    public String deleteBook(@Parameter(description = "ID книги", required = true) @PathVariable Long id) {
        bookService.deleteBook(id);
        return "Book with ID " + id + " was deleted.";
    }
}
