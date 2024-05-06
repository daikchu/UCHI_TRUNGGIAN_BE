package com.vn.osp.notarialservices.packagefunction.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.vn.osp.notarialservices.common.dto.BaseEntityBeans;

@XStreamAlias("GroupRoleFunctionTemplateDTO")
public class GroupRoleFunctionTemplateDTO extends BaseEntityBeans {
    private Long id;
    private String group_role_code;
    private String group_role_name;
    private String function_code;
    @JsonCreator
    public GroupRoleFunctionTemplateDTO(@JsonProperty(value = "id", required = false) Long id,
                               @JsonProperty(value = "group_role_code", required = false) String group_role_code,
                               @JsonProperty(value = "group_role_name", required = false) String group_role_name,
                               @JsonProperty(value = "function_code", required = false) String function_code) {
        this.id = id;
        this.group_role_code = group_role_code;
        this.group_role_name = group_role_name;
        this.function_code = function_code;
    }
    public GroupRoleFunctionTemplateDTO(){

    }

    public Long getSid() {
        return id;
    }

    public void setSid(Long id) {
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
