package com.rfbsoft.service;

import com.rfbsoft.model.Category;
import com.rfbsoft.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository repo;


    public List findAll() {
        return repo.findAll();
    }

    public <S extends Category> S save(S entity) {
        return repo.save(entity);
    }


    public Optional<Category> findById(Long id) {
        return repo.findById(id);
    }


    public boolean existsById(Long id) {
        return repo.existsById(id);
    }


    public void deleteById(Long id) {
        repo.deleteById(id);
    }


    public void delete(Category entity) {
        repo.delete(entity);

    }
}
