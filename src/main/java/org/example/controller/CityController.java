package org.example.controller;


import lombok.AllArgsConstructor;
import org.example.entity.CityEntity;
import org.example.response.BaseResponse;
import org.example.response.DataResponse;
import org.example.response.ListResponse;
import org.example.service.CityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/city")
@AllArgsConstructor
public class CityController {
    private final CityService service;

    @GetMapping("/all")
    public ResponseEntity<BaseResponse> getAll() {
        return ResponseEntity.ok(new ListResponse<CityEntity>(true, "Список городов: ", service.findAll()));
    }

    @GetMapping
    public ResponseEntity<BaseResponse> by_id(@RequestParam Long id) {
        try {
            return ResponseEntity.ok(new DataResponse<CityEntity>(true, "Найден следующий город: ", service.findById(id).orElseThrow()));
        } catch (RuntimeException exception) {
            return ResponseEntity.ok(new BaseResponse(false, exception.getMessage()));
        }
    }


    @PostMapping
    public ResponseEntity<BaseResponse> save(@RequestBody CityEntity city) {
        try {
            return ResponseEntity.ok(new DataResponse<CityEntity>(true, "Город сохранён.", service.save(city)));
        } catch (RuntimeException exception) {
            return ResponseEntity.ok(new BaseResponse(false, exception.getMessage()));
        }
    }

    @PutMapping
    public ResponseEntity<BaseResponse> update(@RequestBody CityEntity city) {
        try {
            service.update(city);
            return ResponseEntity.ok(new BaseResponse(true, "Город сохранён."));
        } catch (RuntimeException exception) {
            return ResponseEntity.ok(new BaseResponse(false, exception.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BaseResponse> delete (@PathVariable Long id){
        try {
            return ResponseEntity.ok(new BaseResponse(true, "Город удалён"));
        } catch (RuntimeException exception) {
            return ResponseEntity.ok(new BaseResponse(false, exception.getMessage()));
        }
    }
}