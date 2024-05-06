package com.vn.osp.notarialservices.customer.controller;
import com.vn.osp.notarialservices.customer.dto.CustomerDTO;
import com.vn.osp.notarialservices.customer.dto.CustomerInfoDTO;
import com.vn.osp.notarialservices.customer.service.CustomerService;
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

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * @author quyenlc on 29/05/2021
 */
@Controller
@RequestMapping(value="/customer")
public class CustomerController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);

    private final CustomerService customerService;

    public static Logger getLOGGER() {
        return LOGGER;
    }

    public CustomerService getCustomerService() {
        return customerService;
    }

    @Autowired
    public CustomerController(final CustomerService customerService){
        this.customerService = customerService;
    }
    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public ResponseEntity<Boolean> insert(@RequestBody final CustomerDTO customerDTO){

        Boolean result = customerService.insert(customerDTO).orElse(false);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResponseEntity<Boolean> update(@RequestBody final CustomerDTO customerDTO){
        Boolean result = customerService.update(customerDTO).orElse(false);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    @RequestMapping(value = "/deleteById", method = RequestMethod.POST)
    public ResponseEntity<Boolean> deleteById(@RequestBody final Long noid){
        Boolean result = customerService.deleteById(noid).orElse(false);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/selectByFilter", method = RequestMethod.POST)
    public ResponseEntity<List<CustomerDTO>> selectByFilter(@RequestBody final String stringFilter){
        List<CustomerDTO> result = customerService.selectByFilter(stringFilter).orElse(new ArrayList<>() );
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/getCustomerByCode", method = RequestMethod.POST)
    public ResponseEntity<List<CustomerDTO>> getCustomerByCode(@RequestBody final String stringFilter){
        List<CustomerDTO> result = customerService.getCustomerByCode(stringFilter).orElse(new ArrayList<>() );
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @RequestMapping(value = "/countTotalCustomer", method = RequestMethod.POST)
    public ResponseEntity<BigInteger> countTotalNotaryOffice(@RequestBody final String stringFilter){
        BigInteger respone = customerService.countTotalCustomer(stringFilter).orElse(null);
        return new ResponseEntity<>(respone, HttpStatus.OK);
    }

    @RequestMapping(value = "/getCustomerInfoByCode", method = RequestMethod.GET)
    public ResponseEntity<CustomerInfoDTO> getCustomerInfoByCode(@RequestParam(name = "code",required = true, defaultValue = "") String code){
        CustomerInfoDTO result = new CustomerInfoDTO();
        ArrayList<CustomerDTO> item = new ArrayList<>();
        try{
            if (!StringUtils.isBlank(code)){
                result = customerService.getCustomerInfoByCode(code);
            }
            return new ResponseEntity<>(result,HttpStatus.OK);
        }catch (Exception e){
            LOGGER.error(" Have error in CustomerController.getCustomerInfoByCode"+ e.getMessage());
            return new ResponseEntity<>(result,HttpStatus.NO_CONTENT);
        }
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<CustomerDTO>> getList(@RequestParam(name = "province_code", required = false, defaultValue = "") String province_code){
        String stringFilter = " where 1=1 ";
        if(StringUtils.isNotBlank(province_code)) stringFilter += " and province_code='"+province_code+"'";
        List<CustomerDTO> result = customerService.selectByFilter(stringFilter).orElse(new ArrayList<>() );
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
