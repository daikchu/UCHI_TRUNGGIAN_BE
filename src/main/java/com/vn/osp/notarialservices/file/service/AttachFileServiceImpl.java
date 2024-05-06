package com.vn.osp.notarialservices.file.service;

import com.vn.osp.notarialservices.file.domain.AttachFiles;
import com.vn.osp.notarialservices.file.repository.AttachFilesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service
public class AttachFileServiceImpl implements AttachFileService{
    @Autowired
    private AttachFilesRepository attachFileRepository;

    @Override
    public AttachFiles save(AttachFiles attachFiles) {
        return attachFileRepository.save(attachFiles);
    }

    @Override
    public List<AttachFiles> save(List<AttachFiles> attachFiles) {
        return attachFileRepository.save(attachFiles);
    }

    @Override
    public void deleteAllByIds(List<Long> ids) {
        List<AttachFiles> attachFiles = attachFileRepository.findAllByIdIn(ids);
        attachFileRepository.deleteAllByIdIn(ids);
        for(AttachFiles attachFile : attachFiles){
            File file = new File(attachFile.getFile_path());
            if (file.delete()) {
                System.out.println("File deleted successfully.");
            } else {
                System.out.println("Failed to delete the file.");
            }
        }

    }

    @Override
    public List<AttachFiles> getByCodeAndType(String code, String type) {
        return attachFileRepository.findAllByCodeAndType(code, type);
    }

    @Override
    public List<String> getAllFileNameByCodeAndType(String code, String type) {
        return attachFileRepository.findAllFileNameByCodeAndType(code, type);
    }

    @Override
    public AttachFiles getByCodeAndTypeAndId(Long id, String type, String code) {
        return attachFileRepository.findAllByIdAndCodeAndType(id, code, type);
    }
}
