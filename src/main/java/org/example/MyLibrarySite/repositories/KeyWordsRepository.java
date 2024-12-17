package org.example.MyLibrarySite.repositories;

import org.example.MyLibrarySite.models.KeyWords;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface KeyWordsRepository extends CrudRepository<KeyWords, Long> {
    KeyWords findByWord(String word);
    List<KeyWords> findByWordContainingIgnoreCase(String word);
}
