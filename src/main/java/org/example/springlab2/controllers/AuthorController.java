package org.example.springlab2.controllers;

import jakarta.annotation.security.RolesAllowed;
import org.example.springlab2.models.Author;
import org.example.springlab2.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/author")
public class AuthorController {
    @Autowired
    private AuthorService authorService;

    @RolesAllowed({"ADMIN", "GUEST"})
    @GetMapping("/all")
    public String viewAuthors(Model model) {
        model.addAttribute("authors", authorService.findAllAuthors());
        return "authors";
    }

    @RolesAllowed({"ADMIN", "GUEST"})
    @GetMapping("/search")
    public String search(@RequestParam("query") String query, Model model) {
        model.addAttribute("authors", authorService.searchAuthors(query));
        return "search_results";
    }


    @RolesAllowed("ADMIN")
    @GetMapping("/edit")
    public String editAuthor(@RequestParam("id") Long id, Model model) {
        model.addAttribute("author", authorService.findById(id));
        return "edit_author";
    }

    @RolesAllowed("ADMIN")
    @PostMapping("/update")
    public String updateAuthor(@ModelAttribute("author") Author author) {
        authorService.updateAuthor(author);
        return "redirect:authors";
    }
}
