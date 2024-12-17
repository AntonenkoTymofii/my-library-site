package org.example.MyLibrarySite.service;

import jakarta.persistence.EntityNotFoundException;
import org.example.MyLibrarySite.models.Author;
import org.example.MyLibrarySite.repositories.AuthorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class AuthorServiceTest {

    private AuthorRepository authorRepository;
    private AuthorService authorService;

    @BeforeEach
    void setUp() {
        authorRepository = mock(AuthorRepository.class);
        authorService = new AuthorService(authorRepository);
    }

    @Test
    void testFindAllAuthors() {
        Author author1 = new Author(1L, "John", "Doe", new ArrayList<>());
        Author author2 = new Author(2L, "Jane", "Smith", new ArrayList<>());
        when(authorRepository.findAll()).thenReturn(Arrays.asList(author1, author2));

        List<Author> authors = authorService.findAllAuthors();

        assertEquals(2, authors.size());
        verify(authorRepository, times(1)).findAll();
    }

    @Test
    void testSearchAuthors() {
        Author author = new Author(1L, "John", "Doe", new ArrayList<>());
        when(authorRepository.findByFirstNameContaining("John")).thenReturn(Arrays.asList(author));

        List<Author> authors = authorService.searchAuthors("John");

        assertEquals(1, authors.size());
        assertEquals("John", authors.get(0).getFirstName());
        verify(authorRepository, times(1)).findByFirstNameContaining("John");
    }

    @Test
    void testFindById_found() {
        Author author = new Author(1L, "John", "Doe", new ArrayList<>());
        when(authorRepository.findById(1L)).thenReturn(Optional.of(author));

        Author foundAuthor = authorService.findById(1L);

        assertNotNull(foundAuthor);
        assertEquals("John", foundAuthor.getFirstName());
        verify(authorRepository, times(1)).findById(1L);
    }

    @Test
    void testFindById_notFound() {
        when(authorRepository.findById(1L)).thenReturn(Optional.empty());

        Author foundAuthor = authorService.findById(1L);

        assertNull(foundAuthor);
        verify(authorRepository, times(1)).findById(1L);
    }

    @Test
    void testUpdateAuthor() {
        Author existingAuthor = new Author(1L, "John", "Doe", new ArrayList<>());
        Author updatedAuthor = new Author(1L, "Jane", "Doe", new ArrayList<>());

        when(authorRepository.findById(1L)).thenReturn(Optional.of(existingAuthor));

        authorService.updateAuthor(updatedAuthor);

        assertEquals("Jane", existingAuthor.getFirstName());
        verify(authorRepository, times(1)).save(existingAuthor);
    }

    @Test
    void testUpdateAuthor_notFound() {
        Author updatedAuthor = new Author(1L, "Jane", "Doe", new ArrayList<>());

        when(authorRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> authorService.updateAuthor(updatedAuthor));
    }

    @Test
    void testFindByFirstName() {
        Author author = new Author(1L, "John", "Doe", new ArrayList<>());
        when(authorRepository.findByFirstName("John")).thenReturn(author);

        Author foundAuthor = authorService.findByFirstName("John");

        assertNotNull(foundAuthor);
        assertEquals("John", foundAuthor.getFirstName());
        verify(authorRepository, times(1)).findByFirstName("John");
    }

    @Test
    void testFindByLastName() {
        Author author = new Author(1L, "John", "Doe", new ArrayList<>());
        when(authorRepository.findByLastName("Doe")).thenReturn(author);

        Author foundAuthor = authorService.findByLastName("Doe");

        assertNotNull(foundAuthor);
        assertEquals("Doe", foundAuthor.getLastName());
        verify(authorRepository, times(1)).findByLastName("Doe");
    }

    @Test
    void testDeleteAuthor() {
        doNothing().when(authorRepository).deleteById(1L);

        authorService.deleteAuthor(1L);

        verify(authorRepository, times(1)).deleteById(1L);
    }
}
