package org.example.springlab2.Service;


import jakarta.annotation.PostConstruct;
import org.example.springlab2.Models.KeyWords;
import org.example.springlab2.Repositories.BookRepository;
import org.example.springlab2.Repositories.KeyWordsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KeyWordsService {

    @Autowired
    private final KeyWordsRepository keyWordsRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    public KeyWordsService(KeyWordsRepository keyWordsRepository) {
        this.keyWordsRepository = keyWordsRepository;
    }

    public List<KeyWords> findAllKeyWords() {
        return (List<KeyWords>) keyWordsRepository.findAll();
    }

    public KeyWords findById(Long id) {
        return keyWordsRepository.findById(id).orElse(null);
    }

    @PostConstruct
    public void setUpGenres() {
        KeyWords keyWord1 = new KeyWords(1L, "Science Fiction", null);
        KeyWords keyWord2 = new KeyWords(2L, "Fantasy", null);
        KeyWords keyWord3 = new KeyWords(3L, "Mystery", null);

        keyWord1.setBooks(bookRepository.findByKeyWord(keyWord1));
        keyWord2.setBooks(bookRepository.findByKeyWord(keyWord2));
        keyWord3.setBooks(bookRepository.findByKeyWord(keyWord3));

        keyWordsRepository.save(keyWord1);
        keyWordsRepository.save(keyWord2);
        keyWordsRepository.save(keyWord3);
    }
}