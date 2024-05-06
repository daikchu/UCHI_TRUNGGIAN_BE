package com.vn.osp.notarialservices.status.service;

import com.vn.osp.notarialservices.status.domain.StatusBO;
import com.vn.osp.notarialservices.status.repository.StatusRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class StatusServiceImpl implements StatusService{

    @Autowired
    private StatusRepository statusRepository;

    @Override
    public List<StatusBO> getList(String type) {
        return StringUtils.isNotBlank(type) ? statusRepository.findByType(type) : statusRepository.findAll();
    }
}
