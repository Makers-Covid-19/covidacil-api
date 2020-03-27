package com.rfbsoft.v0.controller;


import com.rfbsoft.v0.Info;
import com.rfbsoft.v0.exception.ProvinceNotFound;
import com.rfbsoft.v0.model.Province;
import com.rfbsoft.v0.response.SuccesResponse;
import com.rfbsoft.v0.service.ProvinceService;
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
    public static final String CONTROLLER_PATH = "api/" + Info.VERSION + "/provinces";

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
