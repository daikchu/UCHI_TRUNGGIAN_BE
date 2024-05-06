package com.vn.osp.notarialservices.paymentTransaction.domain;

import com.vn.osp.notarialservices.common.domain.BaseEntity;
import com.vn.osp.notarialservices.paymentTransaction.dto.PaymentTransactionDTO;
import com.vn.osp.notarialservices.utils.TimeUtil;
import lombok.*;

import javax.persistence.*;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Date;

@Data
@Entity
@Table(name = "npo_payment_transactions")
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(
                name = "selectPaymentTransaction",
                procedureName = "osp_npo_payment_transactions_select_by_filter",
                resultClasses = {PaymentTransactions.class},
                parameters = {
                        @StoredProcedureParameter(name = "stringFilter", type = String.class, mode = ParameterMode.IN)
                })
})
public class PaymentTransactions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable = false)
    private Long id;
    @Column(name="transaction_id")
    private String transaction_id;
    @Column(name="order_id")
    private String order_id;
    @Column(name="transaction_time")
    private String transaction_time;
    @Column(name="reference_number")
    private String reference_number;
    @Column(name="amount")
    private String amount;
    @Column(name="content")
    private String content;
    @Column(name="bank_account")
    private String bank_account;
    @Column(name="trans_type")
    private String trans_type;
    @Column(name="error")
    private int error;
    @Column(name="error_reason")
    private String error_reason;
    @Column(name="toast_message")
    private String toast_message;
    @Column(name="ref_transaction_id")
    private String ref_transaction_id;
    @Column(name="transaction_status")
    private String transaction_status;
    @Column(name="status")
    private String status;
    @Column(name="file_names")
    private String file_names;
    @Column(name="file_paths")
    private String file_paths;
    @Column(name="note")
    private String note;

    private java.sql.Timestamp entry_date_time;
    private java.sql.Timestamp update_date_time;
    private String entry_user_name;
    private String update_user_name;

    public PaymentTransactionDTO toDTO(PaymentTransactionDTO dto) {
        if(dto == null) dto = new PaymentTransactionDTO();
        dto.setId(this.id);
        dto.setTransaction_id(this.transaction_id);
        dto.setOrder_id(this.order_id);
        dto.setTransaction_time(this.transaction_time);
        dto.setReference_number(this.reference_number);
        dto.setAmount(this.amount);
        dto.setContent(this.content);
        dto.setBank_account(this.bank_account);
        dto.setTrans_type(this.trans_type);
        dto.setError(this.error);
        dto.setError_reason(this.error_reason);
        dto.setToast_message(this.toast_message);
        dto.setRef_transaction_id(this.ref_transaction_id);
        dto.setTransaction_status(this.transaction_status);
        dto.setStatus(this.status);
        /*dto.setFile_names(this.file_names);
        dto.setFile_paths(this.file_paths);*/
        dto.setNote(this.note);
        return dto;
    }
    public PaymentTransactions convertToBO(PaymentTransactionDTO dto){
        PaymentTransactions bo = new PaymentTransactions();
        bo.setTransaction_id(dto.getTransaction_id());
        bo.setOrder_id(dto.getOrder_id());
        bo.setTransaction_time(dto.getTransaction_time());
        bo.setReference_number(dto.getReference_number());
        bo.setAmount(dto.getAmount());
        bo.setContent(dto.getContent());
        bo.setBank_account(dto.getBank_account());
        bo.setTrans_type(dto.getTrans_type());
        bo.setError(dto.getError());
        bo.setError_reason(dto.getError_reason());
        bo.setToast_message(dto.getToast_message());
        bo.setRef_transaction_id(dto.getRef_transaction_id());
        bo.setTransaction_status(dto.getTransaction_status());
        bo.setStatus(dto.getStatus());
        /*bo.setFile_names(dto.getFile_names());
        bo.setFile_paths(dto.getFile_paths());*/
        bo.setNote(dto.getNote());
        bo.setUpdate_user_name(dto.getUpdate_user_name());
        return bo;
    }

    public void initBeforeUpdate(){
        this.setUpdate_date_time(TimeUtil.getTimeNow());
        //update_user_name duoc truyen len tu session (truyen tu Spring FE)
    }
}
