package org.example.MyLibrarySite.service;

import jakarta.persistence.EntityNotFoundException;
import org.example.MyLibrarySite.models.KeyWords;
import org.example.MyLibrarySite.repositories.KeyWordsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
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

    @Test
    void testFindById() {
        KeyWords keyword = new KeyWords(1L, "History", null);
        when(keyWordsRepository.findById(1L)).thenReturn(Optional.of(keyword));

        Optional<KeyWords> result = keyWordsService.findById(1L);

        assertTrue(result.isPresent());
        assertEquals("History", result.get().getWord());
        verify(keyWordsRepository, times(1)).findById(1L);
    }

    @Test
    void testFindByIdNotFound() {
        when(keyWordsRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<KeyWords> result = keyWordsService.findById(1L);

        assertFalse(result.isPresent());
        verify(keyWordsRepository, times(1)).findById(1L);
    }

    @Test
    void testSave() {
        KeyWords keyword = new KeyWords(0L, "Math", null);
        KeyWords savedKeyword = new KeyWords(1L, "Math", null);

        when(keyWordsRepository.save(keyword)).thenReturn(savedKeyword);

        KeyWords result = keyWordsService.save(keyword);

        assertEquals(1L, result.getId());
        assertEquals("Math", result.getWord());
        verify(keyWordsRepository, times(1)).save(keyword);
    }

    @Test
    void testDeleteById() {
        Long id = 1L;

        doNothing().when(keyWordsRepository).deleteById(id);

        keyWordsService.deleteById(id);

        verify(keyWordsRepository, times(1)).deleteById(id);
    }

    @Test
    void testSearchByWord() {
        KeyWords keyword1 = new KeyWords(1L, "Science", null);
        KeyWords keyword2 = new KeyWords(2L, "Social Science", null);

        when(keyWordsRepository.findByWordContainingIgnoreCase("Science"))
                .thenReturn(Arrays.asList(keyword1, keyword2));

        List<KeyWords> result = keyWordsService.searchByWord("Science");

        assertEquals(2, result.size());
        assertEquals("Science", result.get(0).getWord());
        assertEquals("Social Science", result.get(1).getWord());
        verify(keyWordsRepository, times(1)).findByWordContainingIgnoreCase("Science");
    }

    @Test
    void testUpdateKeyWord() {
        KeyWords existingKeyword = new KeyWords(1L, "OldWord", null);
        KeyWords updatedKeyword = new KeyWords(1L, "NewWord", null);

        when(keyWordsRepository.findById(1L)).thenReturn(Optional.of(existingKeyword));
        when(keyWordsRepository.save(existingKeyword)).thenReturn(updatedKeyword);

        keyWordsService.updateKeyWord(updatedKeyword);

        ArgumentCaptor<KeyWords> captor = ArgumentCaptor.forClass(KeyWords.class);
        verify(keyWordsRepository).save(captor.capture());
        KeyWords savedKeyword = captor.getValue();

        assertEquals("NewWord", savedKeyword.getWord());
        assertEquals(1L, savedKeyword.getId());
    }

    @Test
    void testUpdateKeyWordNotFound() {
        KeyWords updatedKeyword = new KeyWords(1L, "NewWord", null);

        when(keyWordsRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(EntityNotFoundException.class, () ->
                keyWordsService.updateKeyWord(updatedKeyword));

        assertEquals("KeyWord not found", exception.getMessage());
        verify(keyWordsRepository, never()).save(any());
    }
}
