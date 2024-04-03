package org.example.controller;


import lombok.AllArgsConstructor;
import org.example.entity.PublisherEntity;
import org.example.response.BaseResponse;
import org.example.response.DataResponse;
import org.example.response.ListResponse;
import org.example.service.PublisherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/publisher")
@AllArgsConstructor
public class PublisherController {
    private final PublisherService service;

    @GetMapping("/all")
    public ResponseEntity<BaseResponse> getAll() {
        return ResponseEntity.ok(new ListResponse<PublisherEntity>(true, "Список издателей: ", service.findAll()));
    }

    @GetMapping
    public ResponseEntity<BaseResponse> by_id(@RequestParam Long id) {
        try {
            return ResponseEntity.ok(new DataResponse<PublisherEntity>(true, "Найден следующий издатель: ", service.findById(id).orElseThrow()));
        } catch (RuntimeException exception) {
            return ResponseEntity.ok(new BaseResponse(false, exception.getMessage()));
        }
    }

    @PostMapping
    public ResponseEntity<BaseResponse> save(@RequestBody PublisherEntity publisher) {
        try {
            return ResponseEntity.ok(new DataResponse<PublisherEntity>(true, "Издатель сохранён.", service.save(publisher)));
        } catch (RuntimeException exception) {
            return ResponseEntity.ok(new BaseResponse(false, exception.getMessage()));
        }
    }

    @PutMapping
    public ResponseEntity<BaseResponse> update(@RequestBody PublisherEntity publisher) {
        try {
            service.update(publisher);
            return ResponseEntity.ok(new BaseResponse(true, "Издатель сохранён."));
        } catch (RuntimeException exception) {
            return ResponseEntity.ok(new BaseResponse(false, exception.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse> delete (@PathVariable Long id){
        try {
            return ResponseEntity.ok(new BaseResponse(true, "Издатель удалён"));
        } catch (RuntimeException exception) {
            return ResponseEntity.ok(new BaseResponse(false, exception.getMessage()));
        }
    }
}
