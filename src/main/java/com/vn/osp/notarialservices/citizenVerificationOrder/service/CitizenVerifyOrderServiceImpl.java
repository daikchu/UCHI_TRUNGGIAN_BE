package com.vn.osp.notarialservices.citizenVerificationOrder.service;

import com.vn.osp.notarialservices.citizenVerification.dto.CitizenVerificationsDTO;
import com.vn.osp.notarialservices.citizenVerification.service.CitizenVerficationService;
import com.vn.osp.notarialservices.citizenVerificationOrder.domain.CitizenVerifyOrderBO;
import com.vn.osp.notarialservices.citizenVerificationOrder.dto.CitizenVerifyOrderDTO;
import com.vn.osp.notarialservices.citizenVerificationOrder.dto.CitizenVerifyOrderSum;
import com.vn.osp.notarialservices.citizenVerificationOrder.repository.CitizenVerificationOrderRepository;
import com.vn.osp.notarialservices.common.exception.BadRequestException;
import com.vn.osp.notarialservices.common.util.PagingResult;
import com.vn.osp.notarialservices.customer.dto.CustomerDTO;
import com.vn.osp.notarialservices.customer.dto.CustomerInfoDTO;
import com.vn.osp.notarialservices.customer.service.CustomerService;
import com.vn.osp.notarialservices.file.domain.AttachFiles;
import com.vn.osp.notarialservices.file.service.AttachFileService;
import com.vn.osp.notarialservices.paymentTransaction.dto.PaymentTransactionDTO;
import com.vn.osp.notarialservices.paymentTransaction.service.PaymentTransactionService;
import com.vn.osp.notarialservices.paymentTransaction.service.PaymentTransactionServiceImpl;
import com.vn.osp.notarialservices.utils.GenerateIdUtil;
import com.vn.osp.notarialservices.utils.TimeUtil;
import com.vn.osp.notarialservices.utils.config.Constants;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.ObjectNotFoundException;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class CitizenVerifyOrderServiceImpl implements CitizenVerifyOrderService{

    @Autowired
    private CitizenVerificationOrderRepository citizenVerificationOrderRepository;
    @Autowired
    private PaymentTransactionService paymentTransactionService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private AttachFileService attachFileService;
    @Autowired
    private CitizenVerficationService citizenVerficationService;
    private static final Logger LOG = LoggerFactory.getLogger(CitizenVerifyOrderServiceImpl.class);

    private ModelMapper modelMapper = new ModelMapper();

    @Override
    public Optional<CitizenVerifyOrderDTO> insert(CitizenVerifyOrderDTO citizenVerifyOrderDTO) {
        CitizenVerifyOrderBO citizenVerifyOrderBO = citizenVerifyOrderDTO.toEntity();
        CustomerInfoDTO customerDTO = customerService.getCustomerInfoByCode(citizenVerifyOrderDTO.getNotary_office_code());
        if(customerDTO != null) {
            citizenVerifyOrderBO.setProvince_code(customerDTO.getProvince_code());
        }
        citizenVerifyOrderBO.setEntry_user_name(Constants.USER_NAME_UPDATE.SYSTEM_ACCOUNT);
        citizenVerifyOrderBO.setEntry_date_time(TimeUtil.getTimeNow());
        citizenVerifyOrderBO.setUpdate_user_name(Constants.USER_NAME_UPDATE.SYSTEM_ACCOUNT);
        citizenVerifyOrderBO.setUpdate_date_time(TimeUtil.getTimeNow());

        citizenVerifyOrderBO.setOrder_id(""); // thông tin order_id sinh ra sau khi thêm mới thành công
        citizenVerifyOrderBO = citizenVerificationOrderRepository.insert(citizenVerifyOrderBO).orElse(citizenVerifyOrderBO);
        // generate mã đơn hàng orderId
        // công thức generate: DH + 2 chữ cái lấy theo bảng chữ cái tiếng anh + 6 chữ số được tạo tăng dần từ 1-999999
        citizenVerifyOrderBO.setOrder_id(GenerateIdUtil.generateId("DH",citizenVerifyOrderBO.getId().intValue()));
        // cập nhật thông tin trường orderId
        citizenVerifyOrderBO = citizenVerificationOrderRepository.update(citizenVerifyOrderBO).orElse(citizenVerifyOrderBO);
        citizenVerifyOrderDTO = citizenVerifyOrderBO.toDTO(citizenVerifyOrderDTO);
        return Optional.of(citizenVerifyOrderDTO);
    }

    private void validateCitizenPackage() {

    }

    @Override
    public Optional<CitizenVerifyOrderDTO> update(CitizenVerifyOrderDTO citizenVerifyOrderDTO, boolean isCreateTransactionLog) {
        CitizenVerifyOrderBO citizenVerifyOrderDB = citizenVerificationOrderRepository.get(citizenVerifyOrderDTO.getOrder_id()).orElse(null);
        if(citizenVerifyOrderDB == null) {
            throw new BadRequestException("Not found!", null);

        }

        citizenVerifyOrderDB.setTransaction_status(citizenVerifyOrderDTO.getTransaction_status());
        citizenVerifyOrderDB.setStatus(citizenVerifyOrderDTO.getStatus());
        citizenVerifyOrderDB.setNote(citizenVerifyOrderDTO.getNote());
        citizenVerifyOrderDB.setUpdate_date_time(TimeUtil.getTimeNow());
        citizenVerifyOrderDB.setUpdate_user_name(citizenVerifyOrderDTO.getUpdate_user_name());

        CustomerInfoDTO customerDTO = customerService.getCustomerInfoByCode(citizenVerifyOrderDTO.getNotary_office_code());
        if(StringUtils.isNotBlank(customerDTO.getCode())) {
            citizenVerifyOrderDB.setProvince_code(customerDTO.getProvince_code());
        }
        citizenVerifyOrderDB = citizenVerificationOrderRepository.update(citizenVerifyOrderDB).orElse(citizenVerifyOrderDB);
        citizenVerifyOrderDTO = citizenVerifyOrderDB.toDTO(citizenVerifyOrderDTO);

        if(isCreateTransactionLog) {
            //todo create new transaction log
            PaymentTransactionDTO paymentTransactionDTO = new PaymentTransactionDTO();
            paymentTransactionDTO.setOrder_id(citizenVerifyOrderDB.getOrder_id());
            paymentTransactionDTO.setTransaction_status(citizenVerifyOrderDB.getTransaction_status());
            paymentTransactionDTO.setStatus(citizenVerifyOrderDB.getStatus());
            paymentTransactionDTO.setNote(citizenVerifyOrderDB.getNote());
            paymentTransactionDTO.setFile_names(citizenVerifyOrderDTO.getFile_names());
            paymentTransactionDTO.setFile_paths(citizenVerifyOrderDTO.getFile_paths());
            paymentTransactionDTO.setProvince_code(citizenVerifyOrderDB.getProvince_code());
            paymentTransactionDTO.setNotary_office_code(citizenVerifyOrderDB.getNotary_office_code());
            paymentTransactionDTO.setFiles_id_remove(citizenVerifyOrderDTO.getFiles_id_remove());
            paymentTransactionDTO.setVerify_fee(citizenVerifyOrderDB.getVerify_fee());
            paymentTransactionDTO.setVerify_number(citizenVerifyOrderDB.getVerify_number());
            paymentTransactionDTO.setEntry_user_name(citizenVerifyOrderDTO.getUpdate_user_name());
            paymentTransactionDTO.setUpdate_user_name(citizenVerifyOrderDTO.getUpdate_user_name());
            paymentTransactionDTO = paymentTransactionService.insert(paymentTransactionDTO, false).orElse(null);
        }

        return Optional.of(citizenVerifyOrderDTO);
    }

    @Override
    public Optional<Boolean> updateBySystem(String order_id, String transaction_status, String status, String verify_fee_received, String payment_content, String note) {

        CitizenVerifyOrderBO citizenVerifyOrderDB = citizenVerificationOrderRepository.get(order_id).orElse(null);
        if(citizenVerifyOrderDB == null) {
            LOG.error("Not found data by " + order_id);
            throw new BadRequestException("Not found!", null);

        }
        citizenVerifyOrderDB.setUpdate_date_time(TimeUtil.getTimeNow());
        citizenVerifyOrderDB.setUpdate_user_name(Constants.USER_NAME_UPDATE.SYSTEM_ACCOUNT);

        if(transaction_status != null) {
            citizenVerifyOrderDB.setTransaction_status(transaction_status);
        }
        if(status != null) {
            citizenVerifyOrderDB.setStatus(status);
        }
        if(verify_fee_received != null) {
            citizenVerifyOrderDB.setVerify_fee_received(verify_fee_received);
        }
        if(payment_content != null) {
            citizenVerifyOrderDB.setPayment_content(payment_content);
        }
        if(note != null) {
            citizenVerifyOrderDB.setNote(note);
        }
        try {
            citizenVerificationOrderRepository.update(citizenVerifyOrderDB).orElse(citizenVerifyOrderDB);
        } catch (Exception e) {
            LOG.error(e.getMessage());
            throw e;
        }

        return Optional.of(true);
    }

    @Override
    public Optional<Boolean> updateByChangeTransaction(PaymentTransactionDTO paymentTransactionDTO) {

        return Optional.empty();
    }

    @Override
    public Optional<Boolean> deleteById(Long order_id) {
        return Optional.empty();
    }

    @Override
    public Optional<CitizenVerifyOrderDTO> getDetail(String order_id) {
        CitizenVerifyOrderDTO citizenVerifyOrderDTO = citizenVerificationOrderRepository.getDetail(order_id).orElse(null);
        if(citizenVerifyOrderDTO==null){
            throw new ObjectNotFoundException("order_id", "CitizenVerifyOrderDTO");
        }
//        paymentTransactionService.g
//        List<AttachFiles> attachFiles = attachFileService.getByCodeAndType(String.valueOf(citizenVerifyOrderDTO.getPayment_id()), Constants.ATTACH_FILE_CODE.PAYMENT_TRANSACTION.getValue());
        List<PaymentTransactionDTO> transactionHists = paymentTransactionService.filter(order_id).orElse(new ArrayList<>());
        citizenVerifyOrderDTO.setTransaction_hists(transactionHists);
        List<CitizenVerificationsDTO> verifications = citizenVerficationService.getAllPurchased(order_id).orElse(null);
        citizenVerifyOrderDTO.setVerifications(verifications);
        return Optional.of(citizenVerifyOrderDTO);
    }

    @Override
    public Optional<List<CitizenVerifyOrderDTO>> filter(String order_id, String notary_office_code, String province_code,
                                                        String transaction_status, String status, String update_by, String order_time_from,
                                                        String order_time_to, String update_user_name) {
        List<CitizenVerifyOrderDTO> citizenVerifyOrderDTOS = citizenVerificationOrderRepository.filter(order_id, notary_office_code,
                province_code, transaction_status, status,
                update_by,order_time_from, order_time_to, update_user_name).orElse(new ArrayList());
        return Optional.of(citizenVerifyOrderDTOS);
    }

    @Override
    public Optional<PagingResult> page(int page, int numberPerPage, String order_id, String notary_office_code,
                                       String province_code, String transaction_status, String status, String update_by_officer,
                                       String order_time_from, String order_time_to, String update_user_name) {
        List<CitizenVerifyOrderDTO> citizenVerifyOrderDTOS = citizenVerificationOrderRepository.page(page, numberPerPage, order_id,
                notary_office_code, province_code, transaction_status,
                status, update_by_officer,order_time_from, order_time_to, update_user_name).orElse(new ArrayList());
        Long count = citizenVerificationOrderRepository.count(order_id, notary_office_code, province_code,
                transaction_status, status, update_by_officer,order_time_from, order_time_to, update_user_name).orElse(0L);
        return Optional.of(new PagingResult(citizenVerifyOrderDTOS, count));
    }

    @Override
    public Optional<Long> count(String order_id, String notary_office_code, String province_code,
                                String transaction_status, String status, String update_by,
                                String order_time_from, String order_time_to, String update_user_name) {
        Long count = citizenVerificationOrderRepository.count(order_id, notary_office_code, province_code, transaction_status,
                status, update_by,order_time_from, order_time_to, update_user_name).orElse(0L);
        return Optional.of(count);
    }
    @Override
    public Optional<List<CitizenVerifyOrderBO>> getCitizenVerifyOrderByFilter(String stringFilter){
        return citizenVerificationOrderRepository.getCitizenVerifyOrderByFilter(stringFilter);
    }

    @Override
    public Optional<Map> getSumCitizenVerifyOrder(String order_id, String notary_office_code, String province_code, String transaction_status, String status, String update_by, String order_time_from, String order_time_to, String update_user_name) {
        Map citizenVerifyOrderSum = citizenVerificationOrderRepository.getSumCitizenVerifyOrder(order_id, notary_office_code, province_code, transaction_status,
                status, update_by,order_time_from, order_time_to, update_user_name).orElse(null);
        return Optional.of(citizenVerifyOrderSum);
    }

    @Override
    public Optional<List<Map>> retrieveUserAccountsFromData(String province_code, String notary_office_code) {
        List<Map> items = citizenVerificationOrderRepository.retrieveUserAccountsFromData(province_code, notary_office_code).orElse(new ArrayList<>());
        return Optional.of(items);
    }

    private void removeAttachFile(String idsRemove){
        try {
            String[] ids = idsRemove.split(",");
            List<String> stringIds = Arrays.asList(ids);
            List<Long> intIds = stringIds.stream()
                    .map(Long::parseLong)
                    .collect(Collectors.toList());
            attachFileService.deleteAllByIds(intIds);
        } catch (Exception e){
            LOG.error(e.getMessage());
            throw e;
        }

    }
    private void saveAttachFile(CitizenVerifyOrderDTO citizenVerifyOrderDTO) {
        try{
            List<AttachFiles> attachFilesList = new ArrayList<>();
            List<String> fileNames = citizenVerifyOrderDTO.getFile_names();
            List<String> filePaths = citizenVerifyOrderDTO.getFile_paths();
            for(int index = 0; index < fileNames.size(); index++){
                AttachFiles attachFiles = new AttachFiles();
                attachFiles.setCode(String.valueOf(citizenVerifyOrderDTO.getOrder_id()));
                attachFiles.setType(Constants.ATTACH_FILE_CODE.PAYMENT_TRANSACTION.getValue());
                attachFiles.setFile_name(fileNames.get(index));
                attachFiles.setFile_path(filePaths.get(index));
                attachFilesList.add(attachFiles);
            }
            List<AttachFiles> resultSave = attachFileService.save(attachFilesList);
        } catch (Exception e) {
            LOG.error(e.getMessage());
            throw e;
        }
    }
}
