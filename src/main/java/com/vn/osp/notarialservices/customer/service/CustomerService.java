package com.vn.osp.notarialservices.customer.service;

import com.vn.osp.notarialservices.customer.dto.CustomerDTO;
import com.vn.osp.notarialservices.customer.dto.CustomerInfoDTO;
import com.vn.osp.notarialservices.customer.dto.RoleFunctionCustomerDTO;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

public interface CustomerService {
    Optional<Boolean> insert(CustomerDTO customerDTO);
    Optional<Boolean> update(CustomerDTO customerDTO);
    Optional<Boolean> deleteById(Long id);
    Optional<List<CustomerDTO>> selectByFilter(String stringFilter);
    Optional<List<CustomerDTO>> getCustomerByCode(String stringFilter);
    Optional<BigInteger> countTotalCustomer(String stringFilter);
    CustomerInfoDTO getCustomerInfoByCode(String code);
}
