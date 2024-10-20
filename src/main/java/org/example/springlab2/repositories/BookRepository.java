package org.example.springlab2.repositories;

import org.example.springlab2.models.Author;
import org.example.springlab2.models.Book;
import org.example.springlab2.models.KeyWords;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookRepository extends CrudRepository<Book, Long> {
    Book findByTitle(String title);
    List<Book> findByAuthor(Author author);
    List<Book> findByKeyWords(KeyWords keyword);
    List<Book> findByTitleContainingOrKeyWords_WordContaining(String title, String keyword);
    List<Book> findByYear(int year);
}
