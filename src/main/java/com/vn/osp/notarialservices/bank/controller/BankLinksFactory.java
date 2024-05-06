package com.vn.osp.notarialservices.bank.controller;

import org.springframework.stereotype.Service;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Created by Admin on 30/5/2017.
 */
@Service
public class BankLinksFactory {
    private static final BankController CONTROLLER = methodOn(BankController.class);
}
