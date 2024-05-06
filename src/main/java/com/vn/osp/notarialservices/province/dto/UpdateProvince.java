package com.vn.osp.notarialservices.province.dto;

/**
 * Created by ProBook on 5/26/2017.
 */
public class UpdateProvince {
    private Long id ;
    private String name;
    private Long update_user_id;
    private String update_user_name;


    public UpdateProvince() {
    }

    public UpdateProvince(Long id, String name, Long update_user_id, String update_user_name) {
        this.id = id;
        this.name = name;
        this.update_user_id = update_user_id;
        this.update_user_name = update_user_name;

    }

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


}
