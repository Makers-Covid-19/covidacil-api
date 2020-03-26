package com.rfbsoft.controller;


import com.rfbsoft.exception.*;
import com.rfbsoft.model.*;
import com.rfbsoft.request.PhoneAddRequest;
import com.rfbsoft.response.SuccesResponse;
import com.rfbsoft.service.*;
import com.rfbsoft.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(PhoneController.CONTROLLER_PATH)
public class PhoneController {
    public static final String CONTROLLER_PATH = "api/v0/phones";

    @Autowired
    PhoneService repo;

    @Autowired
    NeighborhoodService neighborhoodRepository;

    @Autowired
    DistrictService districtRepository;

    @Autowired
    ProvinceService provinceRepository;

    @Autowired
    CategoryService categoryRepository;

    @GetMapping
    public ResponseEntity get() {
        return ResponseEntity.ok(new SuccesResponse(repo.findAll()));
    }

    @GetMapping("/id")
    public ResponseEntity getOne(
            @RequestParam(required = false, name = "province_id") String provinceId,
            @RequestParam(required = false, name = "district_id") String districtId,
            @RequestParam(required = false, name = "neighborhood_id") String neighborhoodId) {

        Long id = null;
        List phones = new ArrayList();

        if (neighborhoodId != null) {
            if (Utils.isValidLong(neighborhoodId)) {
                id = Long.valueOf(neighborhoodId);
            } else new ResponseEntity(new BadRequestFound( neighborhoodId + " Not Valid Long"),
                    HttpStatus.BAD_REQUEST);
            boolean exist = neighborhoodRepository.existsById(id);
            if (!exist) {
                return new ResponseEntity(new NeighborhoodNotFound(id), HttpStatus.NOT_FOUND);
            }

            Neighborhood neighborhood = neighborhoodRepository.findById(id).get();
            neighborhood.getPhones().forEach(phone -> phones.add(phone));

            District district = neighborhood.getDistrict();

            district.getPhones().stream()
                    .filter(phone -> phone.getNeighborhood() == null)
                    .forEach(phone -> phones.add(phone));

            Province province = district.getProvince();

            province.getPhones().stream()
                    .filter(phone -> phone.getDistrict() == null)
                    .forEach(phone -> phones.add(phone));

            return ResponseEntity.ok(new SuccesResponse(phones));
        }
        if (districtId != null) {
            if (Utils.isValidLong(districtId)) {
                id = Long.valueOf(districtId);
            } else
                new ResponseEntity(new BadRequestFound( districtId + " Not Valid Long"),
                        HttpStatus.BAD_REQUEST);

            boolean exist = districtRepository.existsById(id);
            if (!exist) {
                return new ResponseEntity(new DistrictNotFound(id), HttpStatus.NOT_FOUND);
            }
            District district = districtRepository.findById(id).get();

            district.getPhones().forEach(phone -> phones.add(phone));

            Province province = district.getProvince();
            province.getPhones().stream()
                    .filter(phone -> phone.getDistrict() == null)
                    .forEach(phone -> phones.add(phone));

            return ResponseEntity.ok(new SuccesResponse(phones));

        }
        if (provinceId != null) {
            if (Utils.isValidLong(provinceId)) {
                id = Long.valueOf(provinceId);
            } else new ResponseEntity(new BadRequestFound(provinceId + " Not Valid Long"),
                    HttpStatus.BAD_REQUEST);
            boolean exist = provinceRepository.existsById(id);
            if (!exist) {
                return new ResponseEntity(new ProvinceNotFound(id), HttpStatus.NOT_FOUND);
            }
            Province province = provinceRepository.findById(id).get();

            province.getPhones().forEach(phone -> phones.add(phone));
            return ResponseEntity.ok(new SuccesResponse(phones));

        }
//        "Please send Province - Distric or Neighborhood id"
        return new ResponseEntity(new BadRequestFound(
                "Please send Province - Distric or Neighborhood id"),
                HttpStatus.BAD_REQUEST);

    }

    @PostMapping("")
    public ResponseEntity add(@Valid @RequestBody PhoneAddRequest request) {

        Phone phone = new Phone();
        phone.setName(request.getName());
        phone.setNo(request.getPhoneNumber());

        boolean existCategory = categoryRepository.existsById(request.getCategoryId());
        if (!existCategory) {
            return new ResponseEntity(new CategoryNotFound(request.getCategoryId()), HttpStatus.NOT_FOUND);
        }
        Category category = categoryRepository.findById(request.getCategoryId()).get();

        phone.setCategory(category);


        if (request.getNeighborhoodId() != null) {


            Long id = request.getNeighborhoodId();

            boolean exist = neighborhoodRepository.existsById(id);
            if (!exist) {
                return new ResponseEntity(new NeighborhoodNotFound(id), HttpStatus.NOT_FOUND);
            }

            Neighborhood neighborhood = neighborhoodRepository.findById(id).get();
            District district = neighborhood.getDistrict();
            Province province = district.getProvince();

            phone.setNeighborhood(neighborhood);
            phone.setDistrict(district);
            phone.setProvince(province);
            repo.save(phone);
            return ResponseEntity.ok(new SuccesResponse(phone));


        }

        if (request.getDistrictId() != null) {


            Long id = request.getDistrictId();


            boolean exist = districtRepository.existsById(id);
            if (!exist) {
                return new ResponseEntity(new DistrictNotFound(id), HttpStatus.NOT_FOUND);
            }
            District district = districtRepository.findById(id).get();
            Province province = district.getProvince();

            phone.setDistrict(district);
            phone.setProvince(province);
            repo.save(phone);
            return ResponseEntity.ok(new SuccesResponse(phone));

        }


        if (request.getProvinceId() != null) {


            Long id = request.getProvinceId();

            boolean exist = provinceRepository.existsById(id);
            if (!exist) {
                return new ResponseEntity(new ProvinceNotFound(id), HttpStatus.NOT_FOUND);
            }
            Province province = provinceRepository.findById(id).get();
            phone.setProvince(province);
            repo.save(phone);
            return ResponseEntity.ok(new SuccesResponse(phone));

        }


        return new ResponseEntity(new BadRequestFound("Province - District or NeighborHood id not Found"),
                HttpStatus.BAD_REQUEST);


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
        if (!exist) {
            new ResponseEntity(new PhoneNotFound(phone.getId()), HttpStatus.NOT_FOUND);
        }
        Phone tmp = repo.findById(phone.getId()).get();
        tmp.setName(phone.getName());
        tmp.setNo(phone.getNo());
        return ResponseEntity.ok(new SuccesResponse(repo.save(tmp)));
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@Valid @RequestBody Phone phone) {
        repo.delete(phone);
        return ResponseEntity.ok(new SuccesResponse("Succesfuly deleted"));
    }

}