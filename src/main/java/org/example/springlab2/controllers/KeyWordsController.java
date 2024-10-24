package org.example.springlab2.controllers;

import org.example.springlab2.models.KeyWords;
import org.example.springlab2.service.KeyWordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/keyword")
public class KeyWordsController {
    @Autowired
    private final KeyWordsService keyWordsService;

    @Autowired
    public KeyWordsController(KeyWordsService keyWordsService) {
        this.keyWordsService = keyWordsService;
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'GUEST')")
    @GetMapping("/all")
    public String listKeywords(@RequestParam(required = false) String search, Model model) {
        List<KeyWords> keywords;
        if (search != null && !search.isEmpty()) {
            keywords = keyWordsService.searchByWord(search);
            model.addAttribute("search", search);
        } else {
            keywords = keyWordsService.findAll();
        }
        model.addAttribute("keywords", keywords);
        return "keywords"; // Вкажіть шлях до вашого шаблону
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("keyword", new KeyWords());
        return "add_keyword"; // Вкажіть шлях до вашого шаблону
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/add")
    public String createKeyword(@ModelAttribute KeyWords keyword) {
        keyWordsService.save(keyword);
        return "redirect:/keyword/all";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/edit")
    public String showEditForm(@RequestParam("id") Long id, Model model) {
        Optional<KeyWords> keyword = keyWordsService.findById(id);
        if (keyword.isPresent()) {
            model.addAttribute("keyword", keyword.get());
            return "edit_keyword"; // Вкажіть шлях до вашого шаблону
        }
        return "redirect:/keyword/all"; // Якщо не знайдено, перенаправлення
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/update")
    public String updateKeyword(@ModelAttribute KeyWords keyword) {
        keyWordsService.updateKeyWord(keyword);
        return "redirect:/keyword/all";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/delete")
    public String deleteKeyword(@RequestParam("id") Long id) {
        keyWordsService.deleteById(id);
        return "redirect:/keyword/all";
    }
}

