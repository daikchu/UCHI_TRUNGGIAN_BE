package com.vn.osp.notarialservices.contractfee.dto;

/**
 * Created by Admin on 2018-05-21.
 */
public class ContractFeeCodeTemplate {
    private int id;
    private Long ct_template_code;

    private Long from_fee;

    private Long to_fee;

    private String formula_fee;
    private String code_kind;
    private String template_name;
    private String kind_name;
    private String circulars_fee;
    public ContractFeeCodeTemplate() {
    }

    public ContractFeeCodeTemplate(int id, Long ct_template_code, Long from_fee, Long to_fee, String formula_fee, String code_kind, String template_name, String kind_name) {
        this.id = id;
        this.ct_template_code = ct_template_code;
        this.from_fee = from_fee;
        this.to_fee = to_fee;
        this.formula_fee = formula_fee;
        this.code_kind = code_kind;
        this.template_name = template_name;
        this.kind_name = kind_name;
    }

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

    public String getCode_kind() {
        return code_kind;
    }

    public void setCode_kind(String code_kind) {
        this.code_kind = code_kind;
    }

    public String getTemplate_name() {
        return template_name;
    }

    public void setTemplate_name(String template_name) {
        this.template_name = template_name;
    }

    public String getKind_name() {
        return kind_name;
    }

    public void setKind_name(String kind_name) {
        this.kind_name = kind_name;
    }

    public String getCirculars_fee() {
        return circulars_fee;
    }

    public void setCirculars_fee(String circulars_fee) {
        this.circulars_fee = circulars_fee;
    }
}
