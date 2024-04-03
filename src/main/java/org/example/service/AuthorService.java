package org.example.service;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.entity.AuthorEntity;
import org.example.repository.AuthorRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
@Validated
public class AuthorService {
    private final AuthorRepository repository;

    public List<AuthorEntity> findAll(){
        return repository.findAll();
    }

    public Optional<AuthorEntity> findById(Long id){
        return repository.findById(id);
    }

    public AuthorEntity save (@Valid AuthorEntity data){
        return repository.save(data);
    }

    public void update (AuthorEntity data){
        repository.save(data);
    }

    public void delete (Long id) {repository.deleteById(id);}
}
