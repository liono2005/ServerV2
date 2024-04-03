package org.example.controller;


import lombok.AllArgsConstructor;
import org.example.entity.GenreEntity;
import org.example.response.BaseResponse;
import org.example.response.DataResponse;
import org.example.response.ListResponse;
import org.example.service.GenreService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/genre")
@AllArgsConstructor
public class GenreController {
    private final GenreService service;

    @GetMapping("/all")
    public ResponseEntity<BaseResponse> getAll() {
        return ResponseEntity.ok(new ListResponse<GenreEntity>(true, "Список жанров: ", service.findAll()));
    }

    @GetMapping
    public ResponseEntity<BaseResponse> by_id(@RequestParam Long id) {
        try {
            return ResponseEntity.ok(new DataResponse<GenreEntity>(true, "Найден следующий жанр: ", service.findById(id).orElseThrow()));
        } catch (RuntimeException exception) {
            return ResponseEntity.ok(new BaseResponse(false, exception.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<BaseResponse> save(@RequestBody GenreEntity genre) {
        try {
            return ResponseEntity.ok(new DataResponse<GenreEntity>(true, "Жанр сохранён.", service.save(genre)));
        } catch (RuntimeException exception) {
            return ResponseEntity.ok(new BaseResponse(false, exception.getMessage()));
        }
    }

    @PutMapping
    public ResponseEntity<BaseResponse> update(@RequestBody GenreEntity genre) {
        try {
            service.update(genre);
            return ResponseEntity.ok(new BaseResponse(true, "Жанр сохранён."));
        } catch (RuntimeException exception) {
            return ResponseEntity.ok(new BaseResponse(false, exception.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse> delete (@PathVariable Long id){
        try {
            return ResponseEntity.ok(new BaseResponse(true, "Жанр удалён"));
        } catch (RuntimeException exception) {
            return ResponseEntity.ok(new BaseResponse(false, exception.getMessage()));
        }
    }

}