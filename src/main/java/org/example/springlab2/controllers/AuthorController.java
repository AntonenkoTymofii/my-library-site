package org.example.springlab2.controllers;

import org.example.springlab2.models.Author;
import org.example.springlab2.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/author")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @PreAuthorize("hasAnyRole('ADMIN', 'GUEST')")
    @GetMapping("/all")
    public String viewAuthors(Model model) {
        List<Author> authors = authorService.findAllAuthors();
        model.addAttribute("authors", authors);
        return "authors";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/add")
    public String addAuthorForm(Model model) {
        model.addAttribute("author", new Author());
        return "add_author";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/add")
    public String addAuthor(@ModelAttribute("author") Author author) {
        authorService.updateAuthor(author);
        return "redirect:/author/all";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/edit")
    public String editAuthorForm(@RequestParam("id") Long id, Model model) {
        Author author = authorService.findById(id);
        model.addAttribute("author", author);
        return "edit_author";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/update")
    public String updateAuthor(@ModelAttribute("author") Author author) {
        authorService.updateAuthor(author);
        return "redirect:/author/all";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/delete")
    public String deleteAuthor(@RequestParam("id") Long id) {
        authorService.deleteAuthor(id);
        return "redirect:/author/all";
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'GUEST')")
    @GetMapping("/search")
    public String searchAuthors(@RequestParam("query") String query, Model model) {
        List<Author> authors = authorService.searchAuthors(query);
        model.addAttribute("authors", authors);
        return "search_results_authors";
    }
}
