package org.example.springlab2.controllers;

import jakarta.annotation.security.RolesAllowed;
import org.example.springlab2.models.Book;
import org.example.springlab2.service.BookService;
import org.example.springlab2.service.KeyWordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    private BookService bookService;
    @Autowired
    private KeyWordsService keyWordsService;

    @RolesAllowed({"ADMIN", "GUEST"})
    @GetMapping("/all")
    public String viewBooks(Model model) {
        model.addAttribute("books", bookService.findAllBooks());
        return "books";
    }

    @RolesAllowed({"ADMIN", "GUEST"})
    @GetMapping("/search")
    public String search(@RequestParam("query") String query, Model model) {
        model.addAttribute("books", bookService.searchBooks(query));
        return "search_results";
    }

    @RolesAllowed("ADMIN")
    @GetMapping("/edit")
    public String editBook(@RequestParam("id") Long id, Model model) {
        model.addAttribute("book", bookService.findById(id));
//        model.addAttribute("authors", authorService.findAllAuthors());
        model.addAttribute("allGenres", keyWordsService.findAllKeyWords());
        return "edit_book";
    }

    @RolesAllowed("ADMIN")
    @PostMapping("/update")
    public String updateBook(@ModelAttribute("book") Book book) {
        bookService.updateBook(book);
        return "redirect:books";
    }

}
