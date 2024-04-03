package org.example.controller;


import lombok.AllArgsConstructor;
import org.example.entity.AuthorEntity;
import org.example.response.BaseResponse;
import org.example.response.DataResponse;
import org.example.response.ListResponse;
import org.example.service.AuthorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/author")
@AllArgsConstructor
public class AuthorController {
    private final AuthorService service;

    @GetMapping("/all")
    public ResponseEntity<BaseResponse> getAll() {
        return ResponseEntity.ok(new ListResponse<AuthorEntity>(true, "Список авторов: ", service.findAll()));
    }

    @GetMapping
    public ResponseEntity<BaseResponse> by_id(@RequestParam Long id) {
        try {
            return ResponseEntity.ok(
                    new DataResponse<AuthorEntity>(true, "Найден следующий автор", service.findById(id).orElseThrow()));
        } catch (RuntimeException exception) {
            return ResponseEntity.ok(
                    new BaseResponse(false, exception.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<BaseResponse> save(@RequestBody AuthorEntity author) {
        try {
            return ResponseEntity.ok(new DataResponse<AuthorEntity>(true, "Автор сохранён.", service.save(author)));
        } catch (RuntimeException exception) {
            return ResponseEntity.ok(new BaseResponse(false, exception.getMessage()));
        }
    }

    @PutMapping
    public ResponseEntity<BaseResponse> update(@RequestBody AuthorEntity author) {
        try {
            service.update(author);
            return ResponseEntity.ok(new BaseResponse(true, "Автор сохранён."));
        } catch (RuntimeException exception) {
            return ResponseEntity.ok(new BaseResponse(false, exception.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse> delete(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(new BaseResponse(true, "Автор удалён"));
        } catch (RuntimeException exception) {
            return ResponseEntity.ok(new BaseResponse(false, exception.getMessage()));
        }
    }
}
