package com.vn.osp.notarialservices.packagefunction.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "npo_group_role_function_template")
@NamedStoredProcedureQueries({
        @NamedStoredProcedureQuery(
                name = "osp_npo_group_role_function_template",
                procedureName = "osp_npo_group_role_function_template",
                resultClasses = { GroupRoleFunctionTemplateBO.class }
        )
})
public class GroupRoleFunctionTemplateBO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable = false)
    private Long id;
    @Column(name = "group_role_code")
    private String group_role_code;
    @Column(name = "group_role_name")
    private String group_role_name;
    @Column(name = "function_code")
    private String function_code;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGroup_role_code() {
        return group_role_code;
    }

    public void setGroup_role_code(String group_role_code) {
        this.group_role_code = group_role_code;
    }

    public String getGroup_role_name() {
        return group_role_name;
    }

    public void setGroup_role_name(String group_role_name) {
        this.group_role_name = group_role_name;
    }

    public String getFunction_code() {
        return function_code;
    }

    public void setFunction_code(String function_code) {
        this.function_code = function_code;
    }
}
