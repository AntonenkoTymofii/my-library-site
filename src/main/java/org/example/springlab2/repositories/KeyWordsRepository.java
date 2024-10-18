package org.example.springlab2.repositories;

import org.example.springlab2.models.KeyWords;
import org.springframework.data.repository.CrudRepository;

public interface KeyWordsRepository extends CrudRepository<KeyWords, Long> {
    KeyWords findByWord(String word);
}
