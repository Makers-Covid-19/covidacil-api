package com.rfbsoft.service;

import com.rfbsoft.model.Phone;
import com.rfbsoft.repository.PhoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PhoneService {

    @Autowired
    PhoneRepository repo;

    public List<Phone> findAll() {
        return repo.findAll();
    }


    public <S extends Phone> S save(S entity) {
        return repo.save(entity);
    }


    public Optional<Phone> findById(Long id) {
        return repo.findById(id);
    }


    public boolean existsById(Long id) {
        return repo.existsById(id);
    }


    public void deleteById(Long id) {
        repo.deleteById(id);
    }


    public void delete(Phone entity) {
        repo.delete(entity);

    }
}
