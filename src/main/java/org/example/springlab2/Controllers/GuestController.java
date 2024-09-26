package org.example.springlab2.Controllers;

import org.example.springlab2.Service.AuthorService;
import org.example.springlab2.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class GuestController {

    private final BookService bookService;
    private final AuthorService authorService;

    @Autowired
    public GuestController(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    @GetMapping("/guest/authors")
    public String viewAuthors(Model model) {
        model.addAttribute("authors", authorService.findAllAuthors());
        return "guest/authors";
    }

    @GetMapping("/guest/books")
    public String viewBooks(Model model) {
        model.addAttribute("books", bookService.findAllBooks());
        return "guest/books";
    }

    @GetMapping("/guest/search")
    public String search(@RequestParam("query") String query, Model model) {
        model.addAttribute("books", bookService.searchBooks(query));
        model.addAttribute("authors", authorService.searchAuthors(query));
        return "guest/search_results";
    }
}