package com.vn.osp.notarialservices.contractkind.controller;

import com.vn.osp.notarialservices.contractkind.dto.AddContractKind;
import com.vn.osp.notarialservices.contractkind.dto.ContractKind;
import com.vn.osp.notarialservices.contractkind.dto.UpdateContractKind;
import com.vn.osp.notarialservices.contractkind.service.ContractKindService;
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
 * Created by minh on 5/30/2017.
 */
@Controller
@RequestMapping(value="/contractkind")
public class ContractKindController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ContractKindController.class);
    private final ContractKindService contractKindService;

    @Autowired
    public ContractKindController(final ContractKindService contractKindService){
        this.contractKindService = contractKindService;
    }
    @RequestMapping(value = "/AddContractKind", method = RequestMethod.POST)
    public ResponseEntity<Boolean> ContractKindAdd(@RequestBody @Valid final AddContractKind addContractKind)  {


        return new ResponseEntity<Boolean>((Boolean) contractKindService.ContractKindAdd(addContractKind.getName() ,addContractKind.getEntry_user_id(),addContractKind.getEntry_user_name() ,addContractKind.getCode()).orElse(Boolean.valueOf(false)), HttpStatus.OK);
    }
    @RequestMapping(value = "/UpdateContractKind", method = RequestMethod.POST)
    public ResponseEntity<Boolean> UpdateContractKind(@RequestBody @Valid final UpdateContractKind updateContractKind)  {
        return new ResponseEntity<Boolean>((Boolean) contractKindService.UpdateContractKind(updateContractKind.getCkId(),updateContractKind.getName(),updateContractKind.getUpdate_user_id(),updateContractKind.getUpdate_user_name()).orElse(Boolean.valueOf(false)), HttpStatus.OK);
    }
    @RequestMapping(value = "/deleteContractKind", method = RequestMethod.POST)
    public ResponseEntity<Boolean> deleteProvince(@RequestBody @Valid final Long id)  {
        return new ResponseEntity<Boolean>((Boolean) contractKindService.deleteContractKind(id).orElse(Boolean.valueOf(false)), HttpStatus.OK);
    }
    @RequestMapping(value="/findContractKindByFilter", method = RequestMethod.POST)
    public ResponseEntity<List<ContractKind>> findContractKindByFilter(@RequestBody final String stringFilter)  {
        List<ContractKind> contractKinds = contractKindService.findContractKindByFilter(stringFilter).orElse(new ArrayList<ContractKind>());
        return new ResponseEntity<List<ContractKind>>(contractKinds, HttpStatus.OK);
    }
    @RequestMapping(value="/countContractKindByFilter", method = RequestMethod.POST)
    public ResponseEntity<BigInteger> countContractKindByFilter(@RequestBody final String stringFilter)  {
        BigInteger result = contractKindService.countContractKindByFilter(stringFilter).orElse(BigInteger.valueOf(0));

        return new ResponseEntity<BigInteger>(result, HttpStatus.OK);
    }


}
