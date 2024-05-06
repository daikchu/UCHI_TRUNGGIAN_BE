package com.vn.osp.notarialservices.attestation.controller;

import com.vn.osp.notarialservices.attestation.dto.AttestationDTO;
import com.vn.osp.notarialservices.attestation.service.AttestationService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/api-attestation")
public class AttestationController {
    @Autowired
    AttestationService attestationService;

    @RequestMapping(value="/count", method = RequestMethod.GET)
    public ResponseEntity<Long> countContractKindByFilter(@RequestParam(name = "org_type", defaultValue = "", required = false) Integer org_type,
                                                          @RequestParam(name = "code", defaultValue = "", required = false) String code,
                                                          @RequestParam(name = "parent_code", defaultValue = "", required = false) String parent_code)  {
        String filter = "";
        if(org_type!=null) filter+=" and bo.type_org = "+org_type;
        if(!StringUtils.isBlank(code)) filter += " and bo.code = '"+code+"'";
        if(!StringUtils.isBlank(parent_code)) filter += " and bo.parent_code = '"+parent_code+"'";
        Long result = attestationService.count(filter);
        return new ResponseEntity<Long>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseEntity<List<AttestationDTO>> search(@RequestParam(name = "org_type", defaultValue = "", required = false) Integer org_type,
                                                       @RequestParam(name = "code", defaultValue = "", required = false) String code,
                                                       @RequestParam(name = "parent_code", defaultValue = "", required = false) String parent_code){
        String filter = "";
        if(org_type!=null) filter+=" and bo.type_org = "+org_type;
        if(!StringUtils.isBlank(code)) filter += " and bo.code = '"+code+"'";
        if(!StringUtils.isBlank(parent_code)) filter += " and bo.parent_code = '"+parent_code+"'";
        List<AttestationDTO> result = new ArrayList<>();
        try{
            result = attestationService.findByFilter(filter);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(result, HttpStatus.NO_CONTENT);
        }
    }

    @RequestMapping(value = "/getByParentCode", method = RequestMethod.GET)
    public ResponseEntity<List<AttestationDTO>> getByParentId(@RequestParam(name = "parent_code", required = false) String parent_code){
        List<AttestationDTO> result = new ArrayList<>();
        try{
            result = attestationService.getByParentCode(parent_code);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(result, HttpStatus.NO_CONTENT);
        }
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public ResponseEntity<AttestationDTO> get(@RequestParam(name = "id", required = true) Long id){
        AttestationDTO result = new AttestationDTO();
        try{
            result = attestationService.get(id);
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(result, HttpStatus.NO_CONTENT);
        }
    }


    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public ResponseEntity<Boolean> update(@RequestBody @Valid final AttestationDTO item){
        if (item == null) return new ResponseEntity<>(false, HttpStatus.NO_CONTENT);
        AttestationDTO attestationDB = attestationService.get(item.getId());
        if(attestationDB==null) return new ResponseEntity<>(false, HttpStatus.NO_CONTENT);
        Boolean result = attestationService.update(item);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public ResponseEntity<Boolean> delete(@RequestParam Long id){
        AttestationDTO attestationDB = attestationService.get(id);
        if(attestationDB == null) return new ResponseEntity<>(false, HttpStatus.NO_CONTENT);
        Boolean result = attestationService.delete(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /*@RequestMapping(value="/getListCertTemplateFieldMap", method = RequestMethod.GET)
    public ResponseEntity<List<AttestationTemplateFieldMapDTO>> getListCertTemplateFieldMap(
            @RequestParam(name = "filter", required = false) String filter){
        List<AttestationTemplateFieldMapDTO> result = attestationService.getAllAttestationTemplateFieldMap(filter);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }*/
}
