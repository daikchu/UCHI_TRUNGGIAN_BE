package com.vn.osp.notarialservices.notification.repository;

import com.vn.osp.notarialservices.notification.domain.Notifications;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notifications, Long>, NotificationRepositoryCustom {

}
