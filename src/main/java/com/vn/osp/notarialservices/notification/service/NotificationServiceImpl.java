package com.vn.osp.notarialservices.notification.service;

import com.vn.osp.notarialservices.common.util.PagingResult;
import com.vn.osp.notarialservices.notification.domain.Notifications;
import com.vn.osp.notarialservices.notification.repository.NotificationRepository;
import com.vn.osp.notarialservices.utils.TimeUtil;
import com.vn.osp.notarialservices.utils.config.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class NotificationServiceImpl implements NotificationService{
    @Autowired
    NotificationRepository notificationRepository;

    @Override
    public PagingResult page(int page, int numberPerPage, String type, Integer viewStatus) {
        List<Notifications> items = notificationRepository.page(page, numberPerPage, type, viewStatus);
        Long count = notificationRepository.count(type, viewStatus);
        return new PagingResult(items, count);
    }

    @Override
    public Notifications create(Notifications item) {
        item.setEntry_date_time(TimeUtil.getTimeNow());
        item.setUpdate_date_time(TimeUtil.getTimeNow());
        item.setEntry_user_name(Constants.USER_NAME_UPDATE.SYSTEM_ACCOUNT);
        item.setUpdate_user_name(Constants.USER_NAME_UPDATE.SYSTEM_ACCOUNT);
        return notificationRepository.save(item);
    }

    @Override
    public Notifications update(Notifications item) {
        item.setUpdate_date_time(TimeUtil.getTimeNow());
        item.setUpdate_user_name(Constants.USER_NAME_UPDATE.SYSTEM_ACCOUNT);
        return notificationRepository.save(item);
    }

    @Override
    public void updateViewStatus(Long id, Integer viewStatus) {

        notificationRepository.updateViewStatus(id, viewStatus);

    }

    @Override
    public Long count(String type, Integer viewStatus) {
        return notificationRepository.count(type, viewStatus);
    }
}
