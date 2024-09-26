package org.example.springlab2.Repositories;

import org.example.springlab2.Models.KeyWords;
import org.springframework.data.repository.CrudRepository;

public interface KeyWordsRepository extends CrudRepository<KeyWords, Long> {
    KeyWords findByWord(String word);
}
