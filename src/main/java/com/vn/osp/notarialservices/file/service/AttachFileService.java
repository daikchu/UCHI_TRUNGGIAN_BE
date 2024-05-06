package com.vn.osp.notarialservices.file.service;

import com.vn.osp.notarialservices.file.domain.AttachFiles;

import java.util.List;

public interface AttachFileService {
    AttachFiles save(AttachFiles attachFiles);
    List<AttachFiles> save(List<AttachFiles> attachFiles);
    void deleteAllByIds(List<Long> ids);
    List<AttachFiles> getByCodeAndType(String code, String type);
    List<String> getAllFileNameByCodeAndType(String code, String type);
    AttachFiles getByCodeAndTypeAndId(Long id, String type, String code);
}
