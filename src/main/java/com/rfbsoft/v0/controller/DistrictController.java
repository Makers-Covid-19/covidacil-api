package com.rfbsoft.v0.controller;

import com.rfbsoft.v0.Info;
import com.rfbsoft.v0.exception.ProvinceNotFound;
import com.rfbsoft.v0.model.District;
import com.rfbsoft.v0.model.Province;
import com.rfbsoft.v0.response.SuccesResponse;
import com.rfbsoft.v0.service.DistrictService;
import com.rfbsoft.v0.service.ProvinceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(DistrictController.CONTROLLER_PATH)
public class DistrictController {
    public static final String CONTROLLER_PATH = "api/" + Info.VERSION + "/districts";

    @Autowired
    DistrictService repo;

    @Autowired
    ProvinceService provinceRepository;

    @GetMapping
    public ResponseEntity get() {

        return ResponseEntity.ok(new SuccesResponse(repo.findAll()));
    }

    @GetMapping("/{id}")
    public ResponseEntity getOne(@PathVariable Long id) {
        boolean exist = provinceRepository.existsById(id);
        if (!exist) {
            return new ResponseEntity(new ProvinceNotFound(id), HttpStatus.NOT_FOUND);
        }
        Province province = provinceRepository.findById(id).get();

        Set districts = province.getDistricts();
        List districtList = new ArrayList();
        districts.forEach((district)->{
            districtList.add(district);
        });

        districtList.sort(null);

        return ResponseEntity.ok(new SuccesResponse(districtList));
    }

//    @PostMapping("/{provinceId}")
//    public ResponseEntity add(@Valid @RequestBody District object , @PathVariable Long provinceId) {
//        boolean existProvince = provinceRepository.existsById(provinceId);
//        if(!existProvince){
//            return new ResponseEntity("Province not found",HttpStatus.NOT_FOUND);
//        }
//        Province province = provinceRepository.findById(provinceId).get();
//
//        object.setProvince(province);
//        return new ResponseEntity(repo.save(object),HttpStatus.OK);
//    }
//
//    @PutMapping
//    public ResponseEntity update(@Valid @RequestBody District object) {
//        return new ResponseEntity(repo.save(object), HttpStatus.OK);
//    }
//
//    @DeleteMapping
//    public ResponseEntity<?> delete(@Valid @RequestBody District object) {
//        repo.delete(object);
//        return ResponseEntity.ok("deleted");
//    }

}
