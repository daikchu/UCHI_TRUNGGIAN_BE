package com.vn.osp.notarialservices.citizenVerificationOrder.domain;

import com.vn.osp.notarialservices.citizenVerificationOrder.dto.CitizenVerifyOrderDTO;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "npo_citizen_verification_orders")
public class CitizenVerifyOrderBO implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "order_id", nullable = false)
    private String order_id;

    @Column(name = "order_time")
    private Timestamp order_time;

    @Column(name = "verify_number")
    private int verify_number;

    @Column(name = "verify_fee")
    private String verify_fee;

    @Column(name = "verify_fee_received")
    private String verify_fee_received;

    @Column(name = "notary_office_code")
    private String notary_office_code;

    @Column(name = "province_code")
    private String province_code;

    @Column(name = "transaction_status")
    private String transaction_status;

    @Column(name = "status")
    private String status;

    @Column(name = "notary_office_excutor")
    private String notary_office_excutor;

    @Column(name = "notary_office_excutor_fullname")
    private String notary_office_excutor_fullname;

    @Column(name = "note")
    private String note;

    @Column(name = "update_by")
    private String update_by;

    @Column(name = "update_by_name")
    private String update_by_name;

    private String payment_content;

    private java.sql.Timestamp entry_date_time;
    private java.sql.Timestamp update_date_time;
    private String entry_user_name;
    private String update_user_name;

    public CitizenVerifyOrderDTO toDTO(CitizenVerifyOrderDTO dto) {
        if(dto == null) dto = new CitizenVerifyOrderDTO();

        dto.setOrder_id(this.order_id);
        dto.setOrder_time(this.order_time);
        dto.setVerify_number(this.verify_number);
        dto.setVerify_fee(this.verify_fee);
        dto.setVerify_fee_received(this.verify_fee_received);
        dto.setNotary_office_code(this.notary_office_code);
        dto.setProvince_code(this.province_code);
        dto.setTransaction_status(this.transaction_status);
        dto.setStatus(this.status);
        dto.setNotary_office_excutor(this.notary_office_excutor);
        dto.setNotary_office_excutor_fullname(this.notary_office_excutor_fullname);
        dto.setNote(this.note);
        dto.setUpdate_by(this.update_by);
        dto.setPayment_content(this.payment_content);
        dto.setUpdate_by_name(this.update_by_name);
        return dto;
    }
}
