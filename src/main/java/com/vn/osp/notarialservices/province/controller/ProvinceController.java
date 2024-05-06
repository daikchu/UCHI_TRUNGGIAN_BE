package com.vn.osp.notarialservices.province.controller;

import com.vn.osp.notarialservices.province.dto.AddProvince;
import com.vn.osp.notarialservices.province.dto.Province;
import com.vn.osp.notarialservices.province.dto.UpdateProvince;
import com.vn.osp.notarialservices.province.service.ProvinceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ProBook on 5/25/2017.
 */
@Controller
@RequestMapping(value="/province")
public class ProvinceController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProvinceController.class);

    private final ProvinceService provinceService;
    private final ProvinceControllerHateoasBuilder provinceControllerHateoasBuilder;

    @Autowired
    public ProvinceController(final ProvinceService provinceService, final ProvinceControllerHateoasBuilder provinceControllerHateoasBuilder){
        this.provinceService = provinceService;
        this.provinceControllerHateoasBuilder = provinceControllerHateoasBuilder;
    }
    @RequestMapping(value = "/AddProvince", method = RequestMethod.POST)
    public ResponseEntity<Boolean> AddProvince(@RequestBody @Valid final AddProvince addProvince)  {


        return new ResponseEntity<Boolean>((Boolean) provinceService.AddProvince(addProvince.getName() ,addProvince.getEntry_user_id(),addProvince.getEntry_user_name() ,addProvince.getCode()).orElse(Boolean.valueOf(false)), HttpStatus.OK);
    }
    @RequestMapping(value = "/UpdateProvince", method = RequestMethod.POST)
    public ResponseEntity<Boolean> UpdateProvince(@RequestBody @Valid final UpdateProvince updateProvince)  {
        return new ResponseEntity<Boolean>((Boolean) provinceService.UpdateProvince(updateProvince.getSid(),updateProvince.getName(),updateProvince.getUpdate_user_id(),updateProvince.getUpdate_user_name()).orElse(Boolean.valueOf(false)), HttpStatus.OK);
    }
    @RequestMapping(value = "/deleteProvince", method = RequestMethod.POST)
    public ResponseEntity<Boolean> deleteProvince(@RequestBody @Valid final Long id)  {
        return new ResponseEntity<Boolean>((Boolean) provinceService.deleteProvince(id).orElse(Boolean.valueOf(false)), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Province>> getListProvince()  {
        List<Province> provinceByFilter = provinceService.findProvinceByFilter("where 1=1 order by name asc").orElse(new ArrayList<Province>());
        return new ResponseEntity<List<Province>>(provinceByFilter, HttpStatus.OK);
    }

    @RequestMapping(value="/findProvinceByFilter", method = RequestMethod.POST)
    public ResponseEntity<List<Province>> findProvinceByFilter(@RequestBody final String stringFilter)  {
        List<Province> provinceByFilter = provinceService.findProvinceByFilter(stringFilter).orElse(new ArrayList<Province>());
        System.out.println("string " + stringFilter);
        return new ResponseEntity<List<Province>>(provinceByFilter, HttpStatus.OK);
    }
    @RequestMapping(value="/countProvinceByFilter", method = RequestMethod.POST)
    public ResponseEntity<BigInteger> countProvinceByFilter(@RequestBody final String stringFilter)  {
        BigInteger provinceByFilter = provinceService.countProvinceByFilter(stringFilter).orElse(BigInteger.valueOf(0));

        return new ResponseEntity<BigInteger>(provinceByFilter, HttpStatus.OK);
    }
}
