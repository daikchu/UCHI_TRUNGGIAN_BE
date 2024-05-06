package com.vn.osp.notarialservices.notification.service;

import com.vn.osp.notarialservices.common.util.PagingResult;
import com.vn.osp.notarialservices.notification.domain.Notifications;

import java.util.List;

public interface NotificationService {
    PagingResult page(int page,
                      int numberPerPage,
                      String type,
                      Integer viewStatus);
    Notifications create(Notifications item);
    Notifications update(Notifications item);
    void updateViewStatus(Long id, Integer viewStatus);
    Long count(String type,
               Integer viewStatus);
}
