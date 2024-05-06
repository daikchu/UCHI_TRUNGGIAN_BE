package com.vn.osp.notarialservices.paymentTransaction.service;

import com.vn.osp.notarialservices.citizenVerification.repository.CitizenVerficationRepositoryImpl;
import com.vn.osp.notarialservices.common.exception.BadRequestException;
import com.vn.osp.notarialservices.file.domain.AttachFiles;
import com.vn.osp.notarialservices.file.dto.AttachFileDTO;
import com.vn.osp.notarialservices.file.repository.AttachFilesRepository;
import com.vn.osp.notarialservices.file.service.AttachFileService;
import com.vn.osp.notarialservices.paymentTransaction.domain.PaymentTransactions;
import com.vn.osp.notarialservices.paymentTransaction.dto.PaymentTransactionDTO;
import com.vn.osp.notarialservices.paymentTransaction.repository.PaymentTransactionRepository;
import com.vn.osp.notarialservices.utils.TimeUtil;
import com.vn.osp.notarialservices.utils.config.Constants;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.ObjectNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@Component
@Transactional
public class PaymentTransactionServiceImpl implements PaymentTransactionService {
    @Autowired
    private PaymentTransactionRepository paymentTransactionRepository;
    @Autowired
    private AttachFileService attachFileService;
    private static final Logger LOG = LoggerFactory.getLogger(PaymentTransactionServiceImpl.class);
    @Override
    public Optional<PaymentTransactionDTO> insert(PaymentTransactionDTO paymentTransactionDTO, boolean isBySystem) {
        PaymentTransactions paymentTransaction = new PaymentTransactions().convertToBO(paymentTransactionDTO);
        long currentTimeMillis = Instant.now().toEpochMilli();
        paymentTransaction.setTransaction_time(String.valueOf(currentTimeMillis));
        paymentTransaction.setEntry_user_name(isBySystem ? Constants.USER_NAME_UPDATE.SYSTEM_ACCOUNT : paymentTransactionDTO.getEntry_user_name());
        paymentTransaction.setEntry_date_time(TimeUtil.getTimeNow());
        paymentTransaction.setUpdate_user_name(isBySystem ? Constants.USER_NAME_UPDATE.SYSTEM_ACCOUNT : paymentTransactionDTO.getUpdate_user_name());
        paymentTransaction.setUpdate_date_time(TimeUtil.getTimeNow());
        paymentTransaction = paymentTransactionRepository.saveOrUpdateTransactionBO(paymentTransaction).orElse(paymentTransaction);
        paymentTransactionDTO = paymentTransaction.toDTO(paymentTransactionDTO);

        //todo save attach files
        if(paymentTransactionDTO.getFile_names() != null && paymentTransactionDTO.getFile_names().size() > 0 && paymentTransactionDTO.getFile_names().size() == paymentTransactionDTO.getFile_paths().size()) {
            savePaymentAttachFile(paymentTransactionDTO);
        }

        return Optional.of(paymentTransactionDTO);
    }

    @Override
    @Transactional
    public Optional<PaymentTransactionDTO> update(PaymentTransactionDTO paymentTransactionDTO) {
        String transactionStatusToChange = paymentTransactionDTO.getTransaction_status();

        if(!transactionStatusToChange.equals(Constants.Status_PaymentTransaction.PENDING.getValue())
                && !transactionStatusToChange.equals(Constants.Status_PaymentTransaction.SUCCESS.getValue())) {
            throw new BadRequestException("Transaction status must be pending or success!", null);
        }
        if(paymentTransactionDTO.getId() == null) throw new ObjectNotFoundException("data", "transaction");
        PaymentTransactions paymentTransaction =
                StringUtils.isNotBlank(paymentTransactionDTO.getTransaction_id()) ?
                paymentTransactionRepository.findFirstByTransaction_id(paymentTransactionDTO.getTransaction_id()) :
                        paymentTransactionRepository.findOne(paymentTransactionDTO.getId());

        if(paymentTransaction    == null) throw new ObjectNotFoundException(paymentTransactionDTO.getTransaction_id(), "transaction");

        paymentTransaction.setTransaction_status(transactionStatusToChange);
        paymentTransaction.setNote(paymentTransactionDTO.getNote());

        paymentTransaction.setUpdate_user_name(paymentTransactionDTO.getUpdate_user_name());
        paymentTransaction.initBeforeUpdate();
        paymentTransaction = paymentTransactionRepository.update(paymentTransaction).orElse(paymentTransaction);
        paymentTransactionDTO = paymentTransaction.toDTO(paymentTransactionDTO);

        //todo remove attach files
        if(StringUtils.isNotBlank(paymentTransactionDTO.getFiles_id_remove())) {
            removePaymentAttachFile(paymentTransactionDTO.getFiles_id_remove());
        }
        //todo save attach files
        if(paymentTransactionDTO.getFile_names().size() > 0 && paymentTransactionDTO.getFile_names().size() == paymentTransactionDTO.getFile_paths().size()) {
            savePaymentAttachFile(paymentTransactionDTO);
        }

        return Optional.of(paymentTransactionDTO);
    }

    private void removePaymentAttachFile(String idsRemove){
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
    private void savePaymentAttachFile(PaymentTransactionDTO paymentTransactionDTO) {
        try{
            List<AttachFiles> attachFilesList = new ArrayList<>();
            List<String> fileNames = paymentTransactionDTO.getFile_names();
            List<String> filePaths = paymentTransactionDTO.getFile_paths();
            for(int index = 0; index < fileNames.size(); index++){
                AttachFiles attachFiles = new AttachFiles();
                attachFiles.setCode(String.valueOf(paymentTransactionDTO.getId()));
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

    private void validate() {

    }


    private void validateLength(PaymentTransactionDTO paymentTransactionDTO) {
        if(paymentTransactionDTO.getNote().length() > 255) {

        }
    }

    @Override
    public Optional<Boolean> deleteById(Long transaction_id) {
        return Optional.empty();
    }

    @Override
    public Optional<PaymentTransactionDTO> get(String transaction_id) {

        return Optional.empty();
    }

    @Override
    public Optional<PaymentTransactionDTO> getDetail(String transaction_id) {
        PaymentTransactionDTO paymentTransactionDTO = paymentTransactionRepository.getDetail(transaction_id).orElse(null);
        if(paymentTransactionDTO == null) throw new ObjectNotFoundException(transaction_id, "");
        List<AttachFiles> attachFiles = attachFileService.getByCodeAndType(String.valueOf(paymentTransactionDTO.getId()), Constants.ATTACH_FILE_CODE.PAYMENT_TRANSACTION.getValue());
        paymentTransactionDTO.setAttach_files(attachFiles);
        return Optional.of(paymentTransactionDTO);
    }

    @Override
    public Optional<PaymentTransactionDTO> getById(Long id) {
        PaymentTransactionDTO paymentTransactionDTO = paymentTransactionRepository.getDetailById(id).orElse(null);
        if(paymentTransactionDTO == null) throw new ObjectNotFoundException(id, "");
        List<PaymentTransactionDTO> transactionHistories = filter(paymentTransactionDTO.getOrder_id()).orElse(null);
        if(transactionHistories != null) paymentTransactionDTO.setTransaction_hists(transactionHistories);
        if(paymentTransactionDTO.getId()!=null) {
            List<AttachFiles> attachFiles = attachFileService.getByCodeAndType(String.valueOf(paymentTransactionDTO.getId()), Constants.ATTACH_FILE_CODE.PAYMENT_TRANSACTION.getValue());
            paymentTransactionDTO.setAttach_files(attachFiles);
        }
        return Optional.of(paymentTransactionDTO);
    }

    @Override
    public Optional<List<PaymentTransactionDTO>> filter(String order_id) {
        return paymentTransactionRepository.filter(order_id);
    }

    @Override
    public Optional<List<PaymentTransactionDTO>> page(String order_id) {

        return Optional.empty();
    }

    @Override
    public Optional<Long> count(String order_id) {
        return Optional.empty();
    }

    @Override
    public Optional<PaymentTransactions> saveOrUpdateTransactionBO(PaymentTransactions paymentTransactions){
        return paymentTransactionRepository.saveOrUpdateTransactionBO(paymentTransactions);
    }
    @Override
    public Boolean checkExitsRefTransactionId(String refTransactionId){
        return paymentTransactionRepository.checkExitsRefTransactionId(refTransactionId);
    }
    @Override
    public Optional<PaymentTransactions> getPaymentTransaction(String stringFilter){
        return paymentTransactionRepository.getPaymentTransaction(stringFilter);
    }
    @Override
    public Optional<List<PaymentTransactions>> updateStatusOrderIdAfter15Minutes(){
        return paymentTransactionRepository.updateStatusOrderIdAfter15Minutes();
    }
}
