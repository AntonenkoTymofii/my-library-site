package org.example.MyLibrarySite.controllers;

import org.example.MyLibrarySite.models.Book;
import org.example.MyLibrarySite.service.AuthorService;
import org.example.MyLibrarySite.service.BookService;
import org.example.MyLibrarySite.service.KeyWordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private KeyWordsService keyWordsService;

    // Показати всі книги
    @PreAuthorize("hasAnyRole('ADMIN', 'GUEST')")
    @GetMapping("/all")
    public String viewBooks(Model model) {
        model.addAttribute("books", bookService.findAllBooks());
        return "books";
    }

    // Показати форму для додавання нової книги
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/new")
    public String showAddForm(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("authors", authorService.findAllAuthors());
        model.addAttribute("allGenres", keyWordsService.findAllKeyWords());
        return "add_book";
    }

    // Додати нову книгу
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/add")
    public String addBook(@ModelAttribute("book") Book book) {
        bookService.updateBook(book);
        return "redirect:/book/all";
    }

    // Показати форму редагування книги
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/edit")
    public String showEditForm(@RequestParam("id") Long id, Model model) {
        model.addAttribute("book", bookService.findById(id));
        model.addAttribute("authors", authorService.findAllAuthors());
        model.addAttribute("allGenres", keyWordsService.findAllKeyWords());
        return "edit_book";
    }

    // Оновити книгу
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/update")
    public String updateBook(@ModelAttribute("book") Book book) {
        bookService.updateBook(book);
        return "redirect:/book/all";
    }

    // Видалити книгу
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/delete")
    public String deleteBook(@RequestParam("id") Long id) {
        bookService.deleteBook(id);
        return "redirect:/book/all";
    }

    // Пошук за назвою, автором, роком
    @PreAuthorize("hasAnyRole('ADMIN', 'GUEST')")
    @GetMapping("/search")
    public String searchBooks(@RequestParam(value = "title", required = false) String title,
                              @RequestParam(value = "author", required = false) String author,
                              @RequestParam(value = "year", required = false) Integer year,
                              @RequestParam(value = "keyword", required = false) String keyword, // Додай параметр для ключового слова
                              Model model) {
        List<Book> books = bookService.findAllBooks();

        if (keyword != null && !keyword.isEmpty()) {
            books = bookService.searchBooksByKeyWords(keyword); // Пошук за ключовими словами
        } else if (title != null && !title.isEmpty()) {
            books = bookService.searchBooks(title);
        } else if (author != null && !author.isEmpty()) {
            books = bookService.findBooksByAuthor(authorService.findByFirstName(author));
            if (books == null || books.isEmpty()) {
                books = bookService.findBooksByAuthor(authorService.findByLastName(author));
            }
        } else if (year != null) {
            books = bookService.findBooksByYear(year);
        }

        model.addAttribute("books", books);
        return "search_results_books";
    }
}
