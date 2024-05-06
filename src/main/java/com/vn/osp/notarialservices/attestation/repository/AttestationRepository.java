package com.vn.osp.notarialservices.attestation.repository;

import com.vn.osp.notarialservices.attestation.domain.AttestationBO;

import java.util.List;

public interface AttestationRepository {
    Long count(String search);
    List<AttestationBO> page(int page, String search);
    List<AttestationBO> search(String search);
    List<AttestationBO> listByParentCode(String parent_code);
    AttestationBO get(Long id);
    Boolean add(AttestationBO item);
    Boolean update(AttestationBO item);
    Boolean delete(Long id);
}
