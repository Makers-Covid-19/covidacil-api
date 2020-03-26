package com.rfbsoft.controller;

import com.rfbsoft.exception.CategoryNotFound;
import com.rfbsoft.model.Category;
import com.rfbsoft.response.SuccesResponse;
import com.rfbsoft.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(CategoryController.CONTROLLER_PATH)
public class CategoryController {
    public static final String CONTROLLER_PATH = "api/v0/categories";

    @Autowired
    CategoryService repo;

    @GetMapping
    public ResponseEntity get() {
        return ResponseEntity.ok(new SuccesResponse(repo.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity getOne(@PathVariable Long id) {

        boolean exist = repo.existsById(id);
        if (!exist)
            return new ResponseEntity(new CategoryNotFound(id), HttpStatus.NOT_FOUND);
        Category category = repo.findById(id).get();

        return ResponseEntity.ok(new SuccesResponse(category));
    }

    @PostMapping()
    public ResponseEntity add(@Valid @RequestBody Category object) {


        return ResponseEntity.ok(new SuccesResponse(repo.save(object)));
    }

    @PutMapping
    public ResponseEntity update(@Valid @RequestBody Category phone) {

        return ResponseEntity.ok(new SuccesResponse(repo.save(phone)));
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@Valid @RequestBody Category phone) {
        repo.delete(phone);
        return ResponseEntity.ok(new SuccesResponse("Succesfuly deleted"));
    }

}




