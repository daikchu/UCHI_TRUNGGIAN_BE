package com.vn.osp.notarialservices.function.controller;


import com.vn.osp.notarialservices.function.dto.FunctionDTO;
import com.vn.osp.notarialservices.function.service.FunctionService;
import com.vn.osp.notarialservices.packagefunction.domain.GroupRoleFunctionTemplateBO;
import com.vn.osp.notarialservices.packagefunction.dto.GroupRoleFunctionTemplateDTO;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by TuanNQ on 25/02/2021.
 */
@Controller
@RequestMapping(value="/function")
public class FunctionController {
    private static final Logger LOGGER = LoggerFactory.getLogger(FunctionController.class);

    public static Logger getLOGGER() {
        return LOGGER;
    }

    public FunctionService getFunctionService() {
        return functionService;
    }

    private final FunctionService functionService;
    @Autowired
    public FunctionController(final FunctionService functionService){
        this.functionService = functionService;
    }

    @RequestMapping(value = "/get-function-by-code", method = RequestMethod.POST)
    public ResponseEntity<List<FunctionDTO>> getByFunction(@RequestBody final String stringFilter){
            List<FunctionDTO> functionDTOList = functionService.getByFunction(stringFilter).orElse(new ArrayList<FunctionDTO>());
            return new ResponseEntity<List<FunctionDTO>>(functionDTOList, HttpStatus.OK);
    }


    @RequestMapping(value = "/get-group-role-function-template",method =  RequestMethod.POST)
    public ResponseEntity<List<GroupRoleFunctionTemplateDTO>> getGroupRoleFunctionTemplate(@RequestBody final String stringFilter) {
        List<GroupRoleFunctionTemplateDTO> result = functionService.getGroupRoleFunctionTemplateDTOByFilter(stringFilter).orElse(new ArrayList<>());;
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/get-function-by-filter", method = RequestMethod.POST)
    public ResponseEntity<List<FunctionDTO>> getFunctionByFilter(@RequestBody final String stringFilter) {
        List<FunctionDTO> result = functionService.getFunctionByFilter(stringFilter).orElse(new ArrayList<>());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
