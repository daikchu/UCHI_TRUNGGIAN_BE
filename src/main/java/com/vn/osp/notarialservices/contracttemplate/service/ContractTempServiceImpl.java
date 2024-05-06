package com.vn.osp.notarialservices.contracttemplate.service;

import com.vn.osp.notarialservices.contracttemplate.domain.ContractTempBo;
import com.vn.osp.notarialservices.contracttemplate.dto.AddContractTemp;
import com.vn.osp.notarialservices.contracttemplate.dto.ContractTemp;
import com.vn.osp.notarialservices.contracttemplate.repository.ContractTempRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.StoredProcedureQuery;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by Admin on 1/6/2017.
 */
@Component
public class ContractTempServiceImpl implements ContractTempService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ContractTempServiceImpl.class);
    private final ContractTempRepository contractTempRepository;
    private final ContractTempConverter contractTempConverter;

    @Autowired
    public ContractTempServiceImpl(final ContractTempRepository contractTempRepository, final ContractTempConverter contractTempConverter) {
        this.contractTempRepository = contractTempRepository;
        this.contractTempConverter = contractTempConverter;
    }
    @Override
    public Optional<Boolean> addContractTemp(AddContractTemp contractTemp)
    {
        return contractTempRepository.addContractTemp(contractTemp);

    }
    @Override
    public Optional<Boolean> updateContractTemp(ContractTemp contractTemp)
    {
        return contractTempRepository.updateContractTemp(contractTemp);

    }
    @Override
    public Optional<Boolean> deleteContractTemp(Long id )
    {
        return contractTempRepository.deleteContractTemp(id);

    }
    @Override
    public Optional<List<ContractTemp>> findContractTempByFilter(String stringFilter) {

        List<ContractTempBo> listBO = contractTempRepository.findContractTempByFilter(stringFilter).orElse(new ArrayList<ContractTempBo>());
        ArrayList<ContractTemp> list = new ArrayList<ContractTemp>();
        if (listBO != null && listBO.size() > 0) {
            for (int i = 0; i < listBO.size(); i++) {
                list.add(Optional.ofNullable(listBO.get(i)).map(contractTempConverter::convert).orElse(new ContractTemp()));
            }
        }
        return Optional.of(list);
    }
    @Override
    public Optional<BigInteger> countContractTempByFilter(String stringFilter)
    {
        return contractTempRepository.countContractTempByFilter(stringFilter);
    }

    @Override
    public Optional<Boolean> getByCode(String code) {
        return contractTempRepository.getByCode(code);
    }
}
