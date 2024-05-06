package com.vn.osp.notarialservices.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Admin on 30/5/2017.
 */
@Service
public class BankControllerHateoasBuilder {
    private final BankLinksFactory bankLinksFactory;

    @Autowired
    public BankControllerHateoasBuilder(final BankLinksFactory bankLinksFactory) {
        this.bankLinksFactory = bankLinksFactory;
    }
}
