package com.vn.osp.notarialservices.preventinfoservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Created by Admin on 12/6/2017.
 */
@Service
public class PreventInfoServiceLinksFactory {
    private static final PreventInfoServiceController CONTROLLER = methodOn(PreventInfoServiceController.class);
}
