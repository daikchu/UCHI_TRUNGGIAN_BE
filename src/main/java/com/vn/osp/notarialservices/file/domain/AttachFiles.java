package com.vn.osp.notarialservices.file.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "npo_files")
public class AttachFiles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable = false)
    private Long id;
    @Column(name = "code")//identify cho mỗi loại theo trường type
    private String code;
    @Column(name = "type")
    private String type;
    @Column(name = "file_name")
    private String file_name;
    @Column(name = "file_path")
    private String file_path;
    @Column(name = "note")
    private String note;
}
