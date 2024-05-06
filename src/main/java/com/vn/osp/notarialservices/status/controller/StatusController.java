package com.vn.osp.notarialservices.status.controller;

import com.vn.osp.notarialservices.customer.dto.CustomerDTO;
import com.vn.osp.notarialservices.status.domain.StatusBO;
import com.vn.osp.notarialservices.status.service.StatusService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping(value="/statuses")
@CrossOrigin(origins = "*", allowedHeaders = {"Content-Type", "X-Requested-With"})
public class StatusController {
    @Autowired
    private StatusService statusService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<StatusBO>> getList(@RequestParam(name = "type", required = false, defaultValue = "") String type){
        return new ResponseEntity<>(statusService.getList(type), HttpStatus.OK);
    }
}
