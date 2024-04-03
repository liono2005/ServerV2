package org.example.service;


import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.entity.BookEntity;
import org.example.repository.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
@Validated
public class BookService {
    private final BookRepository repository;

    public List<BookEntity> findAll(){
        return repository.findAll();
    }

    public Optional<BookEntity> findById(Long id){
        return repository.findById(id);
    }

    public BookEntity save (@Valid BookEntity data){
        return repository.save(data);
    }

    public void update (BookEntity data){
        repository.save(data);
    }

    public void delete (Long id) {repository.deleteById(id);}
}
