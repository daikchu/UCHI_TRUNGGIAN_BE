package com.vn.osp.notarialservices.contractkind.service;

import com.vn.osp.notarialservices.contractkind.domain.ContractKindBo;
import com.vn.osp.notarialservices.contractkind.dto.ContractKind;
import com.vn.osp.notarialservices.contractkind.repository.ContractKindRepository;
import com.vn.osp.notarialservices.province.domain.ProvinceBo;
import com.vn.osp.notarialservices.province.dto.Province;
import com.vn.osp.notarialservices.province.repository.ProvinceRepository;
import com.vn.osp.notarialservices.province.service.ProvinceConverter;
import com.vn.osp.notarialservices.province.service.ProvinceServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by minh on 5/30/2017.
 */
@Component
public class ContractKindServiceImpl implements ContractKindService{
    private static final Logger LOGGER = LoggerFactory.getLogger(ContractKindServiceImpl.class);
    private final ContractKindRepository contractKindRepository;
    private final ContractKindConverter contractKindConverter;

    @Autowired
    public ContractKindServiceImpl(final ContractKindRepository contractKindRepository, final ContractKindConverter contractKindConverter) {
        this.contractKindRepository = contractKindRepository;
        this.contractKindConverter = contractKindConverter;
    }

    @Override
    public Optional<Boolean> ContractKindAdd(String name, Long entryUserId, String entryUserName, String code)
    {
        return contractKindRepository.ContractKindAdd(name,entryUserId,entryUserName,code);

    }
    @Override
    public Optional<Boolean> UpdateContractKind(Long id ,String name,  Long updateUserId, String updateUserName)
    {
        return contractKindRepository.UpdateContractKind(id,name,updateUserId,updateUserName);

    }
    @Override
    public Optional<Boolean> deleteContractKind(Long id )
    {
        return contractKindRepository.deleteContractKind(id);

    }
    @Override
    public Optional<List<ContractKind>> findContractKindByFilter(String stringFilter) {

        List<ContractKindBo> listBO = contractKindRepository.findContractKindByFilter(stringFilter).orElse(new ArrayList<ContractKindBo>());
        ArrayList<ContractKind> list = new ArrayList<ContractKind>();
        if (listBO != null && listBO.size() > 0) {
            for (int i = 0; i < listBO.size(); i++) {
                list.add(Optional.ofNullable(listBO.get(i)).map(contractKindConverter::convert).orElse(new ContractKind()));
            }
        }
        return Optional.of(list);
    }
    @Override
    public Optional<BigInteger> countContractKindByFilter(String stringFilter)
    {
        return contractKindRepository.countContractKindByFilter(stringFilter);
    }
}
