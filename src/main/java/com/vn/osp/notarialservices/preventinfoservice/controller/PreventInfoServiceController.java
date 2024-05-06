package com.vn.osp.notarialservices.preventinfoservice.controller;

import com.vn.osp.notarialservices.preventinfoservice.dto.PreventInfoService;
import com.vn.osp.notarialservices.preventinfoservice.service.PreventInfoServiceService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 12/6/2017.
 */
@Controller
@RequestMapping(value ="/PreventInfoService")
public class PreventInfoServiceController {
    private static final org.slf4j.Logger LOGGER =  LoggerFactory.getLogger(PreventInfoServiceController.class);

    private final PreventInfoServiceService preventInfoServiceService;

    @Autowired
    public PreventInfoServiceController(final PreventInfoServiceService preventInfoServiceService) {
        this.preventInfoServiceService= preventInfoServiceService;
    }
    @RequestMapping(value="/FindPreventInfoServiceByFilter", method = RequestMethod.POST)
    public ResponseEntity<List<PreventInfoService>> findBankByFilter(@RequestBody final String stringFilter)  {
        List<PreventInfoService> preventInfoServiceByFilter = preventInfoServiceService.findPreventInfoServiceByFilter(stringFilter).orElse(new ArrayList<PreventInfoService>());
        System.out.println("string " + stringFilter);
        return new ResponseEntity<List<PreventInfoService>>(preventInfoServiceByFilter, HttpStatus.OK);
    }
}
