package com.vn.osp.notarialservices.province.service;

import com.vn.osp.notarialservices.province.domain.ProvinceBo;
import com.vn.osp.notarialservices.province.dto.Province;
import com.vn.osp.notarialservices.province.repository.ProvinceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by ProBook on 5/25/2017.
 */
@Component
public class ProvinceServiceImpl implements ProvinceService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProvinceServiceImpl.class);
    private final ProvinceRepository provinceRepository;
    private final ProvinceConverter provinceConverter;

    @Autowired
    public ProvinceServiceImpl(final ProvinceRepository provinceRepository, final ProvinceConverter provinceConverter) {
        this.provinceRepository = provinceRepository;
        this.provinceConverter = provinceConverter;
    }
    @Override
    public Optional<Boolean> AddProvince(String name,  Long entryUserId, String entryUserName,  String code)
    {
        return provinceRepository.AddProvince(name,entryUserId,entryUserName,code);

    }
    @Override
    public Optional<Boolean> UpdateProvince(Long id ,String name,  Long updateUserId, String updateUserName)
    {
        return provinceRepository.UpdateProvince(id,name,updateUserId,updateUserName);

    }
    @Override
    public Optional<Boolean> deleteProvince(Long id )
    {
        return provinceRepository.deleteProvince(id);

    }
    @Override
    public Optional<List<Province>> findProvinceByFilter(String stringFilter) {

        List<ProvinceBo> listBO = provinceRepository.findProvinceByFilter(stringFilter).orElse(new ArrayList<ProvinceBo>());
        ArrayList<Province> list = new ArrayList<Province>();
        if (listBO != null && listBO.size() > 0) {
            for (int i = 0; i < listBO.size(); i++) {
                list.add(Optional.ofNullable(listBO.get(i)).map(provinceConverter::convert).orElse(new Province()));
            }
        }
        return Optional.of(list);
    }
    @Override
    public Optional<BigInteger> countProvinceByFilter(String stringFilter)
    {
        return provinceRepository.countProvinceByFilter(stringFilter);
    }
}
