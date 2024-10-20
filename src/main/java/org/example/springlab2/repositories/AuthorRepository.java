package org.example.springlab2.repositories;

import org.example.springlab2.models.Author;
import org.example.springlab2.models.Book;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.List;

public interface AuthorRepository extends CrudRepository<Author, Long> {
    List<Author> findByFirstNameContaining(String firstName);
    Author findByFirstName(String firstName);
    Author findByLastName(String lastName);
}
