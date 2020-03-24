package com.rfbsoft.controller;

import com.rfbsoft.model.Category;
import com.rfbsoft.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(CategoryController.CONTROLLER_PATH)
public class CategoryController {
    public static final String CONTROLLER_PATH = "api/v0/categories";

    @Autowired
    CategoryRepository repo;

    @GetMapping
    public List<Category> get() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity getOne(@PathVariable Long id) {
        Optional optionalPhone = repo.findById(id);
        return ResponseEntity.ok(optionalPhone);
    }

    @PostMapping()
    public ResponseEntity add(@Valid @RequestBody Category object) {


        return new ResponseEntity(repo.save(object), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity update(@Valid @RequestBody Category phone) {
        return new ResponseEntity(repo.save(phone), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@Valid @RequestBody Category phone) {
        repo.delete(phone);
        return ResponseEntity.ok("deleted");
    }

}




