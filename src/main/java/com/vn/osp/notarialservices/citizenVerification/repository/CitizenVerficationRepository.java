package com.vn.osp.notarialservices.citizenVerification.repository;

import com.vn.osp.notarialservices.citizenVerification.domain.CitizenVerificationsBO;
import com.vn.osp.notarialservices.citizenVerification.dto.CitizenVerificationsDTO;
import com.vn.osp.notarialservices.common.util.PagingResult;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;


public interface CitizenVerficationRepository{
    Optional<CitizenVerificationsBO> saveOrUpdate(CitizenVerificationsBO citizenVerificationsBO);

    Optional<List<CitizenVerificationsDTO>> page(
            int page,
            int numberPerPage,
            String verify_id,
            String province_code,
            String notary_office_id,
            String verify_status,
            String verify_by,
            String citizen_verify_fullname,
            String citizen_verify_cccd,
            String verify_date_from,
            String verify_date_to,
            String order_id,
            String verify_by_name
    );

    Optional<Long> count(String verify_id,
                         String province_code,
                         String notary_office_id,
                         String verify_status,
                         String verify_by,
                         String citizen_verify_fullname,
                         String citizen_verify_cccd,
                         String verify_date_from,
                         String verify_date_to,
                         String order_id,
                         String verify_by_name
    );
    Optional<CitizenVerificationsDTO> getDetail(String verify_id);
    Optional<List<CitizenVerificationsDTO>> filter(String verify_id,
                                                   String province_code,
                                                   String notary_office_id,
                                                   String verify_status,
                                                   String verify_by,
                                                   String citizen_verify_fullname,
                                                   String citizen_verify_cccd,
                                                   String verify_date_from,
                                                   String verify_date_to,
                                                   String order_id,
                                                   String verify_by_name
    );
    Optional<List<Map>> retrieveUserAccountsFromData(String province_code, String notary_office_code);

    Optional<List<CitizenVerificationsDTO>> getListPurchased(String order_id);
}
