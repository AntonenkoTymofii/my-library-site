package org.example.MyLibrarySite.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.example.MyLibrarySite.models.KeyWords;
import org.example.MyLibrarySite.service.KeyWordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/keywords")
@Tag(name = "KeyWords", description = "API для роботи з ключовими словами")
public class KeyWordApiController {

    @Autowired
    private KeyWordsService keyWordService;

    @Operation(summary = "Отримати всі ключові слова", description = "Повертає список усіх ключових слів.")
    @ApiResponse(responseCode = "200", description = "Список ключових слів успішно отримано")
    @GetMapping
    public List<KeyWords> getAllKeyWords() {
        return keyWordService.findAllKeyWords();
    }

    @Operation(summary = "Отримати ключове слово за ID", description = "Повертає ключове слово за його ID.")
    @ApiResponse(responseCode = "200", description = "Ключове слово знайдено")
    @ApiResponse(responseCode = "404", description = "Ключове слово не знайдено")
    @GetMapping("/{id}")
    public KeyWords getKeyWordById(@Parameter(description = "ID ключового слова", required = true) @PathVariable Long id) {
        return keyWordService.findById(id).get();
    }

    @Operation(summary = "Додати нове ключове слово", description = "Створює нове ключове слово.")
    @ApiResponse(responseCode = "201", description = "Ключове слово успішно створено")
    @PostMapping
    public KeyWords createKeyWord(@RequestBody KeyWords keyWord) {
        keyWordService.save(keyWord);
        return keyWord;
    }

    @Operation(summary = "Оновити ключове слово", description = "Оновлює ключове слово за його ID.")
    @ApiResponse(responseCode = "200", description = "Ключове слово успішно оновлено")
    @PutMapping("/{id}")
    public KeyWords updateKeyWord(
            @Parameter(description = "ID ключового слова", required = true) @PathVariable Long id,
            @RequestBody KeyWords keyWord) {
        keyWord.setId(id);
        keyWordService.updateKeyWord(keyWord);
        return keyWord;
    }

    @Operation(summary = "Видалити ключове слово", description = "Видаляє ключове слово за його ID.")
    @ApiResponse(responseCode = "200", description = "Ключове слово успішно видалено")
    @ApiResponse(responseCode = "404", description = "Ключове слово не знайдено")
    @DeleteMapping("/{id}")
    public String deleteKeyWord(@Parameter(description = "ID ключового слова", required = true) @PathVariable Long id) {
        keyWordService.deleteById(id);
        return "KeyWord with ID " + id + " was deleted.";
    }
}
