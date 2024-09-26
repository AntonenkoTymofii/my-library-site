package org.example.springlab2.Repositories;

import org.example.springlab2.Models.Author;
import org.example.springlab2.Models.Book;
import org.example.springlab2.Models.KeyWords;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookRepository extends CrudRepository<Book, Long> {
    Book findByTitle(String title);
    List<Book> findByAuthor(Author author);
    List<Book> findByKeyWord(KeyWords keyword);
    List<Book> findByTitleContainingOrGenresNameContaining(String titleQuery, String genreQuery);
}
