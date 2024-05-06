package com.vn.osp.notarialservices.citizenInformation.controller;
import com.vn.osp.notarialservices.citizenInformation.dto.CitizenInformationDTO;
import com.vn.osp.notarialservices.citizenInformation.service.CitizenInformationService;
import com.vn.osp.notarialservices.identityAuthentication.dto.ResultTokenGenerate;
import com.vn.osp.notarialservices.paymentTransaction.domain.OrderMapVerification;
import com.vn.osp.notarialservices.utils.AuthorizationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Controller
@RequestMapping(value = "/citizen-information")
public class CitizenInformationController {

    @Autowired
    private CitizenInformationService citizenInformationService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Object> add(@RequestHeader(value = "Authorization") String authorization, @RequestBody final CitizenInformationDTO citizenInformationDTO, HttpServletRequest request){
        // validate token
        if(AuthorizationUtil.validateAccessToken(authorization)) {
            CitizenInformationDTO result = citizenInformationService.saveOrUpdate(citizenInformationDTO).orElse(null);
            return ResponseEntity.ok(result);
        } else {
            ResultTokenGenerate result = new ResultTokenGenerate();
            result.setTimestamp(new Date().getTime());
            result.setStatus(401);
            result.setError("Unauthorized");
            result.setMessage("");
            result.setPath(request.getRequestURI());
            return new ResponseEntity<>(result, HttpStatus.UNAUTHORIZED);
        }
    }
    @RequestMapping(value = "generate-verify-id",method = RequestMethod.POST)
    public ResponseEntity<Object> generateVerifyId(@RequestHeader(value = "Authorization") String authorization, @RequestBody String notary_office_code, HttpServletRequest request){
        // validate token
        if(AuthorizationUtil.validateAccessToken(authorization)) {
            return new ResponseEntity<>(citizenInformationService.generateVerifyId(notary_office_code), HttpStatus.OK);
        }else {
            ResultTokenGenerate result = new ResultTokenGenerate();
            result.setTimestamp(new Date().getTime());
            result.setStatus(401);
            result.setError("Unauthorized");
            result.setMessage("");
            result.setPath(request.getRequestURI());
            return new ResponseEntity<>(result, HttpStatus.UNAUTHORIZED);
        }
    }
}
