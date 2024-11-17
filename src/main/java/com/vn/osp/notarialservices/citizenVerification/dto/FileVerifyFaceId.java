package com.vn.osp.notarialservices.citizenVerification.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class FileVerifyFaceId {
    private String verifyId;
    private String notaryOfficeId;
    private String cccdNumber;
    private MultipartFile file;
}
