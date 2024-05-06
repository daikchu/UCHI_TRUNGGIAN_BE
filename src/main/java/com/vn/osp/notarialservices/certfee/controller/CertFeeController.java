package com.vn.osp.notarialservices.certfee.controller;

import com.vn.osp.notarialservices.attestation.dto.AttestationDTO;
import com.vn.osp.notarialservices.certfee.dto.CertFeeDTO;
import com.vn.osp.notarialservices.certfee.service.CertFeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DaiCQ on 2020-11-27
 */
@Controller
@RequestMapping(value="/certfee")
public class CertFeeController {
    @Autowired
    CertFeeService certFeeService;
    /*@RequestMapping(value = "/getByType", method = RequestMethod.GET)
    public ResponseEntity<List<AttestationDTO>> getByParentId(@RequestParam(name = "type", required = false) String parent_code){
        List<AttestationDTO> result = new ArrayList<>();
        try{
            result = attestationService.getByParentCode(parent_code);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(result, HttpStatus.NO_CONTENT);
        }
    }*/

    @RequestMapping(value = "/getFeeByType", method = RequestMethod.GET)
    public ResponseEntity<CertFeeDTO> getType(@RequestParam(name = "type", required = false) int type){
        CertFeeDTO result = new CertFeeDTO();
        try{
            result = certFeeService.getByType(type);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(result, HttpStatus.NO_CONTENT);
        }
    }

   /* @RequestMapping(value = "/getCertFeeTcccCall", method = RequestMethod.GET)
    public ResponseEntity<Long> getType(@RequestParam(name = "type", required = false) int type,
                                        @RequestParam(name = "soBan", required = false) int soBan){
        List<AttestationDTO> result = new ArrayList<>();
        try{
            result = certFeeService.getByParentCode(parent_code);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(result, HttpStatus.NO_CONTENT);
        }
    }*/
}
