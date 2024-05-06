package com.vn.osp.notarialservices.citizenVerification.service;

import com.vn.osp.notarialservices.citizenVerification.domain.CitizenVerificationsBO;
import com.vn.osp.notarialservices.citizenVerification.dto.CitizenVerificationsDTO;
import com.vn.osp.notarialservices.citizenVerification.repository.CitizenVerficationRepository;
import com.vn.osp.notarialservices.citizenVerificationOrder.dto.CitizenVerifyOrderDTO;
import com.vn.osp.notarialservices.common.util.PagingResult;
import com.vn.osp.notarialservices.paymentTransaction.dto.PaymentTransactionDTO;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Transactional
public class CitizenVerficationServiceImpl implements CitizenVerficationService {
    @Autowired
    private CitizenVerficationRepository citizenVerficationRepository;
    @Override
    public Optional<CitizenVerificationsBO> saveOrUpdate(CitizenVerificationsBO citizenVerificationsBO){
        return citizenVerficationRepository.saveOrUpdate(citizenVerificationsBO);
    }

    @Override
    public Optional<PagingResult> page(int page, int numberPerPage, String verify_id, String province_code, String notary_office_id, String verify_status, String verify_by,
                                       String citizen_verify_fullname, String citizen_verify_cccd,
                                       String verify_date_from, String verify_date_to,
                                       String order_id, String verify_by_name) {
        Long count = citizenVerficationRepository.count(verify_id, province_code, notary_office_id,
                verify_status, verify_by,citizen_verify_fullname, citizen_verify_cccd,
                verify_date_from, verify_date_to, order_id, verify_by_name).orElse(0L);
        List<CitizenVerificationsDTO> items = new ArrayList<>();
        if(count > 0) {
            items = citizenVerficationRepository.page(page, numberPerPage, verify_id, province_code, notary_office_id,
                    verify_status, verify_by,citizen_verify_fullname, citizen_verify_cccd,
                    verify_date_from, verify_date_to, order_id, verify_by_name).orElse(new ArrayList<>());
        }

        return Optional.of(new PagingResult(items, count));
    }

    @Override
    public Optional<Long> count(String verify_id, String province_code, String notary_office_id, String verify_status, String verify_by, String citizen_verify_fullname,
                                String citizen_verify_cccd, String verify_date_from, String verify_date_to, String order_id, String verify_by_name) {
        Long count = citizenVerficationRepository.count(verify_id, province_code, notary_office_id,
                verify_status, verify_by,citizen_verify_fullname, citizen_verify_cccd,
                verify_date_from, verify_date_to,
                order_id, verify_by_name).orElse(0L);
        return Optional.of(count);
    }

    @Override
    public Optional<CitizenVerificationsDTO> getDetail(String verify_id) {
        Optional<CitizenVerificationsDTO> optional = citizenVerficationRepository.getDetail(verify_id);
        if(!optional.isPresent()){
            throw new ObjectNotFoundException("verify_id", "CitizenVerification");
        }
        return optional;
    }

    @Override
    public Optional<List<CitizenVerificationsDTO>> filter(String verify_id, String province_code, String notary_office_id,
                                                          String verify_status, String verify_by, String citizen_verify_fullname,
                                                          String citizen_verify_cccd, String verify_date_from, String verify_date_to,
                                                          String order_id, String verify_by_name) {
        List<CitizenVerificationsDTO> items = citizenVerficationRepository.filter(verify_id, province_code, notary_office_id,
                verify_status, verify_by,citizen_verify_fullname, citizen_verify_cccd,
                verify_date_from, verify_date_to, order_id, verify_by_name).orElse(new ArrayList<>());
        return Optional.of(items);
    }

    @Override
    public Optional<List<Map>> retrieveUserAccountsFromData(String province_code, String notary_office_code) {
        List<Map> items = citizenVerficationRepository.retrieveUserAccountsFromData(province_code, notary_office_code).orElse(new ArrayList<>());
        return Optional.of(items);
    }

    @Override
    public Optional<List<CitizenVerificationsDTO>> getAllPurchased(String order_id) {
        List<CitizenVerificationsDTO> items = citizenVerficationRepository.getListPurchased(order_id).orElse(null);
        return Optional.of(items);
    }
}
