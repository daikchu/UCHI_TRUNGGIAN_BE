package com.vn.osp.notarialservices.province.service;

import com.vn.osp.notarialservices.province.domain.ProvinceBo;
import com.vn.osp.notarialservices.province.dto.Province;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

/**
 * Created by ProBook on 5/25/2017.
 */

public interface ProvinceService  {
    Optional<Boolean> AddProvince(String name , Long entryUserId, String entryUserName , String code);
    Optional<Boolean> UpdateProvince(Long id ,String name , Long updateUserId, String updateUserName);
    Optional<List<Province>> findProvinceByFilter (String stringFilter);
    Optional<BigInteger> countProvinceByFilter(String stringFilter);
    Optional<Boolean> deleteProvince(Long id );
}
