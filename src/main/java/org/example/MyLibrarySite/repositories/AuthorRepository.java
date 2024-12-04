package org.example.MyLibrarySite.repositories;

import org.example.MyLibrarySite.models.Author;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AuthorRepository extends CrudRepository<Author, Long> {
    List<Author> findByFirstNameContaining(String firstName);
    Author findByFirstName(String firstName);
    Author findByLastName(String lastName);
}
