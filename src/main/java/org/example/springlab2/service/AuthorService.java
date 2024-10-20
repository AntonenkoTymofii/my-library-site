package org.example.springlab2.service;

import jakarta.persistence.EntityNotFoundException;
import org.example.springlab2.models.Author;
import org.example.springlab2.repositories.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public List<Author> findAllAuthors() {
        return (List<Author>) authorRepository.findAll();
    }

    public List<Author> searchAuthors(String name) {
        return authorRepository.findByFirstNameContaining(name);
    }

    public Author findById(Long id) {
        return authorRepository.findById(id).orElse(null);
    }

    public void updateAuthor(Author author) {
        Author existingAuthor = authorRepository.findById(author.getId())
                .orElseThrow(() -> new EntityNotFoundException("Author not found"));

        existingAuthor.setFirstName(author.getFirstName());
        existingAuthor.setLastName(author.getLastName());

        authorRepository.save(existingAuthor);
    }

    public Author findByFirstName(String firstName) {
        return authorRepository.findByFirstName(firstName);
    }

    public Author findByLastName(String lastName) {
        return authorRepository.findByLastName(lastName);
    }

    public void deleteAuthor(Long id) {
        authorRepository.deleteById(id);
    }

}
