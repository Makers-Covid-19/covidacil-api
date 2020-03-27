package com.rfbsoft.v0.service;

import com.rfbsoft.v0.model.Province;
import com.rfbsoft.v0.repository.ProvinceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProvinceService {

    @Autowired
    ProvinceRepository repo;

    public List<Province> findAll() {
        return repo.findAll();
    }


    public <S extends Province> S save(S entity) {
        return repo.save(entity);
    }


    public Optional<Province> findById(Long id) {
        return repo.findById(id);
    }


    public boolean existsById(Long id) {
        return repo.existsById(id);
    }


    public void deleteById(Long id) {
        repo.deleteById(id);
    }


    public void delete(Province entity) {
        repo.delete(entity);

    }
}
