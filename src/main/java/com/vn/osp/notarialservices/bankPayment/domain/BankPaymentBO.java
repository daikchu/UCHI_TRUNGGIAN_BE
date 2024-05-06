package com.vn.osp.notarialservices.bankPayment.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "npo_bank_payment")
public class BankPaymentBO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable = false)
    private Long id;

    @Column(name="bank_code")
    private String bankCode;

    @Column(name="bank_name")
    private String bankName;
}
