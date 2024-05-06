package com.vn.osp.notarialservices.notification.repository;

import com.vn.osp.notarialservices.notification.domain.Notifications;

import java.util.List;

public interface NotificationRepositoryCustom {
    List<Notifications> page(int page,
                             int numberPerPage,
                             String type,
                             Integer viewStatus);
    Long count(String type,
               Integer viewStatus);
    int updateViewStatus(Long id, Integer status);
}
