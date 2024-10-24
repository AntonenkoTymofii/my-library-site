package org.example.springlab2.repositories;

import org.example.springlab2.models.KeyWords;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface KeyWordsRepository extends CrudRepository<KeyWords, Long> {
    KeyWords findByWord(String word);
    List<KeyWords> findByWordContainingIgnoreCase(String word);

}
