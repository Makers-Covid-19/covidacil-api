package com.rfbsoft.controller;


import com.rfbsoft.exception.ProvinceNotFound;
import com.rfbsoft.model.Province;
import com.rfbsoft.response.SuccesResponse;
import com.rfbsoft.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(ProvinceController.CONTROLLER_PATH)

public class ProvinceController {
    public static final String CONTROLLER_PATH = "api/v0/provinces";

    @Autowired
    ProvinceService repo;

    @GetMapping
    public ResponseEntity get() {

        return ResponseEntity.ok(new SuccesResponse(repo.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity getOne(@PathVariable Long id) {

        boolean existProvince = repo.existsById(id);
        if (!existProvince) {
            return new ResponseEntity(new ProvinceNotFound(id), HttpStatus.NOT_FOUND);
        }
        Province province = repo.findById(id).get();

        return ResponseEntity.ok(new SuccesResponse(province));
    }

//    @PostMapping
//    public ResponseEntity add(@Valid @RequestBody Province phone) {
//
//        return new ResponseEntity(repo.save(phone), HttpStatus.OK);
//    }
//
//    @PutMapping
//    public ResponseEntity update(@Valid @RequestBody Province phone) {
//        return new ResponseEntity(repo.save(phone), HttpStatus.OK);
//    }
//
//    @DeleteMapping
//    public ResponseEntity<?> delete(@Valid @RequestBody Province phone) {
//        repo.delete(phone);
//        return ResponseEntity.ok("deleted");
//    }
}
