package com.vn.osp.notarialservices.attestation.service;

import com.vn.osp.notarialservices.attestation.dto.AttestationDTO;

import java.util.List;

public interface AttestationService {
    Long count(String filter);
    List<AttestationDTO> page(int page, String search);
    List<AttestationDTO> findByFilter(String filter);
    Boolean add(AttestationDTO item);
    Boolean update(AttestationDTO item);
    Boolean delete(Long id);
    AttestationDTO get(Long id);
    List<AttestationDTO> getByParentCode(String parent_code);
}
