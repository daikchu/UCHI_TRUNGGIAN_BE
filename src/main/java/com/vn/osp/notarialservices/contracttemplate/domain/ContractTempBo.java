package com.vn.osp.notarialservices.contracttemplate.domain;

import com.vn.osp.notarialservices.common.domain.AbstractAuditEntity;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by Admin on 1/6/2017.
 */
@Data
@Entity
@Table(name="npo_contract_template")
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(
                name = "addContractTemp",
                procedureName = "osp_npo_contract_template_add",
                parameters = {
                        @StoredProcedureParameter(name="name",type = String.class,mode = ParameterMode.IN),
                        @StoredProcedureParameter(name="code_temp",type = String.class,mode = ParameterMode.IN),
                        @StoredProcedureParameter(name="relate_object_A_display",type = String.class,mode = ParameterMode.IN),
                        @StoredProcedureParameter(name="relate_object_B_display",type = String.class,mode = ParameterMode.IN),
                        @StoredProcedureParameter(name="relate_object_C_display",type = String.class,mode = ParameterMode.IN),
                        @StoredProcedureParameter(name="description",type = String.class,mode = ParameterMode.IN),
                        @StoredProcedureParameter(name="active_flg",type = Long.class,mode = ParameterMode.IN),
                        @StoredProcedureParameter(name="period_flag",type = Long.class,mode = ParameterMode.IN),
                        @StoredProcedureParameter(name="mortage_cancel_func",type = Long.class,mode = ParameterMode.IN),
                        @StoredProcedureParameter(name="entry_user_id",type = Long.class,mode = ParameterMode.IN),
                        @StoredProcedureParameter(name="entry_user_name",type = String.class,mode = ParameterMode.IN),
                        @StoredProcedureParameter(name="kind_html",type = String.class,mode = ParameterMode.IN),
                        @StoredProcedureParameter(name="code_template",type = Long.class,mode = ParameterMode.IN),
                        @StoredProcedureParameter(name="kind_id",type = Long.class,mode = ParameterMode.IN)
                }
        ),
        @NamedStoredProcedureQuery(
                name ="updateContractTemp",
                procedureName = "osp_npo_contract_template_update",
                parameters = {
                        @StoredProcedureParameter(name="id",type = Long.class,mode = ParameterMode.IN),
                        @StoredProcedureParameter(name="name",type = String.class,mode = ParameterMode.IN),
                        @StoredProcedureParameter(name="code_temp",type = String.class,mode = ParameterMode.IN),
                        @StoredProcedureParameter(name="relate_object_A_display",type = String.class,mode = ParameterMode.IN),
                        @StoredProcedureParameter(name="relate_object_B_display",type = String.class,mode = ParameterMode.IN),
                        @StoredProcedureParameter(name="relate_object_C_display",type = String.class,mode = ParameterMode.IN),
                        @StoredProcedureParameter(name="description",type = String.class,mode = ParameterMode.IN),
                        @StoredProcedureParameter(name="active_flg",type = Long.class,mode = ParameterMode.IN),
                        @StoredProcedureParameter(name="period_flag",type = Long.class,mode = ParameterMode.IN),
                        @StoredProcedureParameter(name="mortage_cancel_func",type = Long.class,mode = ParameterMode.IN),
                        @StoredProcedureParameter(name="update_user_id",type = Long.class,mode = ParameterMode.IN),
                        @StoredProcedureParameter(name="update_user_name",type = String.class,mode = ParameterMode.IN),
                        @StoredProcedureParameter(name="kind_html",type = String.class,mode = ParameterMode.IN),
                        @StoredProcedureParameter(name="kind_id",type = Long.class,mode = ParameterMode.IN)
                }
        ),
        @NamedStoredProcedureQuery(
                name="findContractTempByFilter",
                procedureName = "osp_npo_contract_template_select_filter",
                resultClasses = {ContractTempBo.class},
                parameters = {
                        @StoredProcedureParameter(name="stringFilter",type = String.class,mode=ParameterMode.IN)
                }
        ),
        @NamedStoredProcedureQuery(
                name="countContractTemplateByCode",
                procedureName = "store_count_contract_template_by_code",
                parameters = {
                        @StoredProcedureParameter(name="code_temp",type = String.class,mode=ParameterMode.IN)
                }
        ),
        @NamedStoredProcedureQuery(
                name="deleteContractTemp",
                procedureName = "osp_npo_contract_template_delete",
                parameters = {
                        @StoredProcedureParameter(name="id",type = Long.class,mode=ParameterMode.IN)
                }
        ),
        @NamedStoredProcedureQuery(
                name="countContractTempByFilter",
                procedureName = "osp_npo_contract_template_count_filter",
                parameters = {
                        @StoredProcedureParameter(name="stringFilter",type = String.class,mode=ParameterMode.IN)
                }
        )
})
public class ContractTempBo extends AbstractAuditEntity implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;
    @Column(name = "kind_id")
    private Long kind_id;
    @Column(name = "kind_id_tt08")
    private Long kind_id_tt08;
    @Column(name = "code")
    private String code;
    @Column(name = "description")
    private String description;
    @Column(name = "file_name")
    private String file_name;
    @Column(name = "file_path")
    private String file_path;
    @Column(name = "active_flg")
    private Long active_flg;
    @Column(name = "relate_object_number")
    private Long relate_object_number;
    @Column(name = "relate_object_A_display")
    private String relate_object_A_display;
    @Column(name = "relate_object_B_display")
    private String relate_object_B_display;
    @Column(name = "relate_object_C_display")
    private String relate_object_C_display;
    @Column(name = "period_flag")
    private Long period_flag;
    @Column(name = "period_req_flag")
    private Long period_req_flag;
    @Column(name = "mortage_cancel_func")
    private Long mortage_cancel_func;
    @Column(name = "sync_option")
    private Long sync_option;
    @Column(name = "entry_user_id")
    private Long entry_user_id;
    @Column(name = "entry_user_name")
    private String entry_user_name;
    @Column(name = "entry_date_time")
    private java.sql.Timestamp entry_date_time;
    @Column(name = "update_user_id")
    private Long update_user_id;
    @Column(name = "update_user_name")
    private String update_user_name;
    @Column(name = "update_date_time")
    private java.sql.Timestamp update_date_time;
    @Column(name = "kind_html")
    private String kind_html;
    @Column(name = "office_code")
    private String office_code;
    @Column(name = "code_template")
    private Long code_template;

    public Long getSid() {
        return id;
    }

    public void setSid(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getKind_id() {
        return kind_id;
    }

    public void setKind_id(Long kind_id) {
        this.kind_id = kind_id;
    }

    public Long getKind_id_tt08() {
        return kind_id_tt08;
    }

    public void setKind_id_tt08(Long kind_id_tt08) {
        this.kind_id_tt08 = kind_id_tt08;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public String getFile_path() {
        return file_path;
    }

    public void setFile_path(String file_path) {
        this.file_path = file_path;
    }

    public Long getActive_flg() {
        return active_flg;
    }

    public void setActive_flg(Long active_flg) {
        this.active_flg = active_flg;
    }

    public Long getRelate_object_number() {
        return relate_object_number;
    }

    public void setRelate_object_number(Long relate_object_number) {
        this.relate_object_number = relate_object_number;
    }

    public Long getPeriod_flag() {
        return period_flag;
    }

    public void setPeriod_flag(Long period_flag) {
        this.period_flag = period_flag;
    }

    public Long getPeriod_req_flag() {
        return period_req_flag;
    }

    public void setPeriod_req_flag(Long period_req_flag) {
        this.period_req_flag = period_req_flag;
    }

    public Long getMortage_cancel_func() {
        return mortage_cancel_func;
    }

    public void setMortage_cancel_func(Long mortage_cancel_func) {
        this.mortage_cancel_func = mortage_cancel_func;
    }

    public Long getSync_option() {
        return sync_option;
    }

    public void setSync_option(Long sync_option) {
        this.sync_option = sync_option;
    }

    public Long getEntry_user_id() {
        return entry_user_id;
    }

    public void setEntry_user_id(Long entry_user_id) {
        this.entry_user_id = entry_user_id;
    }

    public String getEntry_user_name() {
        return entry_user_name;
    }

    public void setEntry_user_name(String entry_user_name) {
        this.entry_user_name = entry_user_name;
    }

    public Timestamp getEntry_date_time() {
        return entry_date_time;
    }

    public void setEntry_date_time(Timestamp entry_date_time) {
        this.entry_date_time = entry_date_time;
    }

    public Long getUpdate_user_id() {
        return update_user_id;
    }

    public void setUpdate_user_id(Long update_user_id) {
        this.update_user_id = update_user_id;
    }

    public String getUpdate_user_name() {
        return update_user_name;
    }

    public void setUpdate_user_name(String update_user_name) {
        this.update_user_name = update_user_name;
    }

    public Timestamp getUpdate_date_time() {
        return update_date_time;
    }

    public void setUpdate_date_time(Timestamp update_date_time) {
        this.update_date_time = update_date_time;
    }

    public String getKind_html() {
        return kind_html;
    }

    public void setKind_html(String kind_html) {
        this.kind_html = kind_html;
    }

    public String getOffice_code() {
        return office_code;
    }

    public void setOffice_code(String office_code) {
        this.office_code = office_code;
    }

    public Long getCode_template() {
        return code_template;
    }

    public void setCode_template(Long code_template) {
        this.code_template = code_template;
    }

    public String getRelate_object_A_display() {
        return relate_object_A_display;
    }

    public void setRelate_object_A_display(String relate_object_A_display) {
        this.relate_object_A_display = relate_object_A_display;
    }

    public String getRelate_object_B_display() {
        return relate_object_B_display;
    }

    public void setRelate_object_B_display(String relate_object_B_display) {
        this.relate_object_B_display = relate_object_B_display;
    }

    public String getRelate_object_C_display() {
        return relate_object_C_display;
    }

    public void setRelate_object_C_display(String relate_object_C_display) {
        this.relate_object_C_display = relate_object_C_display;
    }
}
