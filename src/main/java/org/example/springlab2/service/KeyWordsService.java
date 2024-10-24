package org.example.springlab2.service;


import jakarta.persistence.EntityNotFoundException;
import org.example.springlab2.models.KeyWords;
import org.example.springlab2.repositories.KeyWordsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public List<KeyWords> findAll() {
        return (List<KeyWords>) keyWordsRepository.findAll();
    }

    public Optional<KeyWords> findById(Long id) {
        return keyWordsRepository.findById(id);
    }

    public KeyWords save(KeyWords keyWords) {
        return keyWordsRepository.save(keyWords);
    }

    public void deleteById(Long id) {
        keyWordsRepository.deleteById(id);
    }

    public List<KeyWords> searchByWord(String word) {
        return keyWordsRepository.findByWordContainingIgnoreCase(word);
    }

    public void updateKeyWord(KeyWords keyWords) {
        KeyWords existingKeyWord = keyWordsRepository.findById(keyWords.getId())
                .orElseThrow(() -> new EntityNotFoundException("KeyWord not found"));

        existingKeyWord.setWord(keyWords.getWord());
        keyWordsRepository.save(existingKeyWord);
    }

}