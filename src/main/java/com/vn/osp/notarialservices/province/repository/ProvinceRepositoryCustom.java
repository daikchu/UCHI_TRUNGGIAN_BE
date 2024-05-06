package com.vn.osp.notarialservices.province.repository;

import com.vn.osp.notarialservices.province.domain.ProvinceBo;
import org.hibernate.cache.spi.OptimisticCacheSource;

import java.math.BigInteger;
import java.util.List;
import java.util.Optional;

/**
 * Created by ProBook on 5/25/2017.
 */
public interface ProvinceRepositoryCustom {
    void findByOverridingMethod();

    /***
     *
     * @paramid
     */
    Optional<Boolean> AddProvince(String name,  Long entryUserId, String entryUserName, String code);
    Optional<List<ProvinceBo>> findProvinceByFilter (String stringFilter);
    Optional<BigInteger> countProvinceByFilter(String stringFilter);
    Optional<Boolean> UpdateProvince(Long id, String name, Long updateUserId,String updateUserName);
    Optional<Boolean>deleteProvince(Long id);
}
