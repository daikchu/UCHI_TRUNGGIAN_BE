package com.vn.osp.notarialservices.contractfee.service;

import com.vn.osp.notarialservices.contractfee.domain.ContractFeeBO;
import com.vn.osp.notarialservices.contractfee.dto.ContractFeeCodeTemplate;
import com.vn.osp.notarialservices.contractfee.repository.ContractFeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;


/**
 * Created by Admin on 2018-05-09.
 */
@Component
public class ContractFeeServiceImpl implements ContractFeeService{
    private static final Logger LOG = LoggerFactory.getLogger(ContractFeeServiceImpl.class);
    private final ContractFeeRepository contractFeeRepository;

    @Autowired
    public ContractFeeServiceImpl(ContractFeeRepository contractFeeRepository){
        this.contractFeeRepository = contractFeeRepository;
    }

    @Override
    public Optional<Integer> addContractFee(ContractFeeBO contractFeeBO){
        return contractFeeRepository.addContractFee(contractFeeBO);

    }
    @Override
    public Optional<Integer> updateContractFee(ContractFeeBO contractFeeBO){
        return contractFeeRepository.updateContractFee(contractFeeBO);
    }
    @Override
    public Optional<List<ContractFeeBO>> getContractFeeCode(int page,String codeContract){
        return contractFeeRepository.getContractFeeCode(page,codeContract);
    }
    @Override
    public Optional<List<ContractFeeBO>> getContractFeeAll(){
        return contractFeeRepository.getContractFeeAll();
    }

    @Override
    public Optional<Long> countContractFeeAll(String codeContract){
        return contractFeeRepository.countContractFeeAll(codeContract);
    }

    //
    @Override
    public Optional<ContractFeeBO> getContractFeeId(int id){
        return contractFeeRepository.getContractFeeId(id);
    }

    @Override
    public Boolean deleteContractFee(int id){
        return contractFeeRepository.deleteContractFee(id);
    }

    @Override
    public Optional<List<ContractFeeBO>> getContractFeeOnlyCode(Long codeContract){
        return contractFeeRepository.getContractFeeOnlyCode(codeContract);
    }

    @Override
    public Optional<List<ContractFeeBO>> getContractFeeOnlyCodeExceptId(Long codeContract,int id){
        return contractFeeRepository.getContractFeeOnlyCodeExceptId(codeContract,id);
    }

    @Override
    public Optional<List<ContractFeeBO>> getContractFeeTcccCall(Long codeContract,long notaryCost){
        return contractFeeRepository.getContractFeeTcccCall(codeContract,notaryCost);
    }

    @Override
    public Optional<List<ContractFeeCodeTemplate>> getContractFeeCodeJoinContractTemplate(int page, String codeContract, String codeKind){
        return contractFeeRepository.getContractFeeCodeJoinContractTemplate(page,codeContract,codeKind);
    }
    @Override
    public Optional<Long> countContractFeeAllInnerJoinContractTemplate(String codeContract,String codeKind){
        return contractFeeRepository.countContractFeeAllInnerJoinContractTemplate(codeContract,codeKind);
    }

    @Override
    public Optional<List<ContractFeeCodeTemplate>> getTest(){
        return contractFeeRepository.getTest();
    }

}
