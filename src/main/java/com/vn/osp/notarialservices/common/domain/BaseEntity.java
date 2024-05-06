package com.vn.osp.notarialservices.common.domain;

import lombok.Data;

@Data
public class BaseEntity {
    private java.sql.Timestamp entry_date_time;
    private java.sql.Timestamp update_date_time;
    private String entry_user_name;
    private String update_user_name;
}
