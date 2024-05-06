package com.vn.osp.notarialservices.attestation.service;

import com.vn.osp.notarialservices.attestation.domain.AttestationBO;
import com.vn.osp.notarialservices.attestation.dto.AttestationDTO;
import com.vn.osp.notarialservices.attestation.repository.AttestationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AttestationServiceImpl implements AttestationService{
    @Autowired
    AttestationRepository attestationRepository;
    @Autowired AttestationTemplateConverter attestationTemplateConverter;


    @Override
    public Long count(String filter) {
        return attestationRepository.count(filter);
    }

    @Override
    public List<AttestationDTO> page(int page, String search) {
        List<AttestationBO> itemBOs = attestationRepository.page(page, search);
        List<AttestationDTO> items = new ArrayList<>();
        for (AttestationBO itemBo: itemBOs) {
            items.add(attestationTemplateConverter.convert(itemBo));
        }
        return items;
    }

    @Override
    public List<AttestationDTO> findByFilter(String filter) {
        List<AttestationBO> itemBOs = attestationRepository.search(filter);
        List<AttestationDTO> items = new ArrayList<>();
        for (AttestationBO itemBo: itemBOs) {
            items.add(attestationTemplateConverter.convert(itemBo));
        }
        return items;
    }

    @Override
    public Boolean add(AttestationDTO item) {
        AttestationBO bo = attestationTemplateConverter.convert(item);
        return attestationRepository.add(bo);
    }

    @Override
    public Boolean update(AttestationDTO item) {
        AttestationBO bo = attestationTemplateConverter.convert(item);
        return attestationRepository.update(bo);
    }

    @Override
    public Boolean delete(Long id) {
        return attestationRepository.delete(id);
    }

    @Override
    public AttestationDTO get(Long id) {
        AttestationBO bo = attestationRepository.get(id);
        return attestationTemplateConverter.convert(bo);
    }

    @Override
    public List<AttestationDTO> getByParentCode(String parent_code) {
        List<AttestationBO> listBO = attestationRepository.listByParentCode(parent_code);
        List<AttestationDTO> items = new ArrayList<>();
        for (AttestationBO itemBo: listBO) {
            items.add(attestationTemplateConverter.convert(itemBo));
        }
        return items;
    }
}
