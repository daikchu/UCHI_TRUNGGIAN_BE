package com.vn.osp.notarialservices.citizenVerificationOrder.dto;

import com.vn.osp.notarialservices.citizenVerification.dto.CitizenVerificationsDTO;
import com.vn.osp.notarialservices.citizenVerificationOrder.domain.CitizenVerifyOrderBO;
import com.vn.osp.notarialservices.file.domain.AttachFiles;
import com.vn.osp.notarialservices.paymentTransaction.domain.PaymentTransactions;
import com.vn.osp.notarialservices.paymentTransaction.dto.PaymentTransactionDTO;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

//@Data
//@Entity
//@Table(name = "npo_citizen_verification_orders")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CitizenVerifyOrderDTO {
    private String order_id;

    private java.sql.Timestamp order_time;

    private int verify_number;

    private String verify_fee;

    private String verify_fee_received;

    private String notary_office_code;

    private String notary_office_name;

    private String province_code;

    private String province_name;

    private String transaction_status;

    private String transaction_status_name;

    private String status;

    private String status_name;

    private String notary_office_excutor;

    private String notary_office_excutor_fullname;

    private String note;

    private String update_by;

    private String payment_content;

    private String update_by_name;

    private String order_time_formatted;

    private String update_user_fullname;
    private Long payment_id;
    private String transaction_id;

    private String update_user_name;

    private List<PaymentTransactionDTO> transaction_hists;

    private List<CitizenVerificationsDTO> verifications;

    private List<String> file_names;
    private List<String> file_paths;
    private String files_id_remove;
    private List<AttachFiles> attach_files;

    public CitizenVerifyOrderBO toEntity(){
        CitizenVerifyOrderBO entity = new CitizenVerifyOrderBO();

        entity.setOrder_id(this.order_id);
        entity.setOrder_time(this.order_time);
        entity.setVerify_number(this.verify_number);
        entity.setVerify_fee(this.verify_fee);
        entity.setVerify_fee_received(this.verify_fee_received);
        entity.setNotary_office_code(this.notary_office_code);
        entity.setProvince_code(this.province_code);
        entity.setTransaction_status(this.transaction_status);
        entity.setStatus(this.status);
        entity.setNotary_office_excutor(this.notary_office_excutor);
        entity.setNotary_office_excutor_fullname(this.notary_office_excutor_fullname);
        entity.setNote(this.note);
        entity.setUpdate_by(this.update_by);
        entity.setPayment_content(this.payment_content);
        entity.setUpdate_by_name(this.update_by_name);
        entity.setUpdate_user_name(this.update_user_name);
        return entity;
    }
}
