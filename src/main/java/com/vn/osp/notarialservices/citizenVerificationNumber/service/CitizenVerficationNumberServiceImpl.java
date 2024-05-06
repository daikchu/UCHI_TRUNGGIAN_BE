package com.vn.osp.notarialservices.citizenVerificationNumber.service;

import com.vn.osp.notarialservices.citizenVerificationNumber.domain.CitizenVerifyNumberBO;
import com.vn.osp.notarialservices.citizenVerificationNumber.repository.CitizenVerficationNumberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class CitizenVerficationNumberServiceImpl implements CitizenVerficationNumberService {
    @Autowired
    private CitizenVerficationNumberRepository citizenVerficationNumberRepository;
    @Override
    public Optional<CitizenVerifyNumberBO> saveOrUpdate(CitizenVerifyNumberBO citizenVerifyNumberBO, boolean isPlusTurn){
        return citizenVerficationNumberRepository.saveOrUpdate(citizenVerifyNumberBO, isPlusTurn);
    }
}
