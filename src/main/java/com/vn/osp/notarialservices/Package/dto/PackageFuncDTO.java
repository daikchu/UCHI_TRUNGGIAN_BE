package com.vn.osp.notarialservices.Package.dto;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("PackageFuncDTO")
public class PackageFuncDTO {
    private Long id;
    private String code;
    private String name;
    private Long user_num;
    private String description;
    private String note;
    private String cb01;
    private String cb02;
    private String cb03;
    public PackageFuncDTO(){
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getCb01() {
        return cb01;
    }

    public void setCb01(String cb01) {
        this.cb01 = cb01;
    }

    public String getCb02() {
        return cb02;
    }

    public void setCb02(String cb02) {
        this.cb02 = cb02;
    }

    public String getCb03() {
        return cb03;
    }

    public void setCb03(String cb03) {
        this.cb03 = cb03;
    }
}
