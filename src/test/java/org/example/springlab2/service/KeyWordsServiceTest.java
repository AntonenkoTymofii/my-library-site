package org.example.springlab2.service;

import org.example.springlab2.models.KeyWords;
import org.example.springlab2.repositories.KeyWordsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class KeyWordsServiceTest {

    private KeyWordsRepository keyWordsRepository;
    private KeyWordsService keyWordsService;

    @BeforeEach
    void setUp() {
        keyWordsRepository = mock(KeyWordsRepository.class);
        keyWordsService = new KeyWordsService(keyWordsRepository);
    }

    @Test
    void testFindAllKeyWords() {
        KeyWords keyword1 = new KeyWords(1L, "Fiction", null);
        KeyWords keyword2 = new KeyWords(2L, "Science", null);

        when(keyWordsRepository.findAll()).thenReturn(Arrays.asList(keyword1, keyword2));

        List<KeyWords> keywords = keyWordsService.findAllKeyWords();

        assertEquals(2, keywords.size());
        assertEquals("Fiction", keywords.get(0).getWord());
        assertEquals("Science", keywords.get(1).getWord());
        verify(keyWordsRepository, times(1)).findAll();
    }
}
