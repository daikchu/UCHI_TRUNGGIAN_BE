package com.vn.osp.notarialservices.citizenVerificationNumber.controller;

import com.vn.osp.notarialservices.citizenVerificationNumber.domain.CitizenVerifyNumberBO;
import com.vn.osp.notarialservices.citizenVerificationNumber.dto.CitizenVerifyNumberDTO;
import com.vn.osp.notarialservices.citizenVerificationNumber.repository.CitizenVerficationNumberRepository;
import com.vn.osp.notarialservices.citizenVerificationNumber.service.CitizenVerficationNumberService;
import com.vn.osp.notarialservices.citizenVerificationOrder.dto.CitizenVerifyOrderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/citizen-verification-number")
public class CitizenVerificationNumberController {
    @Autowired
    private CitizenVerficationNumberRepository citizenVerficationNumberRepository;


    @RequestMapping(value = "/getByOfficeCode", method = RequestMethod.GET)
    public ResponseEntity<CitizenVerifyNumberDTO> getDetail(@RequestParam(name = "office_code") String office_code){
        CitizenVerifyNumberDTO result = citizenVerficationNumberRepository.getDetail(office_code, null).orElse(null);
        return ResponseEntity.ok(result);
    }
}
