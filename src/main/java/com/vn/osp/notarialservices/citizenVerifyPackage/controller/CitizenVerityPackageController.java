package com.vn.osp.notarialservices.citizenVerifyPackage.controller;

import com.vn.osp.notarialservices.citizenVerifyPackage.domain.CitizenVerifyPackageBO;
import com.vn.osp.notarialservices.citizenVerifyPackage.service.CitizenVerifyPackageService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping(value = "/citizen-verify-packages")
public class CitizenVerityPackageController {
    private final CitizenVerifyPackageService citizenVerifyPackageService;

    @Autowired
    public CitizenVerityPackageController(final CitizenVerifyPackageService citizenVerifyPackageService) {
        this.citizenVerifyPackageService = citizenVerifyPackageService;
    }

    /*public CitizenVerityPackageController() {
    }

    public CitizenVerityPackageController(CitizenVerifyPackageService citizenVerifyPackageService) {
        this.citizenVerifyPackageService = citizenVerifyPackageService;
    }*/

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<CitizenVerifyPackageBO>> getAll() {
        return new ResponseEntity<>(citizenVerifyPackageService.getAll().orElse(null), HttpStatus.OK);
    }
}
