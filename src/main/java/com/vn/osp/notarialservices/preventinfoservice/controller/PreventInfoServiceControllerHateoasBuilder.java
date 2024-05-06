package com.vn.osp.notarialservices.preventinfoservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Admin on 12/6/2017.
 */
@Service
public class PreventInfoServiceControllerHateoasBuilder {
    private final PreventInfoServiceLinksFactory preventInfoServiceLinksFactory;

    @Autowired
    public PreventInfoServiceControllerHateoasBuilder(final PreventInfoServiceLinksFactory preventInfoServiceLinksFactory) {
        this.preventInfoServiceLinksFactory = preventInfoServiceLinksFactory;
    }
}
