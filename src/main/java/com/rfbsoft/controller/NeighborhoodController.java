package com.rfbsoft.controller;

import com.rfbsoft.model.District;
import com.rfbsoft.model.Neighborhood;
import com.rfbsoft.repository.DistrictRepository;
import com.rfbsoft.repository.NeighborhoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController

@RequestMapping(NeighborhoodController.CONTROLLER_PATH)
public class NeighborhoodController {
    public static final String CONTROLLER_PATH = "api/v0/neighborhoods";

    @Autowired
    NeighborhoodRepository repo;

    @Autowired
    DistrictRepository districtRepository;

    @GetMapping
    public List<Neighborhood> get() {
        return repo.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity getOne(@PathVariable Long id) {
        boolean exist = districtRepository.existsById(id);
        if (!exist) {
            return new ResponseEntity("District not found", HttpStatus.NOT_FOUND);
        }
        District district = districtRepository.findById(id).get();
        List neighborhoods = district.getNeighborhoods();
        return ResponseEntity.ok(neighborhoods);
    }

//    @PostMapping("/{districtId}")
//    public ResponseEntity add(@Valid @RequestBody Neighborhood object,@PathVariable Long districtId) {
//
//        boolean existDistrict = districtRepository.existsById(districtId);
//        if(!existDistrict){
//            return new ResponseEntity("District not found",HttpStatus.NOT_FOUND);
//        }
//        District province = districtRepository.findById(districtId).get();
//
//        object.setDistrict(province);
//        return new ResponseEntity(repo.save(object),HttpStatus.OK);
//    }
//
//    @PutMapping
//    public ResponseEntity update(@Valid @RequestBody Neighborhood phone) {
//        return new ResponseEntity(repo.save(phone), HttpStatus.OK);
//    }
//
//    @DeleteMapping
//    public ResponseEntity<?> delete(@Valid @RequestBody Neighborhood phone) {
//        repo.delete(phone);
//        return ResponseEntity.ok("deleted");
//    }

}