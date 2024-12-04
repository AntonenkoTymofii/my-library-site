package org.example.MyLibrarySite.controllers;

import org.example.MyLibrarySite.models.Author;
import org.example.MyLibrarySite.service.AuthorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class AuthorControllerTest {

    @Mock
    private AuthorService authorService;

    @Mock
    private Model model;

    @InjectMocks
    private AuthorController authorController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testViewAuthors() {
        Author author1 = new Author();
        author1.setFirstName("John");
        author1.setLastName("Doe");

        Author author2 = new Author();
        author2.setFirstName("Jane");
        author2.setLastName("Smith");

        List<Author> authors = Arrays.asList(author1, author2);
        when(authorService.findAllAuthors()).thenReturn(authors);

        String viewName = authorController.viewAuthors(model);

        verify(model).addAttribute("authors", authors);
        assertEquals("authors", viewName);
    }

    @Test
    void testAddAuthorForm() {
        String viewName = authorController.addAuthorForm(model);

        verify(model).addAttribute(eq("author"), any(Author.class));
        assertEquals("add_author", viewName);
    }

    @Test
    void testAddAuthor() {
        Author author = new Author();
        author.setFirstName("John");
        author.setLastName("Doe");

        String redirectView = authorController.addAuthor(author);

        verify(authorService).updateAuthor(any(Author.class));
        assertEquals("redirect:/author/all", redirectView);
    }

    @Test
    void testEditAuthorForm() {
        Long authorId = 1L;
        Author author = new Author();
        author.setId(authorId);
        author.setFirstName("John");
        author.setLastName("Doe");

        when(authorService.findById(authorId)).thenReturn(author);

        String viewName = authorController.editAuthorForm(authorId, model);

        verify(model).addAttribute("author", author);
        assertEquals("edit_author", viewName);
    }

    @Test
    void testUpdateAuthor() {
        Author author = new Author();
        author.setId(1L);
        author.setFirstName("John");
        author.setLastName("Doe");

        String redirectView = authorController.updateAuthor(author);

        verify(authorService).updateAuthor(author);
        assertEquals("redirect:/author/all", redirectView);
    }

    @Test
    void testDeleteAuthor() {
        Long authorId = 1L;

        String redirectView = authorController.deleteAuthor(authorId);

        verify(authorService).deleteAuthor(authorId);
        assertEquals("redirect:/author/all", redirectView);
    }

    @Test
    void testSearchAuthors() {
        String query = "John";
        Author author1 = new Author();
        author1.setFirstName("John");
        author1.setLastName("Doe");

        List<Author> authors = Arrays.asList(author1);
        when(authorService.searchAuthors(query)).thenReturn(authors);

        String viewName = authorController.searchAuthors(query, model);

        verify(model).addAttribute("authors", authors);
        assertEquals("search_results_authors", viewName);
    }
}
