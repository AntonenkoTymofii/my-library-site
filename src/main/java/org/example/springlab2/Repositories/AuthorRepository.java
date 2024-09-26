package org.example.springlab2.Repositories;

import org.example.springlab2.Models.Author;
import org.example.springlab2.Models.Book;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.List;

public interface AuthorRepository extends CrudRepository<Author, Long> {
    List<Author> findByNameContaining(String name);
    Author findByName(String name);
    Author findByBooks(ArrayList<Book> books);
}
