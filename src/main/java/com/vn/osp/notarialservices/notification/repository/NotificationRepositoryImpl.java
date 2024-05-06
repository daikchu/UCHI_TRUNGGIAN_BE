package com.vn.osp.notarialservices.notification.repository;

import com.vn.osp.notarialservices.citizenVerificationOrder.repository.CitizenVerificationOrderRepositoryImpl;
import com.vn.osp.notarialservices.notification.domain.Notifications;
import com.vn.osp.notarialservices.utils.TimeUtil;
import com.vn.osp.notarialservices.utils.config.Constants;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Repository
public class NotificationRepositoryImpl implements NotificationRepositoryCustom{
    private static final Logger LOG = LoggerFactory.getLogger(NotificationRepositoryImpl.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Notifications> page(int page, int numberPerPage, String type, Integer viewStatus) {
        int offset = numberPerPage*(page-1);
        String hqlQuery = "";
        if(StringUtils.isNotBlank(type)) {
            hqlQuery += " and bo.type = '" + type + "'";
        }
        if(viewStatus != null) {
            hqlQuery += " and bo.view_status = " + viewStatus;
        }
        Query query = entityManager.createQuery("SELECT bo FROM Notifications bo where 1=1 "+hqlQuery +" order by bo.entry_date_time asc");
        query.setFirstResult(offset);
        query.setMaxResults(numberPerPage);

        List<Notifications> resultList = query.getResultList();
        return resultList;
    }

    @Override
    public Long count(String type, Integer viewStatus) {
        String hqlQuery = "";
        if(StringUtils.isNotBlank(type)) {
            hqlQuery += " and bo.type = '" + type + "'";
        }
        if(viewStatus != null) {
            hqlQuery += " and bo.view_status = " + viewStatus;
        }

        Query query = entityManager.createQuery("SELECT COUNT(bo) FROM Notifications bo WHERE 1=1 "+hqlQuery);
        Long count = (Long) query.getSingleResult();
        return count;
    }

    @Override
    public int updateViewStatus(Long id, Integer status) {
        Query query = entityManager.createQuery("UPDATE Notifications bo SET bo.view_status = :status, bo.update_date_time = :update_date_time, bo.update_user_name = :update_user_name WHERE bo.id = :id")
                .setParameter("status", status)
                .setParameter("id", id)
                .setParameter("update_date_time", TimeUtil.getTimeNow())
                .setParameter("update_user_name", Constants.USER_NAME_UPDATE.SYSTEM_ACCOUNT);

        int updatedCount = query.executeUpdate();
        return updatedCount;
    }
}
