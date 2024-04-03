package org.example.service;


import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.entity.CityEntity;
import org.example.repository.CityRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
@Validated
public class CityService {
    private final CityRepository repository;

    public List<CityEntity> findAll(){
        return repository.findAll();
    }

    public Optional<CityEntity> findById(Long id){
        return repository.findById(id);
    }

    public CityEntity save (@Valid CityEntity data){
        return repository.save(data);
    }

    public void update (CityEntity data){
        repository.save(data);
    }

    public void delete (Long id) {repository.deleteById(id);}
}
