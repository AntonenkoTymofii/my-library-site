package org.example.springlab2.Controllers;

import org.example.springlab2.Models.Author;
import org.example.springlab2.Models.Book;
import org.example.springlab2.Service.AuthorService;
import org.example.springlab2.Service.BookService;
import org.example.springlab2.Service.KeyWordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class AdminController {

    private final BookService bookService;
    private final AuthorService authorService;
    private final KeyWordsService keyWordsService;

    @Autowired
    public AdminController(BookService bookService, AuthorService authorService, KeyWordsService keyWordsService) {
        this.bookService = bookService;
        this.authorService = authorService;
        this.keyWordsService = keyWordsService;
    }

    @GetMapping("/admin/authors")
    public String viewAuthors(Model model) {
        model.addAttribute("authors", authorService.findAllAuthors());
        return "admin/authors";
    }

    @GetMapping("/admin/books")
    public String viewBooks(Model model) {
        model.addAttribute("books", bookService.findAllBooks());
        return "admin/books";
    }

    @GetMapping("/admin/search")
    public String search(@RequestParam("query") String query, Model model) {
        model.addAttribute("books", bookService.searchBooks(query));
        model.addAttribute("authors", authorService.searchAuthors(query));
        return "admin/search_results";
    }

    @GetMapping("/admin/authors/edit")
    public String editAuthor(@RequestParam("id") Long id, Model model) {
        model.addAttribute("author", authorService.findById(id));
        return "admin/edit_author";
    }

    @PostMapping("/admin/authors/update")
    public String updateAuthor(@ModelAttribute("author") Author author) {
        authorService.updateAuthor(author);
        return "redirect:/admin/authors";
    }

    @GetMapping("/admin/books/edit")
    public String editBook(@RequestParam("id") Long id, Model model) {
        model.addAttribute("book", bookService.findById(id));
        model.addAttribute("authors", authorService.findAllAuthors());
        model.addAttribute("allGenres", keyWordsService.findAllKeyWords());
        return "admin/edit_book";
    }

    @PostMapping("/admin/books/update")
    public String updateBook(@ModelAttribute("book") Book book) {
        bookService.updateBook(book);
        return "redirect:/admin/books";
    }
}
