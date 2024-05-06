package com.vn.osp.notarialservices.file.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AttachFileDTO {
    private Long id;
    private String code;
    private String type;
    private String file_name;
    private String file_path;
    private String note;
}
