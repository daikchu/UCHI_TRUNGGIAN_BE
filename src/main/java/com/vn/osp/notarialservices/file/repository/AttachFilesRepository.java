package com.vn.osp.notarialservices.file.repository;

import com.vn.osp.notarialservices.file.domain.AttachFiles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttachFilesRepository extends JpaRepository<AttachFiles, String> {
    void deleteAllByIdIn(List<Long> ids);
    List<AttachFiles> findAllByCodeAndType(String code, String type);
    @Query("SELECT a.file_name FROM AttachFiles a where a.file_name = :code and a.type = :fileType")
    List<String> findAllFileNameByCodeAndType(@Param("code") String code, @Param("fileType") String fileType);
    List<AttachFiles> findAllByIdIn(List<Long> ids);
    AttachFiles findAllByIdAndCodeAndType(Long id, String code, String type);
}
