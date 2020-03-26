package com.rfbsoft.service;

import com.rfbsoft.model.Neighborhood;
import com.rfbsoft.repository.NeighborhoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class NeighborhoodService {
    @Autowired
    NeighborhoodRepository repo;

    public List<Neighborhood> findAll() {
        return repo.findAll();
    }


    public <S extends Neighborhood> S save(S entity) {
        return repo.save(entity);
    }


    public Optional<Neighborhood> findById(Long id) {
        return repo.findById(id);
    }


    public boolean existsById(Long id) {
        return repo.existsById(id);
    }


    public void deleteById(Long id) {
        repo.deleteById(id);
    }


    public void delete(Neighborhood entity) {
        repo.delete(entity);

    }


}
