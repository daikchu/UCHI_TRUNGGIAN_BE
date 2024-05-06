package com.vn.osp.notarialservices.status.service;

import com.vn.osp.notarialservices.status.domain.StatusBO;

import java.util.List;

public interface StatusService {
    List<StatusBO> getList(String type);
}
