package com.vn.osp.notarialservices.bank.service;

import com.vn.osp.notarialservices.bank.domain.BankBo;
import com.vn.osp.notarialservices.bank.dto.Bank;
import com.vn.osp.notarialservices.bank.repository.BankRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Created by Admin on 30/5/2017.
 */
@Component
public class BankServiceImpl implements BankService {
    private static final Logger LOGGER = LoggerFactory.getLogger(BankServiceImpl.class);
    private final BankRepository bankRepository;
    private final BankConverter bankConverter;

    @Autowired
    public BankServiceImpl(final BankRepository bankRepository, final BankConverter bankConverter) {
        this.bankRepository = bankRepository;
        this.bankConverter = bankConverter;
    }
    @Override
    public Optional<Boolean> addBank(String name, Long entryUserId, String entryUserName, String code, Long active)
    {
        return bankRepository.addBank(name, entryUserId,entryUserName,code, active);

    }
    @Override
    public Optional<Boolean> updateBank(Long id ,String name,  Long updateUserId, String updateUserName, Long active)
    {
        return bankRepository.updateBank(id,name,updateUserId,updateUserName,active);

    }
    @Override
    public Optional<Boolean> deleteBank(Long id )
    {
        return bankRepository.deleteBank(id);

    }
    @Override
    public Optional<List<Bank>> findBankByFilter(String stringFilter) {

        List<BankBo> listBO = bankRepository.findBankByFilter(stringFilter).orElse(new ArrayList<BankBo>());
        ArrayList<Bank> list = new ArrayList<Bank>();
        if (listBO != null && listBO.size() > 0) {
            for (int i = 0; i < listBO.size(); i++) {
                list.add(Optional.ofNullable(listBO.get(i)).map(bankConverter::convert).orElse(new Bank()));
            }
        }
        return Optional.of(list);
    }
    @Override
    public Optional<BigInteger> countBankByFilter(String stringFilter)
    {
        return bankRepository.countBankByFilter(stringFilter);
    }
}
