package com.vn.osp.notarialservices.paymentTransaction.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "npo_order_map_verification")
public class OrderMapVerification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable = false)
    private Long id;
    @Column(name="order_id", nullable = false)
    private String order_id;
    @Column(name="verify_id", nullable = false)
    private String verify_id;
    @Column(name="verify_status")
    private Integer verify_status;
}
