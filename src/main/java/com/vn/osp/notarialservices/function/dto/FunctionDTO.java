package com.vn.osp.notarialservices.function.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.vn.osp.notarialservices.common.dto.BaseEntityBeans;
import org.springframework.hateoas.ResourceSupport;

@XStreamAlias("FunctionDTO")
public class FunctionDTO extends BaseEntityBeans {

    private String code;
    private String name;
    private String parent_code;
    private Long type;
    @JsonCreator
    public FunctionDTO(@JsonProperty(value = "code", required = false) String code,
                       @JsonProperty(value = "name", required = false) String name,
                       @JsonProperty(value = "parent_code", required = false) String parent_code,
                       @JsonProperty(value = "type", required = false) Long type){
        this.code = code;
        this.name = name;
        this.parent_code = parent_code;
        this.type = type;
    }
    public FunctionDTO(){

    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParent_code() {
        return parent_code;
    }

    public void setParent_code(String parent_code) {
        this.parent_code = parent_code;
    }

    public Long getType() {
        return type;
    }

    public void setType(Long type) {
        this.type = type;
    }
}
