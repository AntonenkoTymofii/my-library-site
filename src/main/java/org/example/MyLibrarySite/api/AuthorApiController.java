package org.example.MyLibrarySite.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.example.MyLibrarySite.models.Author;
import org.example.MyLibrarySite.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/authors")
@Tag(name = "Authors", description = "API для роботи з авторами")
public class AuthorApiController {

    @Autowired
    private AuthorService authorService;

    @Operation(summary = "Отримати всіх авторів", description = "Повертає список усіх авторів з бази даних.")
    @ApiResponse(responseCode = "200", description = "Список авторів успішно отримано")
    @GetMapping
    public List<Author> getAllAuthors() {
        return authorService.findAllAuthors();
    }

    @Operation(summary = "Отримати автора за ID", description = "Повертає конкретного автора за його ID.")
    @ApiResponse(responseCode = "200", description = "Автор знайдений")
    @ApiResponse(responseCode = "404", description = "Автор не знайдений")
    @GetMapping("/{id}")
    public Author getAuthorById(@Parameter(description = "ID автора", required = true) @PathVariable Long id) {
        return authorService.findById(id);
    }

    @Operation(summary = "Додати нового автора", description = "Створює нового автора в базі даних.")
    @ApiResponse(responseCode = "201", description = "Автор успішно створений")
    @PostMapping
    public Author createAuthor(@RequestBody Author author) {
        authorService.addAuthor(author);
        return author;
    }

    @Operation(summary = "Оновити інформацію про автора", description = "Оновлює існуючого автора за його ID.")
    @ApiResponse(responseCode = "200", description = "Автор успішно оновлений")
    @PutMapping("/{id}")
    public Author updateAuthor(
            @Parameter(description = "ID автора, що оновлюється", required = true) @PathVariable Long id,
            @RequestBody Author author) {
        author.setId(id);
        authorService.updateAuthor(author);
        return author;
    }

    @Operation(summary = "Видалити автора", description = "Видаляє автора за його ID.")
    @ApiResponse(responseCode = "200", description = "Автор успішно видалений")
    @ApiResponse(responseCode = "404", description = "Автор не знайдений")
    @DeleteMapping("/{id}")
    public String deleteAuthor(@Parameter(description = "ID автора для видалення", required = true) @PathVariable Long id) {
        authorService.deleteAuthor(id);
        return "Author with ID " + id + " was deleted.";
    }
}
