package com.vn.osp.notarialservices.notification.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "npo_notifications")
public class Notifications {
    private static final long serialVersionUID = 1L;

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    private String type;
    private String content;
    private String target_url;
    private Integer view_status;
    @Column(name="entry_user_name")
    private String entry_user_name;
    @Column(name="entry_date_time")
    private java.sql.Timestamp entry_date_time;
    @Column(name="update_user_name")
    private String update_user_name;
    @Column(name="update_date_time")
    private java.sql.Timestamp update_date_time;
}
