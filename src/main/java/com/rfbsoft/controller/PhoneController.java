package com.rfbsoft.controller;


import com.rfbsoft.Utils;
import com.rfbsoft.model.*;
import com.rfbsoft.repository.*;
import com.rfbsoft.request.PhoneAddRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin(maxAge = 3600)
@RequestMapping(PhoneController.CONTROLLER_PATH)
public class PhoneController {
    public static final String CONTROLLER_PATH = "api/v0/phones";

    @Autowired
    PhoneRepository repo;

    @Autowired
    NeighborhoodRepository neighborhoodRepository;

    @Autowired
    DistrictRepository districtRepository;

    @Autowired
    ProvinceRepository provinceRepository;

    @Autowired
    CategoryRepository categoryRepository;


    @GetMapping
    public List<Phone> get() {
        return repo.findAll();
    }

    @GetMapping("/id")
    public ResponseEntity getOne(
            @RequestParam(required = false, name = "province_id") String provinceId,
            @RequestParam(required = false, name = "district_id") String districtId,
            @RequestParam(required = false, name = "neighborhood_id") String neighborhoodId) {

        Long id;

        if (neighborhoodId != null) {
            if (Utils.isValidLong(neighborhoodId)) {
                id = Long.valueOf(neighborhoodId);
            } else return ResponseEntity.ok("neighborhoodId not accepted");
            boolean exist = neighborhoodRepository.existsById(id);
            if (!exist) {
                return new ResponseEntity("Neighborhood not found", HttpStatus.NOT_FOUND);
            }
            Neighborhood neighborhood = neighborhoodRepository.findById(id).get();

            List phones = neighborhood.getPhones();
            return ResponseEntity.ok(phones);
        }
        if (districtId != null) {
            if (Utils.isValidLong(districtId)) {
                id = Long.valueOf(districtId);
            } else return ResponseEntity.ok("districtId not accepted");

            boolean exist = districtRepository.existsById(id);
            if (!exist) {
                return new ResponseEntity("District not found", HttpStatus.NOT_FOUND);
            }
            District neighborhood = districtRepository.findById(id).get();

            List phones = neighborhood.getPhones();
            return ResponseEntity.ok(phones);

        }
        if (provinceId != null) {
            if (Utils.isValidLong(provinceId)) {
                id = Long.valueOf(provinceId);
            } else return ResponseEntity.ok("provinceId not accepted");
            boolean exist = provinceRepository.existsById(id);
            if (!exist) {
                return new ResponseEntity("Province not found", HttpStatus.NOT_FOUND);
            }
            Province neighborhood = provinceRepository.findById(id).get();

            Set phones = neighborhood.getPhones();
            return ResponseEntity.ok(phones);

        }
        return ResponseEntity.ok("Please send Province - Distric or Neighborhood id");

    }

    @PostMapping("")
    public ResponseEntity add(@Valid @RequestBody PhoneAddRequest request) {

        Phone phone = new Phone();
        phone.setName(request.getName());
        phone.setNo(request.getPhoneNumber());

        boolean existCategory = categoryRepository.existsById(request.getCategoryId());
        if (!existCategory) {
            return new ResponseEntity("Category not found", HttpStatus.NOT_FOUND);
        }
        Category category = categoryRepository.findById(request.getCategoryId()).get();

        phone.setCategory(category);


        if (request.getNeighborhoodId() != null) {

            Long id = request.getNeighborhoodId();

            boolean exist = neighborhoodRepository.existsById(id);
            if (!exist) {
                return new ResponseEntity("Neighborhood not found", HttpStatus.NOT_FOUND);
            }
            Neighborhood neighborhood = neighborhoodRepository.findById(id).get();
            phone.setNeighborhood(neighborhood);
            District district = neighborhood.getDistrict();
            phone.setDistrict(district);
            Province province = district.getProvince();
            phone.setProvince(province);
            repo.save(phone);
            return ResponseEntity.ok(phone);
        }

        if (request.getDistrictId() != null) {

            Long id = request.getDistrictId();


            boolean exist = districtRepository.existsById(id);
            if (!exist) {
                return new ResponseEntity("District not found", HttpStatus.NOT_FOUND);
            }
            District district = districtRepository.findById(id).get();

            phone.setDistrict(district);
            Province province = district.getProvince();
            phone.setProvince(province);
            repo.save(phone);
            return ResponseEntity.ok(phone);
        }


        if (request.getProvinceId() != null) {

            Long id = request.getProvinceId();

            boolean exist = provinceRepository.existsById(id);
            if (!exist) {
                return new ResponseEntity("Province not found", HttpStatus.NOT_FOUND);
            }
            Province province = provinceRepository.findById(id).get();
            phone.setProvince(province);
            repo.save(phone);
            return ResponseEntity.ok(phone);
        }

        return ResponseEntity.ok("Province - District or NeighborHood id not Found");


//        boolean existNeighborhood = neighborhoodRepository.existsById(neighborhoodId);
//        if (!existNeighborhood) {
//            return new ResponseEntity("Neighborhood not found", HttpStatus.NOT_FOUND);
//        }
//        Neighborhood province = neighborhoodRepository.findById(neighborhoodId).get();
//
//        boolean existCategory = categoryRepository.existsById(categoryId);
//        if (!existCategory) {
//            return new ResponseEntity("Category not found", HttpStatus.NOT_FOUND);
//        }
//        Category category = categoryRepository.findById(categoryId).get();
//
//        object.setNeighborhood(province);
//        object.setCategory(category);
//        return new ResponseEntity(repo.save(object), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity update(@Valid @RequestBody Phone phone) {
        boolean exist = repo.existsById(phone.getId());
        if(!exist){
            ResponseEntity.ok("Phone not found");
        }
        Phone tmp = repo.findById(phone.getId()).get();
        tmp.setName(phone.getName());
        tmp.setNo(phone.getNo());
        return new ResponseEntity(repo.save(tmp), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@Valid @RequestBody Phone phone) {
        repo.delete(phone);
        return ResponseEntity.ok("deleted");
    }

}