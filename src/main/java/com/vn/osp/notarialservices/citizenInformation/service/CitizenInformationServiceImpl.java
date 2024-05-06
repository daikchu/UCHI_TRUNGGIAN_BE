package com.vn.osp.notarialservices.citizenInformation.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vn.osp.notarialservices.citizenInformation.domain.CitizenInfoBO;
import com.vn.osp.notarialservices.citizenInformation.dto.CitizenInformationDTO;
import com.vn.osp.notarialservices.citizenInformation.repository.CitizenInformationRepository;
import com.vn.osp.notarialservices.citizenVerification.domain.CitizenVerificationsBO;
import com.vn.osp.notarialservices.citizenVerification.repository.CitizenVerficationRepository;
import com.vn.osp.notarialservices.citizenVerificationNumber.domain.CitizenVerifyNumberBO;
import com.vn.osp.notarialservices.citizenVerificationNumber.repository.CitizenVerficationNumberRepository;
import com.vn.osp.notarialservices.customer.dto.CustomerInfoDTO;
import com.vn.osp.notarialservices.customer.service.CustomerService;
import com.vn.osp.notarialservices.paymentTransaction.domain.OrderMapVerification;
import com.vn.osp.notarialservices.paymentTransaction.repository.OrderMapVerifyRepository;
import com.vn.osp.notarialservices.utils.GenerateIdUtil;
import com.vn.osp.notarialservices.utils.TimeUtil;
import com.vn.osp.notarialservices.utils.config.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CitizenInformationServiceImpl implements CitizenInformationService {
    @Autowired
    private CitizenInformationRepository citizenInformationRepository;
    @Autowired
    private CitizenVerficationRepository citizenVerficationRepository;
    @Autowired
    private CitizenVerficationNumberRepository citizenVerficationNumberRepository;

    @Autowired
    private OrderMapVerifyRepository orderMapVerifyRepository;

    @Autowired
    private CustomerService customerService;

    @Override
    public Optional<CitizenInformationDTO> saveOrUpdate(CitizenInformationDTO citizenInformationDTO) {
        CitizenInfoBO citizenInfoBO = null;
        try {
            citizenInfoBO = citizenInformationDTO.convertToCitizenInfoBO(citizenInformationDTO);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        CitizenVerificationsBO citizenVerificationsBO = new CitizenVerificationsBO();
        // case xác thực danh tính thành công
        if (citizenInformationDTO.getVerify_status().equals(Constants.STATUS_VERIFY_CITIZEN.SUCCESS.getValue())) {
            citizenInfoBO = citizenInformationRepository.saveOrUpdate(citizenInfoBO).orElse(null);
            if (citizenInfoBO != null) {
                citizenVerificationsBO.setCitizen_info(citizenInformationDTO.getCitizen_info());
            }
        } else {
            // case xác thực danh tính thất bại, cập nhật lại mã xác thực chưa được sử dụng
            if (citizenInformationDTO.getOrder_map_verification().getId() != null) {
                OrderMapVerification map = citizenInformationDTO.getOrder_map_verification();
                map.setVerify_status(0);
                orderMapVerifyRepository.saveOrUpdate(map).orElse(null);
            }
        }
//        citizenVerificationsBO.setVerify_id(""); // thông tin verify_id sinh ra sau khi thêm mới thành công
        citizenVerificationsBO.setNotary_office_id(citizenInformationDTO.getNotary_office_id());
        CustomerInfoDTO customerDTO = customerService.getCustomerInfoByCode(citizenInformationDTO.getNotary_office_id());
        if (customerDTO != null) {
            citizenVerificationsBO.setProvince_code(customerDTO.getProvince_code());
        }
//        citizenVerificationsBO.setProvince_code(citizenInformationDTO.getProvince_code());
        citizenVerificationsBO.setVerify_date(TimeUtil.getTimeNow());
        citizenVerificationsBO.setVerify_by(citizenInformationDTO.getVerify_by());
        citizenVerificationsBO.setVerify_by_name(citizenInformationDTO.getVerify_by_name());
        citizenVerificationsBO.setVerify_status(citizenInformationDTO.getVerify_status());
        citizenVerificationsBO.setCitizen_verify_cccd(citizenInformationDTO.getCccd_number());
        citizenVerificationsBO.setCitizen_verify_fullname(citizenInformationDTO.getFull_name());
        citizenVerificationsBO.setEntry_user_name(Constants.USER_NAME_UPDATE.SYSTEM_ACCOUNT);
        citizenVerificationsBO.setEntry_date_time(TimeUtil.getTimeNow());

//        OrderMapVerification result = generateVerifyId(citizenInformationDTO.getNotary_office_id());
        citizenVerificationsBO.setVerify_id(citizenInformationDTO.getVerify_id());
        citizenVerificationsBO = citizenVerficationRepository.saveOrUpdate(citizenVerificationsBO).orElse(null);
        // cập nhật trạng thái mã xác thực đã được sử dụng
        if (citizenInformationDTO.getOrder_map_verification().getId() != null
//                && citizenInformationDTO.getVerify_status().equals(Constants.STATUS_VERIFY_CITIZEN.SUCCESS.getValue())
        ) {
            OrderMapVerification orderMapVerification = citizenInformationDTO.getOrder_map_verification();
            orderMapVerification.setVerify_status(1);
            orderMapVerifyRepository.saveOrUpdate(orderMapVerification).orElse(null);
        }
        // trừ lượt xác thực nếu như xác thực danh tính thành công
        if (citizenVerificationsBO.getId() != null) {
            citizenInformationDTO.setVerify_id(citizenVerificationsBO.getVerify_id());
//            if (citizenInformationDTO.getVerify_status().equals("success") || "Y".equals(citizenInformationDTO.getMinus_verify_number_yn())) {
                CitizenVerifyNumberBO citizenVerifyNumberBO = new CitizenVerifyNumberBO();
                citizenVerifyNumberBO.setNotary_office_code(citizenVerificationsBO.getNotary_office_id());
                citizenVerifyNumberBO.setProvince_code(citizenVerificationsBO.getProvince_code());
                citizenVerficationNumberRepository.saveOrUpdate(citizenVerifyNumberBO, false);
//            }
            citizenInformationDTO.setHaveVerifyNumber(true);
        }
        // case lưu thông tin xác thực thất bại. Bỏ dánh dấu mã xác thực đã được sử dụng
        else {
            if (citizenInformationDTO.getOrder_map_verification().getId() != null) {
                OrderMapVerification mapVerification = citizenInformationDTO.getOrder_map_verification();
                mapVerification.setVerify_status(0);
                orderMapVerifyRepository.saveOrUpdate(mapVerification).orElse(null);
            }
        }
//        else{
//              citizenInformationDTO.setHaveVerifyNumber(false);
//              citizenVerificationsBO = citizenVerficationRepository.saveOrUpdate(citizenVerificationsBO).orElse(null);
//            // generate mã đơn hàng verifyId
//            // công thức generate: XT + 2 chữ cái lấy theo bảng chữ cái tiếng anh + 6 chữ số được tạo tăng dần từ 1-999999
//              citizenVerificationsBO.setVerify_id(GenerateIdUtil.generateId("XT",citizenVerificationsBO.getId().intValue()));
//              // cập nhật thông tin trường verifyId
//              citizenVerificationsBO = citizenVerficationRepository.saveOrUpdate(citizenVerificationsBO).orElse(null);
//              citizenInformationDTO.setVerify_id(citizenVerificationsBO.getVerify_id());
//        }
        return Optional.of(citizenInformationDTO);
    }

    @Override
    public synchronized OrderMapVerification generateVerifyId(String notary_office_code) {
        OrderMapVerification orderMapVerification = citizenInformationRepository.getVerifyIdMapOrderId(notary_office_code).orElse(null);
        OrderMapVerification result = null;
        if (orderMapVerification.getId() != null) {
            // đánh dấu mã xác thực đang được sử dụng
            orderMapVerification.setVerify_status(2);
            result = orderMapVerifyRepository.saveOrUpdate(orderMapVerification).orElse(null);
        }
        return result;
    }
}
