package com.vn.osp.notarialservices.Package.controller;

import com.vn.osp.notarialservices.Package.dto.PackageDTO;
import com.vn.osp.notarialservices.Package.dto.PackageFuncDTO;
import com.vn.osp.notarialservices.Package.service.PackageService;
import com.vn.osp.notarialservices.customer.dto.RoleFunctionCustomerDTO;
import com.vn.osp.notarialservices.user.controller.UserController;
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
 * @author quyenlc on 29/05/2021
 */
@Controller
@RequestMapping(value = "/package")
public class PackageController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    private final PackageService packageService;

    public static Logger getLOGGER() {
        return LOGGER;
    }

    public PackageService getPackageService() {
        return packageService;
    }

    @Autowired
    public PackageController(final PackageService packageService){
        this.packageService = packageService;
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public ResponseEntity<Boolean> insert(@RequestBody final PackageFuncDTO packageFuncDTO){

        Boolean result = packageService.insert(packageFuncDTO).orElse(false);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity<Boolean> update(@RequestBody final PackageFuncDTO packageFuncDTO){
        Boolean result = packageService.update(packageFuncDTO).orElse(false);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/deleteById", method = RequestMethod.POST)
    public ResponseEntity<Boolean> deleteById(@RequestBody final Long id){
        Boolean result = packageService.deleteById(id).orElse(false);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/count-total-package", method = RequestMethod.POST)
    public ResponseEntity<BigInteger> packageCountTotalByFilter(@RequestBody @Valid final String stringFilter) {
        BigInteger result = packageService.packageCountTotalByFilter(stringFilter).orElse(BigInteger.valueOf(0));
        return new ResponseEntity<BigInteger>(result, HttpStatus.OK);
    }
    @RequestMapping(value = "/get-package-by-filter", method = RequestMethod.POST)
    public ResponseEntity<List<PackageDTO>> selectPackagebyFilter(@RequestBody final String stringFilter){
        List<PackageDTO> functionDTOList = packageService.selectPackagebyFilter(stringFilter).orElse(new ArrayList<>());
        return new ResponseEntity<List<PackageDTO>>(functionDTOList, HttpStatus.OK);
    }

}
