package com.vn.osp.notarialservices.identityAuthentication.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "npo_bank_beneficiary")
public class BankBeneficiaryBO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable = false)
    private Long id;

    @Column(name="bank_account")
    private String bankAccount;

    @Column(name="amount")
    private String amount;

    @Column(name="content")
    private String content;

    @Column(name="bank_code")
    private String bankCode;

    @Column(name="trans_type")
    private String transType;

    @Column(name="user_bank_name")
    private String userBankName;

    @Column(name="order_id")
    private String orderId;

    @Column(name="sign")
    private String sign;

    @Column(name="customer_bank_account")
    private String customerBankAccount;

    @Column(name="customer_bank_code")
    private String customerBankCode;

    @Column(name="customer_name")
    private String customerName;

    @Column(name="gen_qr_trans_type")
    private Integer genQRTransType;
}
