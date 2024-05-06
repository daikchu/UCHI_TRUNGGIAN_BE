package com.vn.osp.notarialservices.certfee.service;

import com.vn.osp.notarialservices.attestation.repository.AttestationRepositoryImpl;
import com.vn.osp.notarialservices.certfee.domain.CertFeeBO;
import com.vn.osp.notarialservices.certfee.dto.CertFeeDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class CertFeeServiceImpl implements CertFeeService{
    @Autowired CertFeeConverter certFeeConverter;
    private static final Logger LOG = LoggerFactory.getLogger(CertFeeServiceImpl.class);

    @PersistenceContext
    private EntityManager entityManager;
    @Override
    public CertFeeDTO getByType(int type) {
        CertFeeDTO result = new CertFeeDTO();
        List<CertFeeBO> list;
        String hql = "select bo from CertFeeBO bo where bo.type = "+type;
        list = entityManager.createQuery(hql, CertFeeBO.class).getResultList();
        if(list!=null && !list.isEmpty()){
            result = certFeeConverter.convert(list.get(0));
        }
        return result;
    }

    @Override
    public List<CertFeeDTO> search(String filter) {
        return null;
    }
}
