package com.vn.osp.notarialservices.packagefunction.controller;

import com.vn.osp.notarialservices.packagefunction.dto.PackageFunctionsDTO;
import com.vn.osp.notarialservices.packagefunction.service.PackageFunctionService;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
/**
 * @author quyenlc on 29/05/2021
 */
@Controller
@RequestMapping(value="/packageFunction")
public class PackageFunctionController {
    private static final Logger LOGGER = LoggerFactory.getLogger(PackageFunctionController.class);

    public static Logger getLOGGER() {
        return LOGGER;
    }

    public PackageFunctionService getPackageFunctionService() {
        return packageFunctionService;
    }

    private final PackageFunctionService packageFunctionService;
    @Autowired
    public PackageFunctionController(final PackageFunctionService packageFunctionService){
        this.packageFunctionService = packageFunctionService;
    }

    @RequestMapping(value = "/get-package-function-by-filter", method = RequestMethod.POST)
    public ResponseEntity<List<PackageFunctionsDTO>> selectPackageFunctionbyFilter(@RequestBody final String stringFilter){
        List<PackageFunctionsDTO> functionDTOList = packageFunctionService.selectPackageFunctionbyFilter(stringFilter).orElse(new ArrayList<>());
        return new ResponseEntity<List<PackageFunctionsDTO>>(functionDTOList, HttpStatus.OK);
    }
}
