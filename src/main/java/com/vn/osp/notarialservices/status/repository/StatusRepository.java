package com.vn.osp.notarialservices.status.repository;

import com.vn.osp.notarialservices.common.repository.BaseRepository;
import com.vn.osp.notarialservices.status.domain.StatusBO;
import com.vn.osp.notarialservices.status.domain.StatusId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface StatusRepository extends JpaRepository<StatusBO, StatusId> {
    @Query(value = "SELECT * FROM npo_status s WHERE s.type = ?1", nativeQuery = true)
    List<StatusBO> findByType(String type);
}
