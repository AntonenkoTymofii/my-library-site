package org.example.springlab2.service;


import org.example.springlab2.models.KeyWords;
import org.example.springlab2.repositories.KeyWordsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KeyWordsService {

    @Autowired
    private final KeyWordsRepository keyWordsRepository;

    @Autowired
    public KeyWordsService(KeyWordsRepository keyWordsRepository) {
        this.keyWordsRepository = keyWordsRepository;
    }

    public List<KeyWords> findAllKeyWords() {
        return (List<KeyWords>) keyWordsRepository.findAll();
    }

}