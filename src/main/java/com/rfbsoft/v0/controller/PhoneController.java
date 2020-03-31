package com.rfbsoft.v0.controller;


import com.rfbsoft.v0.Info;
import com.rfbsoft.v0.exception.*;
import com.rfbsoft.v0.model.*;
import com.rfbsoft.v0.request.PhoneAddRequest;
import com.rfbsoft.v0.response.PhoneResponse;
import com.rfbsoft.v0.response.SuccesResponse;
import com.rfbsoft.v0.service.*;
import com.rfbsoft.v0.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(PhoneController.CONTROLLER_PATH)
public class PhoneController {
    public static final String CONTROLLER_PATH = "api/" + Info.VERSION + "/phones";

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

    /*

    For Phone Responding
     */
    Long id = null;
    List globalList = new ArrayList();
    List majorList = new ArrayList();
    List publicList = new ArrayList();
    HashMap categoryPhoneMap = new HashMap();

    @GetMapping
    public ResponseEntity get() {
        return ResponseEntity.ok(new SuccesResponse(repo.findAll()));
    }

    @GetMapping("/id")
    public ResponseEntity getOne(
            @RequestParam(required = false, name = "province_id") String provinceId,
            @RequestParam(required = false, name = "district_id") String districtId,
            @RequestParam(required = false, name = "neighborhood_id") String neighborhoodId) {

        globalList.clear();
        majorList.clear();
        publicList.clear();
        categoryPhoneMap.clear();

        provinceRepository.findById((long) 0).get().getPhones().forEach(phone -> globalList.add(phone));


        if (neighborhoodId != null) {
            return processWithNeighborhoodId(neighborhoodId);
        }
        if (districtId != null) {
            return processWithDistrictId(districtId);

        }
        if (provinceId != null) {
            return processWithProvinceId(provinceId);

        }
//        "Please send Province - Distric or Neighborhood id"
        BadRequestFound badRequestFound = new BadRequestFound(
                "Please send Province - Distric or Neighborhood id");
        return new ResponseEntity(badRequestFound,
                badRequestFound.getHttpStatus());

    }

    private ResponseEntity processWithNeighborhoodId(String neighborhoodId) {

        if (Utils.isValidLong(neighborhoodId)) {
            id = Long.valueOf(neighborhoodId);
        } else {
            BadRequestFound badRequestFound = new BadRequestFound(neighborhoodId + " Not Valid Long");
            new ResponseEntity(badRequestFound,
                    badRequestFound.getHttpStatus());
        }
        boolean exist = neighborhoodRepository.existsById(id);
        if (!exist) {
            NeighborhoodNotFound neighborhoodNotFound = new NeighborhoodNotFound(id);
            return new ResponseEntity(neighborhoodNotFound, neighborhoodNotFound.getHttpStatus());
        }

        Neighborhood neighborhood = neighborhoodRepository.findById(id).get();

        District district = neighborhood.getDistrict();

        neighborhoodPhones(neighborhood);

        districtPhones(district);

        provincePhones(district.getProvince());

        sortLists();

        return ResponseEntity.ok(new SuccesResponse(new PhoneResponse(majorList, publicList, globalList)));
    }

    private ResponseEntity processWithDistrictId(String districtId) {
        if (Utils.isValidLong(districtId)) {
            id = Long.valueOf(districtId);
        } else {
            BadRequestFound badRequestFound = new BadRequestFound(districtId + " Not Valid Long");
            new ResponseEntity(badRequestFound,
                    badRequestFound.getHttpStatus());
        }

        boolean exist = districtRepository.existsById(id);
        if (!exist) {
            DistrictNotFound districtNotFound = new DistrictNotFound(id);
            return new ResponseEntity(districtNotFound, districtNotFound.getHttpStatus());
        }

        District district = districtRepository.findById(id).get();

        districtPhones(district);

        provincePhones(district.getProvince());

        sortLists();


        return ResponseEntity.ok(new SuccesResponse(new PhoneResponse(majorList, publicList, globalList)));
    }

    private void sortLists() {
        globalList.sort(null);
        majorList.sort(null);
        publicList.sort(null);
    }

    private ResponseEntity processWithProvinceId(String provinceId) {
        if (Utils.isValidLong(provinceId)) {
            id = Long.valueOf(provinceId);
        } else {
            BadRequestFound badRequestFound = new BadRequestFound(provinceId + " Not Valid Long");

            new ResponseEntity(badRequestFound,
                    badRequestFound.getHttpStatus());
        }
        boolean exist = provinceRepository.existsById(id);
        if (!exist) {
            ProvinceNotFound provinceNotFound = new ProvinceNotFound(id);
            return new ResponseEntity(provinceNotFound, provinceNotFound.getHttpStatus());
        }
        Province province = provinceRepository.findById(id).get();
        provincePhones(province);

        sortLists();
        return ResponseEntity.ok(new SuccesResponse(new PhoneResponse(majorList, publicList, globalList)));
    }


    private void provincePhones(Province province) {
        province.getPhones().stream()
                .filter(phone -> phone.getDistrict() == null)
                .forEach(phone -> {
                    if (!categoryPhoneMap.containsKey(phone.getCategory())) {
                        categoryPhoneMap.put(phone.getCategory(), phone);
                    } else
                        publicList.add(phone);

                });

        categoryPhoneMap.forEach((category, phone) -> {
            majorList.add(phone);
        });
    }

    private void districtPhones(District district) {
        district.getPhones().stream()
                .filter(phone -> phone.getNeighborhood() == null)
                .forEach(phone -> {
                    if (!categoryPhoneMap.containsKey(phone.getCategory())) {
                        categoryPhoneMap.put(phone.getCategory(), phone);
                    } else
                        publicList.add(phone);
                });


    }

    private void neighborhoodPhones(Neighborhood neighborhood) {
        neighborhood.getPhones().forEach(phone -> {
            if (!categoryPhoneMap.containsKey(phone.getCategory())) {
                categoryPhoneMap.put(phone.getCategory(), phone);
            } else
                publicList.add(phone);
        });
    }


    @PostMapping("")
    public ResponseEntity add(@Valid @RequestBody PhoneAddRequest request) {

        Phone phone = new Phone();
        phone.setName(request.getName());
        phone.setNo(request.getPhoneNumber());

        boolean existCategory = categoryRepository.existsById(request.getCategoryId());
        if (!existCategory) {
            CategoryNotFound categoryNotFound = new CategoryNotFound(request.getCategoryId());
            return new ResponseEntity(categoryNotFound, categoryNotFound.getHttpStatus());
        }
        Category category = categoryRepository.findById(request.getCategoryId()).get();

        phone.setCategory(category);


        if (request.getNeighborhoodId() != null) {


            Long id = request.getNeighborhoodId();

            boolean exist = neighborhoodRepository.existsById(id);
            if (!exist) {
                NeighborhoodNotFound neighborhoodNotFound = new NeighborhoodNotFound(id);
                return new ResponseEntity(neighborhoodNotFound, neighborhoodNotFound.getHttpStatus());
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
                DistrictNotFound districtNotFound = new DistrictNotFound(id);
                return new ResponseEntity(districtNotFound, districtNotFound.getHttpStatus());
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
                ProvinceNotFound provinceNotFound = new ProvinceNotFound(id);
                return new ResponseEntity(provinceNotFound, provinceNotFound.getHttpStatus());
            }
            Province province = provinceRepository.findById(id).get();
            phone.setProvince(province);
            repo.save(phone);
            return ResponseEntity.ok(new SuccesResponse(phone));

        }


        BadRequestFound badRequestFound = new BadRequestFound("Province - District or NeighborHood id not Found");
        return new ResponseEntity(badRequestFound,
                badRequestFound.getHttpStatus());


    }

    @PutMapping
    public ResponseEntity update(@Valid @RequestBody Phone phone) {
        boolean exist = repo.existsById(phone.getId());
        if (!exist) {
            PhoneNotFound phoneNotFound = new PhoneNotFound(phone.getId());
            new ResponseEntity(phoneNotFound, phoneNotFound.getHttpStatus());
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