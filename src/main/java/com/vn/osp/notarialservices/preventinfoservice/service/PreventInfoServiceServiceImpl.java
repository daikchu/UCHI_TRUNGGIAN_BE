package com.vn.osp.notarialservices.preventinfoservice.service;

import com.vn.osp.notarialservices.preventinfoservice.domain.PreventInfoServiceBo;
import com.vn.osp.notarialservices.preventinfoservice.dto.PreventInfoService;
import com.vn.osp.notarialservices.preventinfoservice.repository.PreventInfoServiceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by truonglq on 12/6/2017.
 */
@Component
public class PreventInfoServiceServiceImpl implements PreventInfoServiceService{
    private static final Logger LOGGER = LoggerFactory.getLogger(PreventInfoServiceServiceImpl.class);
    private final PreventInfoServiceRepository preventInfoServiceRepository;
    private final PreventInfoServiceConverter preventInfoServiceConverter;

    @Autowired
    public PreventInfoServiceServiceImpl(final PreventInfoServiceRepository preventInfoServiceRepository, final PreventInfoServiceConverter preventInfoServiceConverter) {
        this.preventInfoServiceConverter = preventInfoServiceConverter;
        this.preventInfoServiceRepository = preventInfoServiceRepository;
    }
    @Override
    public Optional<List<PreventInfoService>> findPreventInfoServiceByFilter(String stringFilter) {

        List<PreventInfoServiceBo> listBO = preventInfoServiceRepository.findPreventInfoServiceByFilter(stringFilter).orElse(new ArrayList<PreventInfoServiceBo>());
        ArrayList<PreventInfoService> list = new ArrayList<PreventInfoService>();
        if (listBO != null && listBO.size() > 0) {
            for (int i = 0; i < listBO.size(); i++) {
                list.add(Optional.ofNullable(listBO.get(i)).map(preventInfoServiceConverter::convert).orElse(new PreventInfoService()));
            }
        }
        return Optional.of(list);
    }
}
