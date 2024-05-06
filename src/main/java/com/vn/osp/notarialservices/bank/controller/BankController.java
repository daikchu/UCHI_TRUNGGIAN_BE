package com.vn.osp.notarialservices.bank.controller;

import com.vn.osp.notarialservices.bank.dto.AddBank;
import com.vn.osp.notarialservices.bank.dto.Bank;
import com.vn.osp.notarialservices.bank.dto.UpdateBank;
import com.vn.osp.notarialservices.bank.service.BankService;
import com.vn.osp.notarialservices.province.dto.UpdateProvince;
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
 * Created by Admin on 30/5/2017.
 */
@Controller
@RequestMapping(value = "/bank")
public class BankController {
    private static final Logger LOGGER = LoggerFactory.getLogger(BankController.class);

    private final BankService bankService;
    private final BankControllerHateoasBuilder bankControllerHateoasBuilder;

    @Autowired
    public BankController(final BankService bankService, final BankControllerHateoasBuilder bankControllerHateoasBuilder){
        this.bankService = bankService;
        this.bankControllerHateoasBuilder = bankControllerHateoasBuilder;
    }
    @RequestMapping(value = "/AddBank", method = RequestMethod.POST)
    public ResponseEntity<Boolean> addBank(@RequestBody @Valid final AddBank addBank)  {
        return new ResponseEntity<Boolean>((Boolean) bankService.addBank(addBank.getName(),addBank.getEntry_user_id(),addBank.getEntry_user_name(),addBank.getCode(),addBank.getActive()).orElse(Boolean.valueOf(false)), HttpStatus.OK);
    }
    @RequestMapping(value = "/UpdateBank", method = RequestMethod.POST)
    public ResponseEntity<Boolean> updateBank(@RequestBody @Valid final UpdateBank updateBank)  {
        return new ResponseEntity<Boolean>((Boolean) bankService.updateBank(updateBank.getSid(),updateBank.getName(),updateBank.getUpdate_user_id(),updateBank.getUpdate_user_name(),updateBank.getActive()).orElse(Boolean.valueOf(false)), HttpStatus.OK);
    }
    @RequestMapping(value = "/DeleteBank", method = RequestMethod.POST)
    public ResponseEntity<Boolean> deleteBank(@RequestBody @Valid final Long id)  {
        return new ResponseEntity<Boolean>((Boolean) bankService.deleteBank(id).orElse(Boolean.valueOf(false)), HttpStatus.OK);
    }
    @RequestMapping(value="/FindBankByFilter", method = RequestMethod.POST)
    public ResponseEntity<List<Bank>> findBankByFilter(@RequestBody final String stringFilter)  {
        List<Bank> bankByFilter = bankService.findBankByFilter(stringFilter).orElse(new ArrayList<Bank>());
        System.out.println("string " + stringFilter);
        return new ResponseEntity<List<Bank>>(bankByFilter, HttpStatus.OK);
    }
    @RequestMapping(value="/CountBankByFilter", method = RequestMethod.POST)
    public ResponseEntity<BigInteger> countBankByFilter(@RequestBody final String stringFilter)  {
        BigInteger bankByFilter = bankService.countBankByFilter(stringFilter).orElse(BigInteger.valueOf(0));

        return new ResponseEntity<BigInteger>(bankByFilter, HttpStatus.OK);
    }
}
