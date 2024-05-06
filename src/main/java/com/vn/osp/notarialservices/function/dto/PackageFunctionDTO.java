package com.vn.osp.notarialservices.function.dto;

public class PackageFunctionDTO {
    private String package_code;
    private int active_flg;
    private String code;
    private String name;
    private String parent_code;
    private int type;

    public String getPackage_code() {
        return package_code;
    }

    public void setPackage_code(String package_code) {
        this.package_code = package_code;
    }

    public int getActive_flg() {
        return active_flg;
    }

    public void setActive_flg(int active_flg) {
        this.active_flg = active_flg;
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
