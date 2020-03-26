package com.rfbsoft.service;

import com.rfbsoft.model.District;
import com.rfbsoft.repository.DistrictRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class DistrictService {
    @Autowired
    DistrictRepository repo;

    public List<District> findAll() {
        return repo.findAll();
    }


    public <S extends District> S save(S entity) {
        return repo.save(entity);
    }


    public Optional<District> findById(Long id) {
        return repo.findById(id);
    }


    public boolean existsById(Long id) {
        return repo.existsById(id);
    }


    public void deleteById(Long id) {
        repo.deleteById(id);
    }


    public void delete(District entity) {
        repo.delete(entity);

    }
}
