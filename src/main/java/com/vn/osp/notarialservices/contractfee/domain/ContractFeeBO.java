package com.vn.osp.notarialservices.contractfee.domain;

import com.vn.osp.notarialservices.common.domain.AbstractAuditEntity;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigInteger;

/**
 * Created by Admin on 2018-05-09.
 */
@Data
@Entity
@Table(name = "npo_contract_fee")
public class ContractFeeBO extends AbstractAuditEntity implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id",nullable = false)
    private int id;
    @Column(name = "ct_template_code")
    private Long ct_template_code;
    @Column(name = "from_fee")
    private Long from_fee;
    @Column(name = "to_fee")
    private Long to_fee;

    @Column(name = "formula_fee")
    private String formula_fee;

    @Column(name = "circulars_fee")
    private String circulars_fee;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Long getCt_template_code() {
        return ct_template_code;
    }

    public void setCt_template_code(Long ct_template_code) {
        this.ct_template_code = ct_template_code;
    }

    public Long getFrom_fee() {
        return from_fee;
    }

    public void setFrom_fee(Long from_fee) {
        this.from_fee = from_fee;
    }

    public Long getTo_fee() {
        return to_fee;
    }

    public void setTo_fee(Long to_fee) {
        this.to_fee = to_fee;
    }

    public String getFormula_fee() {
        return formula_fee;
    }

    public void setFormula_fee(String formula_fee) {
        this.formula_fee = formula_fee;
    }

    public String getCirculars_fee() {
        return circulars_fee;
    }

    public void setCirculars_fee(String circulars_fee) {
        this.circulars_fee = circulars_fee;
    }
}
