package com.vn.osp.notarialservices.identityAuthentication.service;

import com.vn.osp.notarialservices.identityAuthentication.domain.BankBeneficiaryBO;
import com.vn.osp.notarialservices.identityAuthentication.dto.ErrorCode;
import com.vn.osp.notarialservices.identityAuthentication.dto.FormInputTransactionSync;
import com.vn.osp.notarialservices.identityAuthentication.repository.PaymentRepository;
import com.vn.osp.notarialservices.notification.domain.Notifications;
import com.vn.osp.notarialservices.notification.service.NotificationService;
import com.vn.osp.notarialservices.paymentTransaction.domain.PaymentTransactions;
import com.vn.osp.notarialservices.paymentTransaction.repository.PaymentTransactionRepository;
import com.vn.osp.notarialservices.utils.config.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;
    private final PaymentTransactionRepository paymentTransactionRepository;
    private final NotificationService notificationService;
    @Autowired
    public PaymentServiceImpl(PaymentRepository paymentRepository, PaymentTransactionRepository paymentTransactionRepository, NotificationService notificationService){
        this.paymentRepository = paymentRepository;
        this.paymentTransactionRepository = paymentTransactionRepository;
        this.notificationService = notificationService;
    }
    @Override
    public Optional<List<BankBeneficiaryBO>> getListBankBeneficiary(Integer genQRTransType){
        return paymentRepository.getListBankBeneficiary(genQRTransType);
    }
    @Override
    public Optional<String> genRefTransactionId(){
        String refTransactionId = "";
        try{
            refTransactionId = UUID.randomUUID().toString();
            // kiểm tra refTransactionId tạo ngẫu nhiên tồn tại trong csdl chưa
            Boolean exitsRefTransactionId = paymentTransactionRepository.checkExitsRefTransactionId(refTransactionId);
            while(exitsRefTransactionId){
                refTransactionId = UUID.randomUUID().toString();
                exitsRefTransactionId = paymentTransactionRepository.checkExitsRefTransactionId(refTransactionId);
            }
        }catch (Exception e){
            e.printStackTrace();
            return Optional.of("");
        }
        return Optional.of(refTransactionId);
    }
    @Override
    public Optional<ErrorCode> validateInputTransactionSync(FormInputTransactionSync formInputTransactionSync){
        ErrorCode errorCode = new ErrorCode();
        if(formInputTransactionSync.getTransactionid() == null || formInputTransactionSync.getTransactionid().isEmpty()){
            errorCode.setErrorCode(Constants.ERROR_CODE_PAYMENT.ERROR_CODE_007.getErrorCode());
            errorCode.setMessage(Constants.ERROR_CODE_PAYMENT.ERROR_CODE_007.getMessage());
            return Optional.of(errorCode);
        }else if(formInputTransactionSync.getTransactiontime() == null || formInputTransactionSync.getTransactiontime()<= 0){
            errorCode.setErrorCode(Constants.ERROR_CODE_PAYMENT.ERROR_CODE_004.getErrorCode());
            errorCode.setMessage(Constants.ERROR_CODE_PAYMENT.ERROR_CODE_004.getMessage());
            return Optional.of(errorCode);
        }else if(formInputTransactionSync.getReferencenumber() == null || formInputTransactionSync.getReferencenumber().isEmpty()){
            errorCode.setErrorCode(Constants.ERROR_CODE_PAYMENT.ERROR_CODE_008.getErrorCode());
            errorCode.setMessage(Constants.ERROR_CODE_PAYMENT.ERROR_CODE_008.getMessage());
            return Optional.of(errorCode);
        }else if(formInputTransactionSync.getAmount() == null || formInputTransactionSync.getAmount() <= 0){
            errorCode.setErrorCode(Constants.ERROR_CODE_PAYMENT.ERROR_CODE_002.getErrorCode());
            errorCode.setMessage(Constants.ERROR_CODE_PAYMENT.ERROR_CODE_002.getMessage());
            return Optional.of(errorCode);
        }
//        else if(formInputTransactionSync.getContent() == null || formInputTransactionSync.getContent().isEmpty()){
//            errorCode.setErrorCode(Constants.ERROR_CODE_PAYMENT.ERROR_CODE_009.getErrorCode());
//            errorCode.setMessage(Constants.ERROR_CODE_PAYMENT.ERROR_CODE_009.getMessage());
//            return Optional.of(errorCode);
//        }
        else if(formInputTransactionSync.getBankaccount() == null || formInputTransactionSync.getBankaccount().isEmpty()){
            errorCode.setErrorCode(Constants.ERROR_CODE_PAYMENT.ERROR_CODE_010.getErrorCode());
            errorCode.setMessage(Constants.ERROR_CODE_PAYMENT.ERROR_CODE_010.getMessage());
            return Optional.of(errorCode);
        }else if(formInputTransactionSync.getTransType() == null || formInputTransactionSync.getTransType().isEmpty()){
            errorCode.setErrorCode(Constants.ERROR_CODE_PAYMENT.ERROR_CODE_003.getErrorCode());
            errorCode.setMessage(Constants.ERROR_CODE_PAYMENT.ERROR_CODE_003.getMessage());
            return Optional.of(errorCode);
        }
//        else if(formInputTransactionSync.getOrderId() == null || formInputTransactionSync.getOrderId().isEmpty()){
//            errorCode.setErrorCode(Constants.ERROR_CODE_PAYMENT.ERROR_CODE_E05.getErrorCode());
//            errorCode.setMessage(Constants.ERROR_CODE_E05_PAYMENT.ERROR_CODE_E0501.getMessage());
//            return Optional.of(errorCode);
//        }
        else {
            errorCode.setErrorCode(Constants.ERROR_CODE_PAYMENT.SUCCESS.getErrorCode());
            errorCode.setMessage(Constants.ERROR_CODE_PAYMENT.SUCCESS.getMessage());
            return Optional.of(errorCode);
        }
        // them ma loi token, request body, ...
    }

    @Override
    public Optional<ErrorCode> validateExitsTransaction(FormInputTransactionSync formInputTransactionSync, PaymentTransactions paymentTransaction){
        ErrorCode errorCode = new ErrorCode();
//        if(!formInputTransactionSync.getOrderId().equals(paymentTransaction.getOrder_id())){
//            errorCode.setErrorCode(Constants.ERROR_CODE_PAYMENT.ERROR_CODE_E05.getErrorCode());
//            errorCode.setMessage("Lỗi Mã hóa đơn của khách hàng không tồn tại");
//            return Optional.of(errorCode);
//        }
        if(!formInputTransactionSync.getBankaccount().equals(paymentTransaction.getBank_account())){
            errorCode.setErrorCode(Constants.ERROR_CODE_PAYMENT.ERROR_CODE_E05.getErrorCode());
            errorCode.setMessage("Lỗi Số tài khoản ngân hàng không trùng khớp");
            return Optional.of(errorCode);
        }
        else if(!formInputTransactionSync.getTransType().equals(paymentTransaction.getTrans_type())){
            errorCode.setErrorCode(Constants.ERROR_CODE_PAYMENT.ERROR_CODE_E05.getErrorCode());
            errorCode.setMessage("Lỗi Loại giao dịch không trùng khớp");
            return Optional.of(errorCode);
        }else {
            errorCode.setErrorCode(Constants.ERROR_CODE_PAYMENT.SUCCESS.getErrorCode());
            errorCode.setMessage(Constants.ERROR_CODE_PAYMENT.SUCCESS.getMessage());
            return Optional.of(errorCode);
        }
    }
    @Override
    public Optional<PaymentTransactions> handleTransactionSync(FormInputTransactionSync formInputTransactionSync,PaymentTransactions paymentTranStatusPending, PaymentTransactions paymentTransactions){
        // case cập nhật trạng thái giao dịch thành công
        if(String.valueOf(formInputTransactionSync.getAmount()).equals(paymentTranStatusPending.getAmount())){
            paymentTransactions.setTransaction_status(Constants.Status_PaymentTransaction.SUCCESS.getValue());
        }
        // case thanh toán dư
        if(formInputTransactionSync.getAmount() > Integer.valueOf(paymentTranStatusPending.getAmount())){
            paymentTransactions.setStatus(Constants.StatusAddition_PaymentTransaction.PAY_OVER.getValue());
            Integer amoutOver = formInputTransactionSync.getAmount() - Integer.valueOf(paymentTranStatusPending.getAmount());
            paymentTransactions.setNote("Khách hàng thanh toán dư " + amoutOver + " VNĐ");
            paymentTransactions.setTransaction_status(Constants.Status_PaymentTransaction.SUCCESS.getValue());
            //save notification
            Notifications notifications = new Notifications();
            notifications.setType(Constants.NOTIFICATION_TYPE.CITIZEN_VERIFY_ORDER.getValue());
            notifications.setContent("Mã đơn hàng "+paymentTransactions.getOrder_id() + " đã thanh toán dư "+ amoutOver + " VNĐ");
            notifications.setTarget_url("/giao-dich-phi-xac-thuc/danh-sach?idOpenDetail="+paymentTransactions.getOrder_id() + "&isOpenDetail=true");
            notificationService.create(notifications);

        }
        // case thanh toán thiếu
        if(formInputTransactionSync.getAmount() < Integer.valueOf(paymentTranStatusPending.getAmount())){
            paymentTransactions.setStatus(Constants.StatusAddition_PaymentTransaction.PAY_NOT_ENOUGH.getValue());
            Integer amoutOver = Integer.valueOf(paymentTranStatusPending.getAmount()) - formInputTransactionSync.getAmount();
            paymentTransactions.setNote("Khách hàng thanh toán thiếu " + amoutOver + " VNĐ");
            paymentTransactions.setTransaction_status(Constants.Status_PaymentTransaction.FAIL.getValue());
            //save notification
            Notifications notifications = new Notifications();
            notifications.setType(Constants.NOTIFICATION_TYPE.CITIZEN_VERIFY_ORDER.getValue());
            notifications.setContent("Mã đơn hàng "+paymentTransactions.getOrder_id() + " đã thanh toán thiếu "+ amoutOver + " VNĐ");
            notifications.setTarget_url("/giao-dich-phi-xac-thuc/danh-sach?idOpenDetail="+paymentTransactions.getOrder_id() + "&pull=true");
            notificationService.create(notifications);
        }
        // noi dung thanh toan khong trung khop
//        if(!formInputTransactionSync.getContent().equals(paymentTranStatusPending.getContent())){
//            if(paymentTransactions.getNote() != null && !paymentTransactions.getNote().equals("")){
//                paymentTransactions.setNote(paymentTransactions.getNote() + "\n" + "Khách hàng thanh toán với nội dung " + formInputTransactionSync.getContent());
//            }else {
//                paymentTransactions.setNote("Khách hàng thanh toán với nội dung " + formInputTransactionSync.getContent());
//            }
//            // cập nhật trạng thái bổ sung sai nội dung
//            if(paymentTransactions.getStatus() != null && !paymentTransactions.getStatus().equals("")){
//                if(paymentTransactions.getStatus().equals(Constants.StatusAddition_PaymentTransaction.PAY_OVER.getValue())){
//                    paymentTransactions.setStatus(Constants.StatusAddition_PaymentTransaction.OVER_WRONG_CONTENT.getValue());
//                }else if(paymentTransactions.getStatus().equals(Constants.StatusAddition_PaymentTransaction.PAY_NOT_ENOUGH.getValue())){
//                    paymentTransactions.setStatus(Constants.StatusAddition_PaymentTransaction.PAYMENT_NOT_ENOUGH_WRONG_CONTENT.getValue());
//                }
//            }else {
//                paymentTransactions.setStatus(Constants.StatusAddition_PaymentTransaction.WRONG_CONTENT.getValue());
//           }
//        }
        return Optional.of(paymentTransactions);
    }
}
