package com.vn.osp.notarialservices.Package.dto;

import org.springframework.hateoas.ResourceSupport;

public class PackageDTO extends ResourceSupport {

    private Long id;
    private String code;
    private String name;
    private Long user_num;
    private String description;
    private String note;

    public PackageDTO(){

    }
    public PackageDTO(Long id, String code, String name, Long user_num, String description, String note){
        this.id = id;
        this.code = code;
        this.name = name;
        this.user_num = user_num;
        this.description = description;
        this.note = note;
    }

    public Long getSid() {
        return id;
    }

    public void setSid(Long id) {
        this.id = id;
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

    public Long getUser_num() {
        return user_num;
    }

    public void setUser_num(Long user_num) {
        this.user_num = user_num;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
